package com.example.soptexampleproject.presentation.sign.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.soptexampleproject.databinding.ActivitySignUpBinding
import com.example.soptexampleproject.presentation.sign.RequestSignUp
import com.example.soptexampleproject.presentation.sign.ResponseSignUp
import com.example.soptexampleproject.presentation.sign.ServiceCreator
import com.example.soptexampleproject.util.ResponseWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
    }

    private fun initEvent() {

        binding.signBtn.setOnClickListener {
            if (binding.nameEdit.text.isNotBlank() && binding.idEdit.text.isNotBlank() && binding.passwordEdit.text.isNotBlank()) {
                signUpNetWork()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signUpNetWork() {
        val requestSignUp = RequestSignUp(
            name = binding.nameEdit.text.toString().trim(),
            email = binding.idEdit.text.toString().trim(),
            password = binding.passwordEdit.text.toString().trim()
        )

        val call: Call<ResponseWrapper<ResponseSignUp>> = ServiceCreator.soptService.postSignUp(requestSignUp)

        call.enqueue(object : Callback<ResponseWrapper<ResponseSignUp>> {
            override fun onResponse(
                call: Call<ResponseWrapper<ResponseSignUp>>,
                response: Response<ResponseWrapper<ResponseSignUp>>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@SignUpActivity, "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java).apply {
                        putExtra("id", binding.idEdit.text.toString())
                        putExtra("password", binding.passwordEdit.text.toString())
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseWrapper<ResponseSignUp>>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
}