package com.lswarss.ing_project.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lswarss.ing_project.domain.PhotoItem
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.network.PostsApi
import com.lswarss.ing_project.network.PostsApiStatus
import kotlinx.coroutines.launch

class PhotosViewModel(user: UserWithItem, app: Application) : AndroidViewModel(app){

    private val _status = MutableLiveData<PostsApiStatus>()
    val status: LiveData<PostsApiStatus>
        get() = _status

    private val _selectedUserPhotos = MutableLiveData<List<PhotoItem>>()
    val selectedUserPhotos : LiveData<List<PhotoItem>>
        get() = _selectedUserPhotos

    init{
        getPhotos(user.user.id)
    }

    private fun getPhotos(userId: Int){
        viewModelScope.launch {
            var getAlbums = PostsApi.photosService.getUserAlbumsAsyncWithId(userId)
            var getPhotos = PostsApi.photosService.getAllPhotosAsync()

            try{
                _status.value = PostsApiStatus.LOADING
                val albumsForUser= getAlbums.await()
                val photosResult = getPhotos
                    .await()
                    .filter { it.albumId == albumsForUser[1].id }
                _status.value = PostsApiStatus.DONE
                _selectedUserPhotos.value = photosResult
            }catch (e: Exception){
                _status.value = PostsApiStatus.ERROR
                _selectedUserPhotos.value = ArrayList()
            }

        }


    }




}