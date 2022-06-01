package com.example.soptexampleproject.presentation.sign.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.soptexampleproject.databinding.ActivitySignUpBinding
import com.example.soptexampleproject.data.remote.sign.models.RequestSignUp
import com.example.soptexampleproject.data.remote.sign.models.ResponseSignUp
import com.example.soptexampleproject.data.remote.ServiceCreator
import com.example.soptexampleproject.util.ResponseWrapper
import kotlinx.coroutines.*
import retrofit2.Call

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

        val response = CoroutineScope(Dispatchers.IO).async {
            ServiceCreator.soptService.postSignUp(requestSignUp)
        }
        CoroutineScope(Dispatchers.Main).launch {
            response.await()
            if (response.isCompleted) {
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
    }
}