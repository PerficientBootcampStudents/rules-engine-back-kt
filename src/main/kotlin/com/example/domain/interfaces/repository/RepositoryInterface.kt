package com.example.domain.interfaces.repository

interface RepositoryInterface {

    suspend fun getTable(): String
}