package com.example.dogedex.dto

import com.example.dogedex.model.Dog
import com.example.dogedex.model.User

class UserDTOMapper {

     fun fromUserDTOToUserDomain(userDTO: UserDTO): User {
        return User(userDTO.id,userDTO.email,userDTO.authentication)
    }
}