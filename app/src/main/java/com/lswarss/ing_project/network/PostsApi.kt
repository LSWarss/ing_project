package com.lswarss.ing_project.network

import com.lswarss.ing_project.domain.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface PostsApi{

    @GET("/posts")
    fun getPostsAsync(@Query("_start") postStart: Int, @Query("_limit") postLimit : Int) : Deferred<List<PostItem>>



    @GET("/posts")
    suspend fun getPosts() : Response<List<PostItem>>

    @GET("/posts")
    fun getPostsAsyncWithPages(@Query("_page") page : Int) : Deferred<List<PostItem>>

    @GET("/users")
    fun getUsersAsync() : Deferred<List<UserItem>>

    @GET("/comments")
    fun getCommentsAsyncWithId(@Query("postId") postId : Int) : Deferred<List<CommentItem>>

    @GET("/albums")
    fun getUserAlbumsAsyncWithId(@Query("userId") userId : Int) : Deferred<List<AlbumItem>>

    @GET("/photos")
    fun getAllPhotosAsync() : Deferred<List<PhotoItem>>

}


