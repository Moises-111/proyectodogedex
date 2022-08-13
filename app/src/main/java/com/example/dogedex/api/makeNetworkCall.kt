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
            ApiResponseStatus.Error(R.string.unknown_error)
        }

    }

}