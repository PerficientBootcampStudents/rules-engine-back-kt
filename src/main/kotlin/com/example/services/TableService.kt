package com.example.services

import com.example.ServiceHelper.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll


object TableX : Table() {
    val id = varchar("id", 50)
    val column1 = integer("column1")
    val column2 = varchar("column2", 50)
    val column3 = varchar("column3", 50)
    val column4 = bool("column4")
}

class TableService {

    suspend fun getAll(): List<com.example.models.Table> = dbQuery {
        TableX.selectAll().map { toTable(it) }
    }




    private fun toTable(row: ResultRow): com.example.models.Table =
        com.example.models.Table(
            id = row[TableX.id],
            column1 = row[TableX.column1],
            column2 = row[TableX.column2],
            column3 = row[TableX.column3],
            column4 = row[TableX.column4]
        )


}
