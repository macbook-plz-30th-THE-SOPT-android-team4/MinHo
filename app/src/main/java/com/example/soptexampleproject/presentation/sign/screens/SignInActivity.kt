package com.example.soptexampleproject.presentation.sign.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.soptexampleproject.databinding.ActivityMainBinding
import com.example.soptexampleproject.week2.RecyclerViewActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getResultText =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    binding.idEdit.text.append(it.data?.getStringExtra("id") ?: "")
                    binding.passwordEdit.text.append(it.data?.getStringExtra("password") ?: "")
                } else {

                }
            }
        binding.loginBtn.setOnClickListener {
            if (binding.idEdit.text.isNotBlank() && binding.passwordEdit.text.isNotBlank()) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RecyclerViewActivity::class.java)
                startActivity(intent)
            }
        }
        binding.signupBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            getResultText.launch(intent)
        }
    }

}