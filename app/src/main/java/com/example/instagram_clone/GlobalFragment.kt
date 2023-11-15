package com.example.instagram_clone


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class GlobalFragment : Fragment() {
    private val TAG = "GlobalFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        contaner: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_global, contaner, false)
        return view
    }


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userIsAuthorized = SharedPrefManager.getBoolean(Constants.userAuthStatus,false)



        if (userIsAuthorized){
            val actionToAuthorizedSection = GlobalFragmentDirections.navigateToPostListFragment()
            findNavController().navigate(actionToAuthorizedSection)
        }
        else{
            val actionToUnauthorizedSection = GlobalFragmentDirections.navigateToLoginFragment()
            findNavController().navigate(actionToUnauthorizedSection)
        }
    }
}


