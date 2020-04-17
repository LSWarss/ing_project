package com.lswarss.ing_project.fragments

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lswarss.ing_project.domain.UserWithItem

class UserViewModelFactory(
    private val userWithItem: UserWithItem
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userWithItem) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}