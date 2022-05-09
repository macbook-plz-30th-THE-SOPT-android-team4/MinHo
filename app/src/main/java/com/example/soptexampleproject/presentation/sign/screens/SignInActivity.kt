package com.example.soptexampleproject.presentation.sign.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.soptexampleproject.databinding.ActivitySignInBinding
import com.example.soptexampleproject.presentation.home.screens.ViewPagerActivity
import com.example.soptexampleproject.presentation.sign.RequestSignIn
import com.example.soptexampleproject.presentation.sign.ResponseSignIn
import com.example.soptexampleproject.presentation.sign.ServiceCreator
import com.example.soptexampleproject.util.ResponseWrapper
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
        getResultText =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    binding.idEdit.text.append(it.data?.getStringExtra("id") ?: "")
                    binding.passwordEdit.text.append(it.data?.getStringExtra("password") ?: "")
                }
            }
    }

    private fun initEvent() {
        binding.loginBtn.setOnClickListener {
            if (binding.idEdit.text.isNotBlank() && binding.passwordEdit.text.isNotBlank()) {

                /*Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ViewPagerActivity::class.java)
                startActivity(intent)*/
                loginNetWork()
            }else{
                Toast.makeText(this, "빈칸을 먼저 채워주세요.", Toast.LENGTH_SHORT).show()
            }

        }
        binding.signupBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResultText.launch(intent)
        }

    }

    private fun loginNetWork() {
        val requestSignIn = RequestSignIn(
            email = binding.idEdit.text.toString().trim(),
            password = binding.passwordEdit.text.toString().trim()
        )

        val call: Call<ResponseWrapper<ResponseSignIn>> = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueue(object : Callback<ResponseWrapper<ResponseSignIn>> {
            override fun onResponse(
                call: Call<ResponseWrapper<ResponseSignIn>>,
                response: Response<ResponseWrapper<ResponseSignIn>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.data
                    Toast.makeText(
                        this@SignInActivity,
                        "${responseBody?.email}님 반갑습니다. ",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@SignInActivity, ViewPagerActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(
                        this@SignInActivity,
                        "로그인에 실패하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
            override fun onFailure(call: Call<ResponseWrapper<ResponseSignIn>>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })

    }

}