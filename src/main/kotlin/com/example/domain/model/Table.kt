package com.example.domain.model

data class Table(
    val name: String,
    var columns: ArrayList<Column> = arrayListOf()
)