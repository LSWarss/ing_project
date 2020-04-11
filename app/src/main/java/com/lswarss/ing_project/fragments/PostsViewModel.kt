package com.lswarss.ing_project.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lswarss.ing_project.domain.CommentItem
import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.domain.UserItem
import com.lswarss.ing_project.network.PostsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class PostsApiStatus { LOADING, ERROR, DONE }

class PostsViewModel() : ViewModel() {

    private val _status = MutableLiveData<PostsApiStatus>()

    val status: LiveData<PostsApiStatus>
        get() = _status

    private val _posts = MutableLiveData<List<PostItem>>()

    val posts : LiveData<List<PostItem>>
        get() = _posts

    private val _users = MutableLiveData<List<UserItem>>()

    val users : LiveData<List<UserItem>>
        get() = _users

    private val _navigateToSelectedUser = MutableLiveData<UserItem>()

    val navigateToSelectedUser : LiveData<UserItem>
        get() = _navigateToSelectedUser

    private val _navigateToSelectedPostComments = MutableLiveData<List<CommentItem>>()

    val navigateToSelectedPostCOmment : LiveData<List<CommentItem>>
        get() = _navigateToSelectedPostComments

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPostsProperties()
    }


    private fun getPostsProperties() {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = PostsApi.retrofitService.getPostsAsync()
            try {
                _status.value = PostsApiStatus.LOADING
                // this will run on a thread managed by Retrofit
                val listResult = getPropertiesDeferred.await()
                _status.value = PostsApiStatus.DONE
                _posts.value = listResult
            } catch (e: Exception) {
                _status.value = PostsApiStatus.ERROR
                _posts.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
