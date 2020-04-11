package com.lswarss.ing_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lswarss.ing_project.databinding.PostFragmentBinding
import com.lswarss.ing_project.domain.PostItem


/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 * @param onClick a lambda that takes the
 */
class PostsAdapter (val onClickListener : OnClickListener)
    : ListAdapter<PostItem, PostsAdapter.PostsViewHolder>(DiffCallback) {


    class PostsViewHolder(private val binding : PostFragmentBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(post : PostItem){
            binding.post = post
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [PostItem]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<PostItem>() {
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(PostFragmentBinding.inflate(LayoutInflater.from(parent.context)))
    }
    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(post)
        }
        holder.bind(post)
    }


    class OnClickListener(val clickListener: (post:PostItem) -> Unit){
        fun onClick(post:PostItem) = clickListener(post)
    }

}