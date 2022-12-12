package com.example.domain.model

data class Table(
    val name: String,
    val columns: ArrayList<Column> = ArrayList(),
    val tuples: ArrayList<MutableMap<String, Any>> = ArrayList()
)