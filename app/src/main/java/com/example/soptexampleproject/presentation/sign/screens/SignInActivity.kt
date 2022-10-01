package com.example.soptexampleproject.presentation.sign.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.soptexampleproject.data.model.db.User
import com.example.soptexampleproject.data.model.db.UserDatabase
import com.example.soptexampleproject.data.model.db.UserRepository
import com.example.soptexampleproject.databinding.ActivitySignInBinding
import com.example.soptexampleproject.presentation.home.screens.ViewPagerActivity
import com.example.soptexampleproject.data.remote.sign.models.RequestSignIn
import com.example.soptexampleproject.data.remote.ServiceCreator
import com.example.soptexampleproject.util.SOPTSharedPreference
import kotlinx.coroutines.*

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    private lateinit var repository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = UserDatabase.getInstance(this).UserDAO()
        repository = UserRepository(dao)
        initEvent()
        getResultText =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    binding.idEdit.text.append((it.data?.getStringExtra("id") ?: ""))
                    binding.passwordEdit.text.append((it.data?.getStringExtra("password") ?: ""))
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
        initAutoLogin()
    }

    private fun initAutoLogin() {

        /*lifecycleScope.launch {
            val user = repository.getUser(0)
            if(user != null) {
                if(user.autoLogin){
                    val intent = Intent(this@SignInActivity, ViewPagerActivity::class.java)
                    startActivity(intent)
                }
            }else{
                repository.insertUser(User(0, "","",false))
            }
        }*/
        if (SOPTSharedPreference.getAutoLogin(this)) {
            val intent = Intent(this@SignInActivity, ViewPagerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginNetWork() {
        val requestSignIn = RequestSignIn(
            email = binding.idEdit.text.toString().trim(),
            password = binding.passwordEdit.text.toString().trim()
        )
        val response = CoroutineScope(Dispatchers.IO).async {
            ServiceCreator.soptService.postLogin(requestSignIn)
        }
        CoroutineScope(Dispatchers.Main).launch {
            val responseBody = response.await()
            if (responseBody.isSuccessful) {
                Toast.makeText(
                    this@SignInActivity,
                    "${responseBody.body()?.data?.email}님 반갑습니다. ",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@SignInActivity, ViewPagerActivity::class.java).apply {
                    putExtra("username", responseBody.body()?.data?.email)
                }
                /*repository.updateUser(
                    User(
                        id = 0,
                        userName = binding.idEdit.text.toString(),
                        userPassword = binding.passwordEdit.text.toString(),
                        autoLogin = binding.cbAutoLogin.isChecked
                    )
                )*/
                SOPTSharedPreference.setAutoLogin(applicationContext, binding.cbAutoLogin.isChecked)
                startActivity(intent)
            } else {
                Toast.makeText(this@SignInActivity, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

