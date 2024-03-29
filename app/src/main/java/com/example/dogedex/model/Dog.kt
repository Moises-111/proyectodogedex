package com.example.dogedex.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dog(
    val id: Long,
    val index:Int,
    val name :String,
    val type:String,
    val heightFemale:String,
    val heightMale: String,
    val imageUrl : String,
    val lifeExpectancy: String,
    val temperament: String,
    val weightFemale: String,
    val weightMale: String,
    val inCollection: Boolean = true) : Parcelable, Comparable<Dog>{
    override fun compareTo(other: Dog)=
        if(this.index > other.index){
            1
        }else{
            -1
        }
    }