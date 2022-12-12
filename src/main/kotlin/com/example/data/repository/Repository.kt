package com.example.data.repository

import com.example.data.dao.DaoInterface
import com.example.data.source.DatabaseFactory
import com.example.domain.model.Column
import com.example.domain.model.Table
import com.example.domain.interfaces.repository.RepositoryInterface
import com.google.gson.Gson
import io.ktor.http.*
import java.sql.ResultSet

class Repository(
    database: DaoInterface
): RepositoryInterface {

    private val st = database.connect()
    companion object {
        private const val DBNAME = DatabaseFactory.DB_NAME
        private const val LIMIT = 10
    }

    override suspend fun getTable(): String {
        val rs = st.executeQuery("SELECT * FROM $DBNAME")
        val amountColumn = rs.metaData.columnCount
        val table = createSchema(amountColumn, rs)

        return if (amountColumn > 0) {

            for (i in 1..LIMIT) {
                if (rs.next()) {
                    val tuple: MutableMap<String, Any> = mutableMapOf()

                    for (j in 1..amountColumn) {
                        tuple[rs.metaData.getColumnName(j)] = rs.getObject(j)
                    }

                    table.tuples.add(tuple)
                }else{
                    break
                }
            }

            Gson().toJson(table)

        } else {
            HttpStatusCode.NotFound.toString()
        }

    }

    private fun createSchema(amountColumn: Int, rs: ResultSet): Table {
        val auxTable = Table(DBNAME)

        for (i in 1..amountColumn) {
            auxTable.columns.add(
                Column(
                    rs.metaData.getColumnTypeName(i),
                    rs.metaData.getColumnName(i)
                )
            )
        }

        return auxTable
    }

}