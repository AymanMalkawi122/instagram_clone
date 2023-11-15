package com.example.instagram_clone

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private val TAG = "MainActivityViewModel"


    fun logout() {
        SharedPrefManager.setString(Constants.tokenAccessesKey,"undefined")
        SharedPrefManager.setBoolean(Constants.userAuthStatus, false)
    }

}