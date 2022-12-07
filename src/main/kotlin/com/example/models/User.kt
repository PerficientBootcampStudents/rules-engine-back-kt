package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Table (
    val id:String,
    val Column1:Int,
    val Column2: String,
    val Column3:String,
    val Column4:Boolean
)
    {
}