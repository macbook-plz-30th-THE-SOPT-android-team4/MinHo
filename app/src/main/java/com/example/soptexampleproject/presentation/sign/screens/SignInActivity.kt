package com.example.soptexampleproject.presentation.sign.screens

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.soptexampleproject.databinding.ActivitySignInBinding
import com.example.soptexampleproject.data.remote.github.models.ResponseFollowing
import com.example.soptexampleproject.presentation.home.screens.ViewPagerActivity
import com.example.soptexampleproject.data.remote.sign.models.RequestSignIn
import com.example.soptexampleproject.data.remote.sign.models.ResponseSignIn
import com.example.soptexampleproject.data.remote.ServiceCreator
import com.example.soptexampleproject.util.ResponseWrapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

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
                    binding.idEdit.text = (it.data?.getStringExtra("id") ?: "") as Editable?
                    binding.passwordEdit.text =
                        (it.data?.getStringExtra("password") ?: "") as Editable?
                }
            }
    }

    private fun initEvent() {
        binding.loginBtn.setOnClickListener {
            if (binding.idEdit.text.isNotBlank() && binding.passwordEdit.text.isNotBlank()) {
                loginNetWork()
            } else {
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

        val call: Call<ResponseWrapper<ResponseSignIn>> =
            ServiceCreator.soptService.postLogin(requestSignIn)
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<ResponseWrapper<ResponseSignIn>> =
                call.execute()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.data
                    Toast.makeText(
                        this@SignInActivity,
                        "${responseBody?.email}님 반갑습니다. ",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@SignInActivity, ViewPagerActivity::class.java).apply {
                        putExtra("username", responseBody?.email)
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignInActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}