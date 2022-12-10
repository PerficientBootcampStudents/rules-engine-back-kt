package com.example.data.repository

import com.example.data.source.DatabaseFactory
import com.example.domain.model.Column
import com.example.domain.model.Table
import com.example.domain.repository.RepositoryInterface
import com.google.gson.Gson
import com.zaxxer.hikari.HikariDataSource
import java.sql.ResultSet

class Repository(
    private val database: HikariDataSource = DatabaseFactory.hikari()
): RepositoryInterface {

    private val st = database.connection.createStatement()
    private val _DBNAME = DatabaseFactory.DB_NAME

    override suspend fun getTable(): String {
        val rs = st.executeQuery("SELECT * FROM $_DBNAME")
        val amountColumn = rs.metaData.columnCount
        val table = createSchema(amountColumn, rs)

        return if (amountColumn > 0) {

            while (rs.next()) {
                for (i in 1..amountColumn) {
                    val value = rs.getObject(i)
                    table.columns[i - 1].array.add(value)
                }

            }

            Gson().toJson(table)

        } else {
            "Table is empty"
        }

    }

    private fun createSchema(amountColumn: Int, rs: ResultSet): Table {
        val auxTable = Table(_DBNAME)
        val auxColumn = ArrayList<Column>()

        for (i in 1..amountColumn) {
            auxColumn.add(Column(rs.metaData.getColumnType(i)))
        }

        auxTable.columns = auxColumn

        return auxTable
    }

}