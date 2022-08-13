package com.example.dogedex.dto

import com.example.dogedex.model.Dog

class DogDTOMapper {

    //es privado por que solo se usa para parsear el dogdto a dog , para mapear despues la lista fromDogDTOListToDogDomainList
    private fun fromDogDTOToDogDomain(dogDTO: DogDTO): Dog {
        return Dog(dogDTO.id,dogDTO.index,dogDTO.name,dogDTO.type,
        dogDTO.heightFemale,dogDTO.heightMale,dogDTO.imageUrl,dogDTO.lifeExpectancy,
        dogDTO.temperament,dogDTO.weightFemale,dogDTO.weightMale)
    }

    fun fromDogDTOListToDogDomainList(dogDTOList: List<DogDTO>):List<Dog>{
        return dogDTOList.map { fromDogDTOToDogDomain(it) }
    }
}