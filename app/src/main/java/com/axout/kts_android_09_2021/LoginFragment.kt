package com.axout.kts_android_09_2021

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axout.kts_android_09_2021.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: CounterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.etEmailAddress.addTextChangedListener {
            viewModel.validate(
                binding.etEmailAddress.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        binding.etPassword.addTextChangedListener {
            viewModel.validate(
                binding.etEmailAddress.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        binding.btLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
            findNavController().navigate(action)
        }

        viewModel.state.observe(this) { state ->
            if (state.valid) {
                binding.ivValid.visibility = View.VISIBLE
                binding.btLogin.isEnabled = true
            } else {
                binding.ivValid.visibility = View.INVISIBLE
                binding.btLogin.isEnabled = false
            }
        }
    }
}