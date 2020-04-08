package com.lswarss.ing_project.util

import android.view.View
import com.lswarss.ing_project.domain.PostItem

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClicked(view: View, post: PostItem)
}