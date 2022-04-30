package com.example.soptexampleproject.week3.Fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.FragmentPagerSettingBinding

class PagerFragmentSetting : Fragment() {
    private lateinit var _binding: FragmentPagerSettingBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPagerSettingBinding.inflate(inflater, container, false)
        bindingView()
        return binding.root
    }

    private fun bindingView() {
        binding.btnUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                type = "image/*"
                addCategory(Intent.CATEGORY_OPENABLE)
            }
            // Only the system receives the ACTION_OPEN_DOCUMENT, so no need to test.
            startActivityForResult(intent, REQUEST_IMAGE_OPEN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == Activity.RESULT_OK) {
            val fullPhotoUri: Uri? = data?.data
            if (fullPhotoUri != null) {
                binding.imageViewCoil.load(fullPhotoUri)
            }
        }
    }

    companion object {
        const val REQUEST_IMAGE_OPEN = 1
    }
}