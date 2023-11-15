package com.example.instagram_clone

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Response

class RegisterViewModel:ViewModel() {
    private val TAG = "RegisterViewModel"
    private val _registerState = MutableStateFlow<Any?>(null)
    val registerState = _registerState.asStateFlow()
    var registerStateIsValid = false


    suspend fun signup(username: String, password: String, rootView: View) {
        val response = RetrofitInstance.api.createAccount(LoginRequest(username, password))
        Log.d(TAG, "getPosts: $response")
        if (loginResponseValidate(response)) {
            _registerState.value = SharedPrefManager.getString(Constants.tokenAccessesKey)
            registerStateIsValid = true
            SharedPrefManager.setBoolean(Constants.userAuthStatus, true)
            SnackBbarMaker.makeSnackBar(rootView, "Account Created!")
        }
        else{
            SnackBbarMaker.makeSnackBar(rootView, "Something Went Wrong!")
            Log.v(TAG, "create account fail")
        }
    }

    private fun loginResponseValidate(response: Response<LoginResponse>): Boolean {
        return response.isSuccessful
    }

}