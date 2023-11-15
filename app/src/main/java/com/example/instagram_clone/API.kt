package com.example.instagram_clone

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface API {
    @GET("/api/v1/posts/")
    suspend fun getPosts(): Response<List<Post>>


    @POST("/api/token/")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @Headers("Public: true")
    @POST("/api/v1/accounts/register")
    suspend fun createAccount(@Body request: LoginRequest): Response<LoginResponse>
}