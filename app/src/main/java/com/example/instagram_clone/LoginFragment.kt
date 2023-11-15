package com.example.instagram_clone

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.instagram_clone.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private val TAG = "LoginFragment"
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        contaner: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, contaner, false)
        return binding.root
    }


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            val job = viewLifecycleOwner.lifecycleScope.launch {
                viewModel.login(binding.username.text.toString(),
                    binding.password.text.toString(),
                    this@LoginFragment.activity!!.findViewById(android.R.id.content))

                viewModel.loginState.collect {
                    if (viewModel.loginStateIsValid) {
                        CoroutineScope(Dispatchers.Main).launch {
                            navigateToGlobalSection()
                        }
                    }
                }

            }
        }

        binding.createAccount.setOnClickListener {
            navigateToCreateAccount()
        }
    }


    private fun navigateToCreateAccount() {
        val toSignUp = LoginFragmentDirections.navigateToRegisterFragment()
        findNavController().navigate(toSignUp)
    }

    private fun navigateToGlobalSection() {
        val toGlobal = LoginFragmentDirections.navigateToGlobalFragment()
        findNavController().navigate(toGlobal)
    }
}