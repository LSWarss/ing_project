package com.lswarss.ing_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lswarss.ing_project.databinding.PostFragmentBinding
import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.domain.UserItem
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.network.PostsApi


/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 * @param onClick a lambda that takes the
 */
class PostsAdapter (private val onClickListener : OnClickListener)
    : ListAdapter<UserWithItem, PostsAdapter.PostsViewHolder>(DiffCallback) {


    class PostsViewHolder(private val binding : PostFragmentBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(post : UserWithItem){
            binding.post = post.post
            binding.user = post.user
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [PostItem]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<UserWithItem>() {
        override fun areItemsTheSame(oldItem: UserWithItem, newItem: UserWithItem): Boolean {
            return oldItem.post.id == newItem.post.id
        }

        override fun areContentsTheSame(oldItem: UserWithItem, newItem: UserWithItem): Boolean {
            return oldItem == newItem
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
        val userWithItem = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(userWithItem)
        }
        holder.bind(userWithItem)
    }


    class OnClickListener(val clickListener: (post:UserWithItem) -> Unit){
        fun onClick(post:UserWithItem) = clickListener(post)
    }

}