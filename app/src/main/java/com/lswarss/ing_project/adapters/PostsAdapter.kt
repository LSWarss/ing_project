package com.lswarss.ing_project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lswarss.ing_project.databinding.ItemPostBinding

import com.lswarss.ing_project.domain.UserWithItem
import kotlinx.android.synthetic.main.item_post.view.*


class PostsAdapter ( val onUserListener: OnUserListener, val onCommentsListener: OnCommentsListener, val onSaveListener : OnSaveListener)
    : ListAdapter<UserWithItem, PostsAdapter.PostsViewHolder>(DiffCallback) {

    class PostsViewHolder(private val binding : ItemPostBinding)
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
     * Allows the RecyclerView to determine which items have changed when the [List] of [UserWithItem]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<UserWithItem>() {
        override fun areItemsTheSame(oldItem: UserWithItem, newItem: UserWithItem): Boolean {
            return oldItem.post.id  == newItem.post.id
        }

        override fun areContentsTheSame(oldItem: UserWithItem, newItem: UserWithItem): Boolean {
            return oldItem == newItem
        }
    }


    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context)))
    }
    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val userWithItem = getItem(position)
        holder.bind(userWithItem)
        holder.itemView.post_user.setOnClickListener {
            onUserListener.onClick(userWithItem)
        }
        holder.itemView.post_comment.setOnClickListener {
            onCommentsListener.onClick(userWithItem)
        }
        holder.itemView.savePostsView.setOnClickListener {
            onSaveListener.onClick(userWithItem)
        }
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [UserWithItem]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [UserWithItem]
     */
    class OnUserListener(val clickListener: (userWithItem:UserWithItem) -> Unit) {
        fun onClick(userWithItem:UserWithItem) = clickListener(userWithItem)
    }

    class OnCommentsListener(val clickListener: (userWithItem:UserWithItem) -> Unit){
        fun onClick(userWithItem:UserWithItem) = clickListener(userWithItem)
    }

    class OnSaveListener(val clickListener : (userWithItem:UserWithItem) -> Unit){
        fun onClick(userWithItem: UserWithItem) = clickListener(userWithItem)
    }



}