package com.example.instagram_clone

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram_clone.databinding.FragmentCommentBottomSheetBinding
import com.example.instagram_clone.databinding.FragmentPostListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class PostListFragment : Fragment() {

    private val TAG = "profileFragment"
    private val viewModel by viewModels<HomeViewModel>()
    private val postAdapter: PostAdapter = PostAdapter(arrayListOf(),this){ comments ->
        commentOnClick(comments)
    }
    private lateinit var binding: FragmentPostListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.postsState.collect {
                    CoroutineScope(Dispatchers.Main).launch {
                        val diffCallback = PostAdapter.PostsDiffCallback(postAdapter.arrayList, it)
                        val result = DiffUtil.calculateDiff(diffCallback)
                        postAdapter.arrayList.clear()
                        postAdapter.arrayList.addAll(it)
                        result.dispatchUpdatesTo(postAdapter)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() = binding.postListRecyclerView.apply {
        layoutManager = LinearLayoutManager(requireActivity())
        binding.postListRecyclerView.adapter = postAdapter
    }

    fun commentOnClick(post: Post){
        val bottomSheetFragment = RecyclerViewBottomSheet()
        val bundle = Bundle()
        bundle.putParcelable(Constants.ArgumentNames.commentList, post)
        bottomSheetFragment.arguments = bundle

        val fragmentManager = this.parentFragmentManager
        bottomSheetFragment.show(fragmentManager, RecyclerViewBottomSheet.TAG)
    }
}

