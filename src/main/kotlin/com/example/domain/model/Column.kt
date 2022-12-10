package com.example.domain.model

data class Column(
    val type: Int,
    val array: ArrayList<Any> = ArrayList()
)
