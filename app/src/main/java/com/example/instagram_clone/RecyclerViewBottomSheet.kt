package com.example.instagram_clone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram_clone.databinding.FragmentCommentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RecyclerViewBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentCommentBottomSheetBinding
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentBottomSheetBinding.inflate(layoutInflater, container, false)
        Log.v(TAG,"args $arguments")
        var post = arguments?.let { RecyclerViewBottomSheetArgs.fromBundle(it).post }
        commentAdapter = post?.let { CommentAdapter(it,this@RecyclerViewBottomSheet) }!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.commentListRecyclerView.apply {
        layoutManager = LinearLayoutManager(requireActivity())
        adapter = commentAdapter
    }

    companion object {
        val TAG = "RecyclerViewBottomSheet"
    }
}

