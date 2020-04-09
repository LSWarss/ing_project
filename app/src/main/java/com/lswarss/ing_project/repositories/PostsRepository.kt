package com.lswarss.ing_project.repositories

import com.lswarss.ing_project.network.PostsApi
import com.lswarss.ing_project.network.SafeApiRequest

class PostsRepository (private val api: PostsApi) : SafeApiRequest() {

     suspend fun getPosts() = apiRequest { api.getPosts() }
     suspend fun getUserById(id : Int) = apiRequest { api.getUserById(id)}
     suspend fun getUsers() = apiRequest { api.getUsers()}
}