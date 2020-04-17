package com.lswarss.ing_project.fragments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lswarss.ing_project.domain.UserWithItem

class UserViewModel(user: UserWithItem) : ViewModel(){

    private val _selectedUser = MutableLiveData<UserWithItem>()
    val selectedUser : LiveData<UserWithItem>
        get() = _selectedUser

    init{
        _selectedUser.value = user
    }


}