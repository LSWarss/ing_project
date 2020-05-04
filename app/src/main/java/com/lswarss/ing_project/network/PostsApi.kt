package com.lswarss.ing_project.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lswarss.ing_project.domain.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface PostsApiService {

    @GET("/posts")
    fun getPostsAsync() : Deferred<List<PostItem>>

    @GET("/posts")
    fun getPostsAsyncWithPages(@Query("_page") page : Int) : Deferred<List<PostItem>>

 }

interface UserApiService {
    @GET("/users")
    fun getUsersAsync() : Deferred<List<UserItem>>
}

interface CommentsApiService {

    @GET("/comments")
    fun getCommentsAsyncWithId(@Query("postId") postId : Int) : Deferred<List<CommentItem>>

}

interface PhotosApiService {

    @GET("/albums")
    fun getUserAlbumsAsyncWithId(@Query("userId") userId : Int) : Deferred<List<AlbumItem>>

    @GET("/photos")
    fun getAllPhotosAsync() : Deferred<List<PhotoItem>>

}


object PostsApi{
    val postsService : PostsApiService by lazy { retrofit.create(PostsApiService::class.java)}
    val usersService : UserApiService by lazy { retrofit.create(UserApiService::class.java) }
    val commentsService : CommentsApiService by lazy { retrofit.create((CommentsApiService::class.java))}
    val photosService : PhotosApiService by lazy { retrofit.create((PhotosApiService::class.java))}
}


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()