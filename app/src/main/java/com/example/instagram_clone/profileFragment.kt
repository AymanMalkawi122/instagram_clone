package com.example.instagram_clone

import android.app.UiModeManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment


class profileFragment : Fragment() {

    private val TAG = "profileFragment"
    private lateinit var switchView: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater,
        contaner: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {

        val view = inflater.inflate(R.layout.fragment_profile,contaner,false)
        switchView = view.findViewById(R.id.themeSwitch)
        switchView.setOnClickListener{
            if(switchView.isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                Toast.makeText(context,"Dark Mode",Toast.LENGTH_SHORT).show()
            }

            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(context,"Light Mode",Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

}