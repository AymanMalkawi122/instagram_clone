package com.example.instagram_clone

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.instagram_clone.databinding.FragmentRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {
    private val TAG = "RegisterFragment"
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        contaner: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, contaner, false)
        return binding.root
    }


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUp.setOnClickListener {
            var passwordsAreMatching = binding.password.text.toString().equals(binding.confirmPassword.text.toString())
//            passwordsAreMatching = true
            Log.v(TAG,"${binding.password.text}\n${binding.confirmPassword.text}\n${passwordsAreMatching}")
            if (passwordsAreMatching) {
                val job = viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.signup(
                        binding.username.text.toString(),
                        binding.password.text.toString(),
                        activity!!.findViewById(android.R.id.content)
                    )
                    viewModel.registerState.collect {
                        if (viewModel.registerStateIsValid) {
                            CoroutineScope(Dispatchers.Main).launch {
                                navigateToLogin()
                            }
                        }
                    }

                }
            }
            else{
                SnackBbarMaker.makeSnackBar(activity!!.findViewById(android.R.id.content),"Passwords Don't Match!")
            }
        }

    }


    private fun navigateToLogin() {
        val toLogin = RegisterFragmentDirections.navigateToLoginFragment()
        findNavController().navigate(toLogin)
    }
}