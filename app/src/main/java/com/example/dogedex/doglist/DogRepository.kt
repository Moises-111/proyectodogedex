package com.example.dogedex.doglist

import com.example.dogedex.R
import com.example.dogedex.model.Dog
import com.example.dogedex.api.DogsApi.retrofitService
import com.example.dogedex.api.makeNetworkCall
import com.example.dogedex.api.responses.ApiResponseStatus
import com.example.dogedex.dto.AddDogToUserDTO
import com.example.dogedex.dto.DogDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.lang.Exception

class DogRepository {

    suspend fun getDogCollection():ApiResponseStatus<List<Dog>>{
        return withContext(Dispatchers.IO){
            val allDogsListDeferred = async { downsloadDogs()}
            val userDogsListDeferred = async { getUserDogs() }

            val allDogsListResponse = allDogsListDeferred.await()
            val userDogsListResponse = userDogsListDeferred.await()

            if(allDogsListResponse is ApiResponseStatus.Error){
                allDogsListResponse
            }else if(userDogsListResponse is ApiResponseStatus.Error){
                userDogsListResponse
            }else if(allDogsListResponse is ApiResponseStatus.Success &&
                    userDogsListResponse is ApiResponseStatus.Success){
                ApiResponseStatus.Success(getCollectionList(allDogsListResponse.data,userDogsListResponse.data))
            }else{
                ApiResponseStatus.Error(R.string.unknown_error)
            }
        }
    }

    private suspend fun downsloadDogs() : ApiResponseStatus<List<Dog>> = makeNetworkCall {
            val dogListApiResponse = retrofitService.getAllDogs()
            val dogDTOList = dogListApiResponse.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
        }
    private suspend fun getUserDogs() : ApiResponseStatus<List<Dog>> = makeNetworkCall {
        val dogListApiResponse = retrofitService.getUserDogs()
        val dogDTOList = dogListApiResponse.data.dogs
        val dogDTOMapper = DogDTOMapper()
        dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
    }

    suspend fun addDogToUser(dogId: Long): ApiResponseStatus<Any> = makeNetworkCall {
        val addDogToUserDTO = AddDogToUserDTO(dogId)
        val defaultResponse = retrofitService.addDogToUser(addDogToUserDTO)

        if(!defaultResponse.isSuccess){
            throw Exception(defaultResponse.message)
        }
    }

    private fun getCollectionList(allDogList: List<Dog>, userDogList: List<Dog>)=
         allDogList.map{
            if(userDogList.contains(it)){
                it
            }else{
                Dog(0,it.index,"","","","","",
                    "","",""
                ,"",inCollection = false)
            }
        }




}