package com.lswarss.ing_project.network

import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.domain.User
import com.lswarss.ing_project.domain.UserItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface PostsApi {

    @GET("/posts")
    suspend fun getPosts() : Response<List<PostItem>>

    @GET("/users")
    suspend fun getUsers() : Response<List<UserItem>>

    @GET("/users/{id}")
    suspend fun getUserById(@Path("id") id : Int) : Response<UserItem>

    companion object {
        operator fun invoke(): PostsApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(PostsApi::class.java)
        }
    }

 }
