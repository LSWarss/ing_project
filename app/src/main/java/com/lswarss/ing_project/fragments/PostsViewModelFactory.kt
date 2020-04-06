package com.lswarss.ing_project.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lswarss.ing_project.repositories.PostsRepository

@Suppress("UNCHECKED_CAST")
class PostsViewModelFactory(private val repository: PostsRepository) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(repository) as T
    }
}