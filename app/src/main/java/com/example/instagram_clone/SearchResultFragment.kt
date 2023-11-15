package com.example.instagram_clone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs


class SearchResultFragment : Fragment() {
    private val TAG = "SearchResultFragment"
    private lateinit var searchResult: TextView
    private val args: SearchResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        contaner: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {

        val view = inflater.inflate(R.layout.fragment_search_result,contaner,false)
        val queryText = args.queryText
        searchResult = view.findViewById(R.id.result)
        Log.v(TAG, "debug")
        searchResult.text = queryText

        return view
    }


}