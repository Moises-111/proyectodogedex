package com.example.dogedex.api

import com.example.dogedex.R
import com.example.dogedex.api.responses.ApiResponseStatus
import com.example.dogedex.dto.DogDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException


private const val  UNAUTHORIZED_ERROR_CODE = 401
suspend fun <T> makeNetworkCall(
    call: suspend () -> T
) :ApiResponseStatus<T>{
    return withContext(Dispatchers.IO){
        try{
            ApiResponseStatus.Success(call())
        }catch (e: UnknownHostException){
            ApiResponseStatus.Error(R.string.unknown_host_exception_error)
        }catch (e:HttpException){
            val errorMessage= if(e.code() == UNAUTHORIZED_ERROR_CODE){
                R.string.wrong_use_or_passoword
            }else{
                R.string.unknown_error
            }
            ApiResponseStatus.Error(errorMessage)
        }

        catch (e :Exception){

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