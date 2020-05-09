package com.lswarss.ing_project.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

class RetrofitInstance {

    companion object {
        //By calling it with lazy we call this only once
        private val retrofit by lazy {
            val loggin = HttpLoggingInterceptor()
            loggin.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggin)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create()) //this is used to how response should be understand and converted to Kotlin objects
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(PostsApi::class.java)
        }
    }

}