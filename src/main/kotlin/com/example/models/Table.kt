package com.example.models

import kotlinx.serialization.Serializable

//Review serializable annotation

@Serializable
data class Table (
    val id:String,
    val column1:Int,
    val column2: String,
    val column3:String,
    val column4:Boolean
)

