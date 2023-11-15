package com.example.instagram_clone

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _postsState = MutableStateFlow<List<Post>>(listOf())
    val postsState = _postsState.asStateFlow()

    init {
        viewModelScope.launch {
            getPosts().collect { list ->
                Log.d("getPostsLog", "collect: $list")
                list.body()?.let {
                    _postsState.value = it
                }
            }

        }
    }

    private suspend fun getPosts() = flow {
        val response = RetrofitInstance.api.getPosts()
        Log.d("getPostsLog", "getPosts: $response")
        emit(response)
    }
}