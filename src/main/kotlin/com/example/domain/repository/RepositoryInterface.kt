package com.example.domain.repository

interface RepositoryInterface {
    suspend fun getTable(): String
}