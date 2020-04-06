package com.lswarss.ing_project.network

import com.lswarss.ing_project.domain.PostItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface PostsApi {

    @GET("/posts")
    suspend fun getPosts() : Response<List<PostItem>>

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
