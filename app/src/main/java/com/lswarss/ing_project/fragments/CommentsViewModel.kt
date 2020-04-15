package com.lswarss.ing_project.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lswarss.ing_project.domain.CommentItem
import com.lswarss.ing_project.network.PostsApiStatus
import com.lswarss.ing_project.network.PostsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class CommentsViewModel() : ViewModel(){

    private val _status = MutableLiveData<PostsApiStatus>()

    val status: LiveData<PostsApiStatus>
        get() = _status

    private val _comments = MutableLiveData<List<CommentItem>>()

    val comments: LiveData<List<CommentItem>>
        get() = _comments

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private fun getComments() {
        coroutineScope.launch {
            var getCommentsAsync = PostsApi.commentsService.getCommentsAsync()

            try {
                _status.value = PostsApiStatus.LOADING
                val comments = getCommentsAsync.await()
                _status.value = PostsApiStatus.DONE
                _comments.value = comments
            }
            catch (e : Exception){
                _status.value = PostsApiStatus.ERROR
                _comments.value = ArrayList()
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}