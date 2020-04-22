package com.lswarss.ing_project.fragments

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lswarss.ing_project.domain.CommentItem
import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.network.PostsApiStatus
import com.lswarss.ing_project.domain.UserItem
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.network.PostsApi
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class PostsViewModel() : ViewModel() {

    private val _status = MutableLiveData<PostsApiStatus>()

    val status: LiveData<PostsApiStatus>
        get() = _status

    private val _posts = MutableLiveData<List<UserWithItem>>()

    val posts : LiveData<List<UserWithItem>>
        get() = _posts


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPostsProperties()
    }


    private fun getPostsProperties() {
        coroutineScope.launch {
            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = PostsApi.postsService.getPostsAsync()
            var getUserAsync = PostsApi.usersService.getUsersAsync()

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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}

