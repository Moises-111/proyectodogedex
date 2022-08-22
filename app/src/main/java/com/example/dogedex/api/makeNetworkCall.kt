package com.example.dogedex.api

import com.example.dogedex.R
import com.example.dogedex.api.responses.ApiResponseStatus
import com.example.dogedex.dto.DogDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
) :ApiResponseStatus<T>{
    return withContext(Dispatchers.IO){
        try{
            ApiResponseStatus.Success(call())
        }catch (e: UnknownHostException){
            ApiResponseStatus.Error(R.string.unknown_host_exception_error)
        }catch (e :Exception){

            val errorMessage =when(e.message){
                "sign_up_error"->R.string.error_sign_up
                "sign_IN_error"->R.string.error_sign_in
                "user_already_exists"->R.string.user_already_exists
                else -> R.string.unknown_error
            }
            ApiResponseStatus.Error(errorMessage)
        }

    }

}