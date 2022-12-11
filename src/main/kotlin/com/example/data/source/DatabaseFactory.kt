package com.example.data.source

import com.example.data.dao.DaoInterface
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Statement

object DatabaseFactory: DaoInterface {

    const val DB_NAME = "bdata"

    override fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = System.getenv("JDBC_DRIVER") // 1
        config.jdbcUrl = System.getenv("DATABASE_URL") // 2
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()

        return HikariDataSource(config)
    }

    override fun connect(): Statement {
       return this.hikari().connection.createStatement()
    }

}

