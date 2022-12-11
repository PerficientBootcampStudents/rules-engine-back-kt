package com.example.data.dao

import com.zaxxer.hikari.HikariDataSource
import java.sql.Statement

interface DaoInterface {
    fun hikari(): HikariDataSource
    fun connect(): Statement

}