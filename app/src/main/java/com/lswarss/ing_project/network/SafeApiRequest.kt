package com.lswarss.ing_project.network

import com.lswarss.ing_project.exceptions.ApiException
import retrofit2.Response

abstract class SafeApiRequest{

    //this function will check if the response from api was successful and if yes it will give us a response body
    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{
        val response = call.invoke()
        if(response.isSuccessful){
           return response.body()!!
        }
        else{
            throw ApiException(response.code().toString())
        }
    }


}