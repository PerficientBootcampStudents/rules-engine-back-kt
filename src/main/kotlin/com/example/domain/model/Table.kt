package com.example.domain.model

class Table{

    val columns: ArrayList<Column> = ArrayList()
    val tuples: ArrayList<MutableMap<String, Any>>  = ArrayList()
    private constructor()

    companion object{
        private lateinit var instance: Table
        fun getInstance(): Table{
            if(!::instance.isInitialized){
                instance = Table()
            }

            instance.tuples.clear()
            return instance
        }
    }


}