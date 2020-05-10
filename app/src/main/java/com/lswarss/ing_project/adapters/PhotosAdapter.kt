package com.lswarss.ing_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lswarss.ing_project.R
import com.lswarss.ing_project.databinding.ItemPhotoBinding

import com.lswarss.ing_project.domain.PhotoItem
import com.lswarss.ing_project.domain.UserWithItem

class PhotosAdapter (val onClickListener : OnClickListener)
    : ListAdapter<PhotoItem, PhotosAdapter.PhotosViewHolder>(DiffCallback) {

    class PhotosViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: PhotoItem) {
            binding.photo = photo
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photo = getItem(position)
        holder.itemView.setOnClickListener {
            holder.itemView.startAnimation(AnimationUtils.loadAnimation(it.context, R.anim.image_animation))
            onClickListener.onClick(photo)
        }
        holder.bind(photo)
    }

    class OnClickListener(val clickListener: (photo:PhotoItem) -> Unit) {
        fun onClick(photo:PhotoItem) = clickListener(photo)
    }

}