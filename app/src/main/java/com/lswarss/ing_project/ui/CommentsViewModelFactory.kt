package com.lswarss.ing_project.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lswarss.ing_project.domain.UserWithItem

class CommentsViewModelFactory(
    private val userWithItem: UserWithItem,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            return CommentsViewModel(
                userWithItem,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}