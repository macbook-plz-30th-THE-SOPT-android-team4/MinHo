package com.example.soptexampleproject.week1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.soptexampleproject.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signBtn.setOnClickListener {

            if(binding.nameEdit.text.isNotBlank()&&binding.idEdit.text.isNotBlank()&&binding.passwordEdit.text.isNotBlank()){
                val intent = Intent(this, SignInActivity::class.java).apply {
                    putExtra("id", binding.idEdit.text.toString())
                    putExtra("password", binding.passwordEdit.text.toString())
                }
                setResult(RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this,"입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}