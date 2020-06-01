package com.lswarss.ing_project.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lswarss.ing_project.domain.UserWithItem

class UserViewModel(user: UserWithItem, app: Application) : AndroidViewModel(app) {

    private val _selectedUser = MutableLiveData<UserWithItem>()
    val selectedUser: LiveData<UserWithItem>
        get() = _selectedUser

    init {
        _selectedUser.value = user
    }

    private val _navigateToSelectedUserPhotos = MutableLiveData<UserWithItem>()

    val navigateToSelectedUserPhotos: LiveData<UserWithItem>
        get() = _navigateToSelectedUserPhotos

    fun displayUserDetail(userWithItem: UserWithItem) {
        _navigateToSelectedUserPhotos.value = userWithItem
    }

    fun displayUserDetailComplete() {
        _navigateToSelectedUserPhotos.value = null
    }

}