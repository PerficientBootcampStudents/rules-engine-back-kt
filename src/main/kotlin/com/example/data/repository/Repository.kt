package com.example.data.repository

import com.example.data.dao.DaoInterface
import com.example.data.source.DatabaseFactory
import com.example.domain.model.Table
import com.example.domain.interfaces.repository.RepositoryInterface
import com.example.domain.model.Column
import com.google.gson.Gson
import io.ktor.http.*
import java.sql.ResultSet

class Repository(
    database: DaoInterface
) : RepositoryInterface {

    private val st = database.connect()
    override val dbName = DatabaseFactory.DB_NAME
    private val limit = 10

    override suspend fun getTable(): String {
        return invoke("SELECT * FROM $dbName")

    }

    override suspend fun sendQuery(query: String): String {

        return try {
            invoke(query)

        } catch (e: Exception) {
            try {
                st.executeQuery("COMMIT TRANSACTION")
            } catch (_: Exception) { }
            e.message.toString()
        }


    }

    private fun invoke(query: String): String {
        val rs = st.executeQuery(query)
        val amountColumn = rs.metaData.columnCount
        val table = Table.getInstance()

        return if (amountColumn > 0) {
            loadTuples(rs, amountColumn, table)
            Gson().toJson(table.tuples)

        } else {
            HttpStatusCode.NotFound.toString()
        }
    }

    private fun loadTuples(rs: ResultSet, amountColumn: Int, table: Table) {

        if (table.columns.isEmpty()) {
            for (i in 1..amountColumn) {
                table.columns.add(
                    Column(
                        rs.metaData.getColumnName(i),
                        rs.metaData.getColumnTypeName(i)
                    )
                )
            }
        }

        for (i in 1..limit) {
            if (rs.next()) {
                val tuple: MutableMap<String, Any> = mutableMapOf()

                for (j in 1..amountColumn) {
                    tuple[rs.metaData.getColumnName(j)] = rs.getObject(j).toString()
                }

                table.tuples.add(tuple)
            } else {
                break
            }
        }
    }


}

