package com.axout.kts_android_09_2021

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private var isValidEmail = false

    private val viewModel: CounterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.etEmailAddress.addTextChangedListener {
            viewModel.setData(binding.etEmailAddress.text.length)
            isValidEmail = Patterns.EMAIL_ADDRESS.matcher(binding.etEmailAddress.text).matches()
            if (isValidEmail) {
                binding.etEmailAddress.backgroundTintList = resources.getColorStateList(R.color.green)
            }
        }

        binding.etPassword.addTextChangedListener {
            if (binding.etPassword.text.length >= 8 && isValidEmail) {
                binding.etPassword.backgroundTintList = resources.getColorStateList(R.color.green)
                binding.btLogin.isEnabled = true
            }
        }

        binding.btLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
            findNavController().navigate(action)
        }

        viewModel.state.observe(this) { state ->
            binding.tvCounter.text = state.counter.toString()
        }
    }
}