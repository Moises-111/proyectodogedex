package com.example.dogedex.dto

import com.squareup.moshi.Json

class AddDogToUserDTO (@field:Json(name = "dog_id") val dogId: Long)