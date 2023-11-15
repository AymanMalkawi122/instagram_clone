package com.example.instagram_clone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController


class SearchFragment : Fragment() {

    private val TAG = "SearchFragment"
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        contaner: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {

        val view = inflater.inflate(R.layout.fragment_search,contaner,false)
        searchView = view.findViewById(R.id.searchBar)
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val action = SearchFragmentDirections.navigateToSearchResult(query)
                Log.v(TAG, query.toString())
                findNavController().navigate(action)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return view
    }

}

