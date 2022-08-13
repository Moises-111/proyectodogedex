package com.example.dogedex.api

import com.example.dogedex.BASE_URL
import com.example.dogedex.GET_ALL_DOGS
import com.example.dogedex.SIGN_UP_URL
import com.example.dogedex.api.responses.DogListApiResponse
import com.example.dogedex.api.responses.SignUpApiResponse
import com.example.dogedex.dto.SignUpDTO
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface ApiService {
    @GET(GET_ALL_DOGS)
    suspend fun getAllDogs(): DogListApiResponse

    @POST(SIGN_UP_URL)
    suspend fun signUp(@Body signUpDTO: SignUpDTO): SignUpApiResponse
}

object DogsApi{
    val retrofitService : ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}