package com.lswarss.ing_project.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.network.PostsApiStatus
import com.lswarss.ing_project.network.RetrofitInstance
import kotlinx.coroutines.launch

class PostsViewModel() : ViewModel() {

    private val _status = MutableLiveData<PostsApiStatus>()

    val status: LiveData<PostsApiStatus>
        get() = _status

    private val _posts = MutableLiveData<List<UserWithItem>>()

    val posts : LiveData<List<UserWithItem>>
        get() = _posts

    var postPagingStart = 0;
    var postPagingLimit = 10;

    private val _navigateToSelectedUser = MutableLiveData<UserWithItem>()

    val navigateToSelectedUser : LiveData<UserWithItem>
        get() = _navigateToSelectedUser

    private  val _navigateToSelectedComments = MutableLiveData<UserWithItem>()

    val navigateToSelectedComments : LiveData<UserWithItem>
        get() = _navigateToSelectedComments

    init {
        getPostsProperties()
    }

     fun getPostsProperties() {
        viewModelScope.launch {
            // Get the Deferred object for our Retrofit request
            val getPropertiesDeferred = RetrofitInstance.api.getPostsAsync(postPagingStart, postPagingLimit)
            val getUserAsync = RetrofitInstance.api.getUsersAsync()

            try {
                _status.value = PostsApiStatus.LOADING
                // this will run on a thread managed by Retrofit
                val users = getUserAsync.await()
                val listResult = getPropertiesDeferred
                    .await()
                    .map{
                        val currentUser = users
                            .find { user -> user.id == it.userId}
                            ?: throw IllegalStateException("User not found")
                        UserWithItem(currentUser, it)
                    }
                _status.value = PostsApiStatus.DONE
                _posts.value = listResult
            } catch (e: Exception) {
                _status.value = PostsApiStatus.ERROR
                _posts.value = ArrayList()
            }
        }
    }

    fun displayUserDetail(userWithItem: UserWithItem){
        _navigateToSelectedUser.value = userWithItem
    }

    fun displayCommentsForPost(userWithItem: UserWithItem){
        _navigateToSelectedComments.value = userWithItem
    }

    fun displayUserDetailComplete(){
        _navigateToSelectedUser.value = null
    }

    fun displayCommentsForPostComplete(){
        _navigateToSelectedComments.value = null
    }
}

