package com.example.dogedex.auth

import com.example.dogedex.api.DogsApi
import com.example.dogedex.api.makeNetworkCall
import com.example.dogedex.api.responses.ApiResponseStatus
import com.example.dogedex.dto.DogDTOMapper
import com.example.dogedex.dto.SignUpDTO
import com.example.dogedex.dto.UserDTOMapper
import com.example.dogedex.model.User

class AuthRepository {
    //FLUJO
    //Del SignUpFragmen se crea una interfaz , en el login se implementa la interfaz que se creo en el SignUpFragment
    //del LoginActivity se ejecutara un metodo mandara llamar al viewmodel donde abra un metodo que mandara llamar al repository AuthRepository
    //de aqui se manda llamar  la API
    suspend fun signUp(email:String, password: String, passwordConfirmation: String) : ApiResponseStatus<User> = makeNetworkCall {
        val signUpDTO  = SignUpDTO(email,password,passwordConfirmation)
        val sigUpResponse = DogsApi.retrofitService.signUp(signUpDTO)
        val userDTO = sigUpResponse.data.user
        val userDTOMapper = UserDTOMapper()
        userDTOMapper.fromUserDTOToUserDomain(userDTO)
    }
}