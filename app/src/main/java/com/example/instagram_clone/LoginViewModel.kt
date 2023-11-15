package com.example.instagram_clone

import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val TAG = "RegistrationViewModel"
    private val _loginState = MutableStateFlow<Any?>(null)
    val loginState = _loginState.asStateFlow()
    var loginStateIsValid = false


    suspend fun login(username: String, password: String, rootView:View) {
        val response = RetrofitInstance.api.login(LoginRequest(username, password))
        Log.d(TAG, "getPosts: $response")
        if (loginResponseValidate(response)) {
            storeLoginStatus(response)
            _loginState.value = SharedPrefManager.getString(Constants.tokenAccessesKey)
            loginStateIsValid = true
            SharedPrefManager.setBoolean(Constants.userAuthStatus, true)
        }
        else{
            SnackBbarMaker.makeSnackBar(rootView, "Something Went Wrong!")
            Log.v(TAG, "create account fail")
        }
    }

    private fun loginResponseValidate(response: Response<LoginResponse>): Boolean {
        return response.isSuccessful
    }

    private fun storeLoginStatus(response: Response<LoginResponse>) {
        response.body()?.let { SharedPrefManager.setString(Constants.tokenAccessesKey, it.access) }
    }
}