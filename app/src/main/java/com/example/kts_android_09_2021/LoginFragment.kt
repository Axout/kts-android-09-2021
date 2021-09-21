package com.example.kts_android_09_2021

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.kts_android_09_2021.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private var isValidEmail = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with (binding.etEmailAddress) {
            addTextChangedListener {
                isValidEmail = Patterns.EMAIL_ADDRESS.matcher(text).matches()
                if (isValidEmail) {
                    backgroundTintList = resources.getColorStateList(R.color.green)
                }
            }
        }

        with (binding.etPassword) {
            addTextChangedListener {
                if (text.length >= 8 && isValidEmail) {
                    backgroundTintList = resources.getColorStateList(R.color.green)
                    binding.btLogin.isEnabled = true
                }
            }
        }

        binding.btLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
            findNavController().navigate(action)
        }
    }
}