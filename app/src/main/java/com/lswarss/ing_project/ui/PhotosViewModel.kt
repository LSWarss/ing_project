package com.lswarss.ing_project.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lswarss.ing_project.domain.PhotoItem
import com.lswarss.ing_project.domain.UserWithItem
import com.lswarss.ing_project.network.RetrofitInstance
import kotlinx.coroutines.launch

class PhotosViewModel(user: UserWithItem, app: Application) : AndroidViewModel(app){

    private val _selectedUserPhotos = MutableLiveData<List<PhotoItem>>()
    val selectedUserPhotos : LiveData<List<PhotoItem>>
        get() = _selectedUserPhotos

    init{
        getPhotos(user.user.id)
    }



    private fun getPhotos(userId: Int){
        viewModelScope.launch {
            var getAlbums = RetrofitInstance.api.getUserAlbumsAsyncWithId(userId)
            var getPhotos = RetrofitInstance.api.getAllPhotosAsync()

            try {
                val albumsForUser= getAlbums.await()
                Log.d("ImgRequest-albums", albumsForUser.toString())
                val photosResult = getPhotos.await()
                    .filter { list -> list.albumId in albumsForUser[0].id..albumsForUser[albumsForUser.size-1].id}
                Log.d("ImgRequest-photos", photosResult.size.toString())
                _selectedUserPhotos.value = photosResult
            } catch (e: Exception) {
                _selectedUserPhotos.value = ArrayList()
            }
        }


    }




}