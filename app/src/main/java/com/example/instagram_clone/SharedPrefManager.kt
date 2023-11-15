package com.example.instagram_clone

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

@SuppressLint("StaticFieldLeak")
object SharedPrefManager{
    private val TAG = "SharedPrefManager"
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    fun init(context: Context){
        Log.v(TAG, "init launched")
        this.context = context
        this.sharedPreferences = context.getSharedPreferences(Constants.storageKey,Context.MODE_PRIVATE)
        this.sharedPreferencesEditor = sharedPreferences.edit()
    }

    fun getString(key: String, defValue: String = "undefined"):String?{
        return sharedPreferences.getString(key,defValue)
    }

    fun setString(key: String, value: String){
        sharedPreferencesEditor.apply{
         putString(key, value)
        }.apply()
    }

    fun getBoolean(key: String, defValue: Boolean = false): Boolean {
        Log.v(TAG, "${sharedPreferences}")
        return sharedPreferences.getBoolean(key,defValue)
    }

    fun setBoolean(key: String, value: Boolean){
        sharedPreferencesEditor.apply{
            putBoolean(key, value)
        }.apply()
    }

}