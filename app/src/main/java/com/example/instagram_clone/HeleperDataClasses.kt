package com.example.instagram_clone

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    val refresh:String = "",
    var access:String = "",
)

data class LoginRequest (
    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String
)
