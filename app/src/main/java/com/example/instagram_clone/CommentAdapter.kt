package com.example.instagram_clone

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram_clone.databinding.ComponentCommentBinding

class CommentAdapter(
    private var post: Post,
    val fragmentInstance: Fragment
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    val TAG = "CommentAdapter"

    inner class CommentViewHolder(val binding: ComponentCommentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            ComponentCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = post.comments?.size as Int

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.binding.apply {
            val currentComment = post.comments?.get(position)
            commentContent.text = currentComment?.content
            userName.text = currentComment?.user_id?.username
            var url = "${Constants.cloudinaryBaseUrl}${currentComment?.user_id?.profile_pic}"
            Glide.with(fragmentInstance).load(url).into(userPic)
        }
    }

}
