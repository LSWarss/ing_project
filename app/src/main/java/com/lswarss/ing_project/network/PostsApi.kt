package com.lswarss.ing_project.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lswarss.ing_project.domain.PostItem
import com.lswarss.ing_project.domain.UserItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface PostsApiService {

    @GET("/posts")
    fun getPostsAsync() : Deferred<List<PostItem>>

 }

interface UserApiService {
    @GET("/users")
    fun getUsersAsync() : Deferred<List<UserItem>>

    @GET("/users/{id}")
    fun getUserByIdAsync(@Path("id") id : Int) : Deferred<UserItem>


}

object PostsApi{
    val postsService : PostsApiService by lazy { retrofit.create(PostsApiService::class.java)}
    val usersService : UserApiService by lazy { retrofit.create(UserApiService::class.java) }
}


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()