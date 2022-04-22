package com.example.soptexampleproject.week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.ActivityParentBinding

class ParentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentBinding
    private var position = FIRST_POSITION
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        var fragment1 = BlankFragment()
        var fragment2 = BlankFragment2()


        supportFragmentManager.beginTransaction()
            .add(R.id.frag_container, fragment1)
            .commit()
        binding.btnFrag.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }

        })
        binding.btnFrag.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            when (position) {
                FIRST_POSITION -> {
                    tran.replace(R.id.frag_container, fragment2)

                    position = SECOND_POSITION
                }
                SECOND_POSITION -> {
                    tran.replace(R.id.frag_container, fragment1)
                    position = FIRST_POSITION
                }
            }
            tran.commit()

        }
    }

    companion object {
        const val FIRST_POSITION = 1
        const val SECOND_POSITION = 2
    }
}