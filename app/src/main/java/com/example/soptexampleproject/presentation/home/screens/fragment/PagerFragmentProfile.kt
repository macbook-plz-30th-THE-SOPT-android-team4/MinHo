package com.example.soptexampleproject.week3.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.soptexampleproject.R
import com.example.soptexampleproject.databinding.FragmentPagerProfileBinding
import com.example.soptexampleproject.presentation.home.screens.PreferenceActivity
import com.example.soptexampleproject.presentation.home.screens.fragment.MySettingFragment
import com.example.soptexampleproject.presentation.home.screens.fragment.profilechildfragment.Follwer
import com.example.soptexampleproject.presentation.home.screens.fragment.profilechildfragment.Repo
import com.example.soptexampleproject.util.SOPTSharedPreference

class PagerFragmentProfile : Fragment() {

    private lateinit var _binding: FragmentPagerProfileBinding
    private val binding get() = _binding

    var isEnabled = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagerProfileBinding.inflate(layoutInflater,container,false)
        init()
        return binding.root
    }


    private fun init() {
        val fragment = Follwer()
        val fragment2 = Repo()
        binding.ivSetting.setOnClickListener {
            val intent = Intent(requireContext(), PreferenceActivity::class.java)
            startActivity(intent)
        }
        binding.btnFollower.isEnabled = isEnabled
        binding.btnRepo.isEnabled = !isEnabled

        childFragmentManager.beginTransaction()
            .add(R.id.fragContainerGithub, fragment)
            .commit()
        binding.btnFollower.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragContainerGithub, fragment)
                .commit()
            isEnabled=false
            binding.btnFollower.isEnabled = isEnabled
            binding.btnRepo.isEnabled = !isEnabled
        }
        binding.btnRepo.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragContainerGithub, fragment2)
                .commit()
            isEnabled=true
            binding.btnFollower.isEnabled = isEnabled
            binding.btnRepo.isEnabled = !isEnabled
        }
    }

}