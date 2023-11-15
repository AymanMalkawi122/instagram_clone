package com.example.instagram_clone

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    var loggingInterceptor = HttpLoggingInterceptor()
    val interceptor = Interceptor {
        var request = it.request()
        if(request.headers["Public"] == null)
        request = request.newBuilder().header("Authorization","Bearer ${SharedPrefManager.getString(Constants.tokenAccessesKey)}").build()
        request.newBuilder().method(request.method, request.body).removeHeader("Public")
        it.proceed(request)
    }
    var client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(loggingInterceptor)
    val api:API by lazy{
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        Retrofit.Builder()
            .baseUrl("https://back-end-git-ibraheem-deploy-blog-blender.vercel.app/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }
}