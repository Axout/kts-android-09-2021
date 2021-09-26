package com.axout.kts_android_09_2021

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.axout.kts_android_09_2021.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private var isValidEmail = false

    private val viewModel: CounterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with (binding.etEmailAddress) {
            addTextChangedListener {

                viewModel.setData(text.length)

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

        viewModel.state.observe(this) { state ->
            binding.tvCounter.text = state.counter.toString()
        }
    }
}