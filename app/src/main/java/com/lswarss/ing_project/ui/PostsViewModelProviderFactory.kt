package com.lswarss.ing_project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lswarss.ing_project.repositories.PostsRepository

class PostsViewModelProviderFactory(private val postsRepository: PostsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(postsRepository) as T
    }
}