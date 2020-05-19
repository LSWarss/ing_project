package com.lswarss.ing_project.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lswarss.ing_project.db.UserWithItemDao
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.network.PostsApiStatus
import com.lswarss.ing_project.network.RetrofitInstance
import com.lswarss.ing_project.repositories.PostsRepository
import kotlinx.coroutines.launch

class PostsViewModel(val postsRepository: PostsRepository) : ViewModel() {

    private val _status = MutableLiveData<PostsApiStatus>()

    val status: LiveData<PostsApiStatus>
        get() = _status

    private val _posts = MutableLiveData<List<UserWithItem>>()

    val posts : LiveData<List<UserWithItem>>
        get() = _posts

    private val _searchedPosts = MutableLiveData<List<UserWithItem>>()

    val searchedPosts : LiveData<List<UserWithItem>>
        get() = _searchedPosts

    var postPagingStart = 0;
    var postPagingLimit = 10;

    private val _navigateToSelectedUser = MutableLiveData<UserWithItem>()

    val navigateToSelectedUser : LiveData<UserWithItem>
        get() = _navigateToSelectedUser

    private  val _navigateToSelectedComments = MutableLiveData<UserWithItem>()

    val navigateToSelectedComments : LiveData<UserWithItem>
        get() = _navigateToSelectedComments

//    init {
//        getSavedPosts()
//    }



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
                        UserWithItem(it.id,currentUser, it)
                    }
                _status.value = PostsApiStatus.DONE
                _posts.value = listResult
                Log.d("Posts", listResult.toString())
            } catch (e: Exception) {
                _status.value = PostsApiStatus.ERROR
                _posts.value = ArrayList()
            }
        }
    }

    fun savePosts(userWithItem: UserWithItem) = viewModelScope.launch {
        Log.d("Save-post", "Post saved: $userWithItem")
        postsRepository.upsert(userWithItem)
    }

    fun searchPosts(searchId: Int) = viewModelScope.launch {
        val getPostsById = RetrofitInstance.api.getPostsById(searchId)
        val getUserAsync = RetrofitInstance.api.getUsersAsync()

        try {
            _status.value = PostsApiStatus.LOADING
            // this will run on a thread managed by Retrofit
            val users = getUserAsync.await()
            val listResult = getPostsById
                .await()
                .map{
                    val currentUser = users
                        .find { user -> user.id == it.userId}
                        ?: throw IllegalStateException("User not found")
                    UserWithItem(it.id,currentUser, it)
                }
            _status.value = PostsApiStatus.DONE
            _searchedPosts.value = listResult
            Log.d("Search", listResult.toString())
        } catch (e: Exception) {
            _status.value = PostsApiStatus.ERROR
            _searchedPosts.value = ArrayList()
        }
    }

    var postsListFromDB: LiveData<List<UserWithItem>>? = null
    fun getSavedPosts() {
        postsListFromDB = postsRepository.getSavedPosts()
    }

    fun deletePost(userWithItem: UserWithItem) = viewModelScope.launch {
        postsRepository.delete(userWithItem)
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

