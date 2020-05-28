package com.lswarss.ing_project.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lswarss.ing_project.domain.CommentItem
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.network.PostsApiStatus
import com.lswarss.ing_project.network.RetrofitInstance
import kotlinx.coroutines.launch

class CommentsViewModel(post: UserWithItem, app: Application) : AndroidViewModel(app) {

    private val _selectedComments = MutableLiveData<List<CommentItem>>()
    val selectedComments: LiveData<List<CommentItem>>
        get() = _selectedComments

    private val _status = MutableLiveData<PostsApiStatus>()

    val status: LiveData<PostsApiStatus>
        get() = _status


    init {
        getComments(post.post.id)
    }

    fun getComments(postId: Int) {
        viewModelScope.launch {
            // Get the Deferred object for our Retrofit request
            var getComments = RetrofitInstance.api.getCommentsAsyncWithId(postId)

            try {
                _status.value = PostsApiStatus.LOADING
                // this will run on a thread managed by Retrofit
                val comments = getComments.await()
                _status.value = PostsApiStatus.DONE
                _selectedComments.value = comments
            } catch (e: Exception) {
                _status.value = PostsApiStatus.ERROR
                _selectedComments.value = ArrayList()
            }
        }
    }


}