package com.example.instagram_clone

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram_clone.databinding.ComponentPostBinding

class PostAdapter(
    val arrayList: ArrayList<Post> = arrayListOf(),
    val fragmentInstance: Fragment,
    val commentOnClickHandler: (Post)->Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    val TAG = "PostAdapter"


    inner class PostViewHolder(val binding: ComponentPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ComponentPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = arrayList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.apply {
            val currentPost = arrayList[position]
            postTitle.text = currentPost.title
            postContent.text = currentPost.content
            userHandle.text =
                currentPost.autherId?.first_name + " " + currentPost.autherId?.last_name
            userName.text = "@" + currentPost.autherId?.username
            val url = "${Constants.cloudinaryBaseUrl}${currentPost.autherId?.profile_pic}"
            Glide.with(fragmentInstance).load(url).into(userPic)
            commentButton.setOnClickListener{
                commentOnClickHandler(currentPost)
            }
        }
    }

    class PostsDiffCallback(
        private val oldList: List<Post>,
        private val newList: List<Post>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}