package com.lswarss.ing_project.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lswarss.ing_project.Coroutines
import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.repositories.PostsRepository
import kotlinx.coroutines.Job

class PostsViewModel(private val repository: PostsRepository) : ViewModel() {

    private lateinit var job : Job

    private val _posts = MutableLiveData<List<PostItem>>()
    val posts : LiveData<List<PostItem>>
        get() = _posts

     fun getPosts(){
        job = Coroutines.ioThenMain(
            {repository.getPosts()},
            { _posts.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}
