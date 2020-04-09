package com.lswarss.ing_project.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lswarss.ing_project.Coroutines
import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.domain.UserItem
import com.lswarss.ing_project.repositories.PostsRepository
import kotlinx.coroutines.Job

class PostsViewModel(private val repository: PostsRepository) : ViewModel() {

    private lateinit var job : Job

    private val _posts = MutableLiveData<List<PostItem>>()
    val posts : LiveData<List<PostItem>>
        get() = _posts

    private val _users = MutableLiveData<List<UserItem>>()
    val users : LiveData<List<UserItem>>
        get() = _users


     fun getPosts(){
        job = Coroutines.ioThenMain(
            {repository.getPosts()},
            { _posts.value = it }
        )
    }

    fun getUsers(){
        job = Coroutines.ioThenMain(
            {repository.getUsers()},
            {_users.value = it}
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}
