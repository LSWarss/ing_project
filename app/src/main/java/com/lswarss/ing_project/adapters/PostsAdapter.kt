package com.lswarss.ing_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lswarss.ing_project.MainActivity
import com.lswarss.ing_project.R
import com.lswarss.ing_project.databinding.PostFragmentBinding
import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.util.RecyclerViewClickListener

class PostsAdapter (
    private val posts: List<PostItem>,
    private val listener : RecyclerViewClickListener)
    : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    inner class PostsViewHolder(val postFragmentBinding : PostFragmentBinding)
        : RecyclerView.ViewHolder(postFragmentBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.post_fragment,
            parent,
            false
        )
    )

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.postFragmentBinding.post = posts[position]
        holder.postFragmentBinding.postUser.setOnClickListener{
            listener.onRecyclerViewItemClicked(holder.postFragmentBinding.postUser, posts[position])
        }
        holder.postFragmentBinding.postComment.setOnClickListener{
            listener.onRecyclerViewItemClicked(holder.postFragmentBinding.postComment, posts[position])
        }
    }


}