package com.lswarss.ing_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lswarss.ing_project.R
import com.lswarss.ing_project.databinding.PostFragmentBinding
import com.lswarss.ing_project.domain.PostItem

class PostsAdapter (private val posts: List<PostItem>) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    inner class PostsViewHolder(val postFragmentBinding : PostFragmentBinding) : RecyclerView.ViewHolder(postFragmentBinding.root)

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
    }


}