package com.example.dogedex.doglist

import com.example.dogedex.Dog
import com.example.dogedex.R
import com.example.dogedex.api.DogsApi.retrofitService
import com.example.dogedex.api.makeNetworkCall
import com.example.dogedex.api.responses.ApiResponseStatus
import com.example.dogedex.dto.DogDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class DogRepository {
    suspend fun downsloadDogs() : ApiResponseStatus<List<Dog>> = makeNetworkCall {
            val dogListApiResponse = retrofitService.getAllDogs()
            val dogDTOList = dogListApiResponse.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
        }

}