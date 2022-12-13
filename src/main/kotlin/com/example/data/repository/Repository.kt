package com.example.data.repository

import com.example.data.dao.DaoInterface
import com.example.data.source.DatabaseFactory
import com.example.domain.model.Table
import com.example.domain.interfaces.repository.RepositoryInterface
import com.google.gson.Gson
import io.ktor.http.*
import java.sql.ResultSet

class Repository(
    database: DaoInterface
): RepositoryInterface {

    private val st = database.connect()
    override val DBNAME = DatabaseFactory.DB_NAME
    private val LIMIT = 10

    override suspend fun getTable(): String {
        return invoke("SELECT * FROM $DBNAME")

    }

    override suspend fun sendQuery(query: String): String {
        return try {
            invoke(query)

        }catch (e: Exception){
            e.message.toString()
        }

    }

    private fun invoke(query: String): String {
        val rs = st.executeQuery(query)
        val amountColumn = rs.metaData.columnCount
        val table = Table()

        return if (amountColumn > 0) {
            crateSchema(rs, amountColumn, table)
            Gson().toJson(table)

        } else {
            HttpStatusCode.NotFound.toString()
        }
    }

    private fun crateSchema(rs: ResultSet, amountColumn: Int, table: Table) {
        for (i in 1..LIMIT) {
            if (rs.next()) {
                val tuple: MutableMap<String, Any> = mutableMapOf()

                for (j in 1..amountColumn) {
                    tuple[rs.metaData.getColumnName(j)] = rs.getObject(j).toString()
                }

                table.tuples.add(tuple)
            }else{
                break
            }
        }
    }

}

