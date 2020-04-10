package com.lswarss.ing_project.network

import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.domain.UserItem
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface PostsApiService {

    @GET("/posts")
    suspend fun getPostsAsync() : Deferred<List<PostItem>>

    @GET("/users")
    suspend fun getUsersAsync() : Deferred<List<UserItem>>

    @GET("/users/{id}")
    suspend fun getUserByIdAsync(@Path("id") id : Int) : Deferred<UserItem>


 }

object PostsApi{
    val retrofitService : PostsApiService by lazy { retrofit.create(PostsApiService::class.java)}
}


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()