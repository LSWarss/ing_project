package com.lswarss.ing_project.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.lswarss.ing_project.R
import com.lswarss.ing_project.domain.CommentItem
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.network.PostsApiStatus


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UserWithItem>?){
    val adapter = recyclerView.adapter as PostsAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData2")
fun bindRecyclerView2(recyclerView: RecyclerView, data: List<CommentItem>?){
    val adapter = recyclerView.adapter as CommentsAdapter
    adapter.submitList(data)
}


@BindingAdapter("postApiStatus")
fun bindStatus(statusImageView: ImageView, status: PostsApiStatus?) {
    when (status) {
        PostsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PostsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        PostsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

