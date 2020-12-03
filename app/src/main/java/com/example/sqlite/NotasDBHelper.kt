package com.example.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException
import java.util.ArrayList

class NotasDBHelper(context: Context?,
                    name: String?,
                    factory: SQLiteDatabase.CursorFactory?,
                    version:Int)
:SQLiteOpenHelper(context,name,factory,version){
    //Nombre de la tabla
    val TABLE_NAME = "notas"

    // nombre de las columnas
    val _ID = "id"
    val TITLE = "titulo"
    val CONTENT = "contenido"


    var database:SQLiteDatabase?=null

    val QUERY_CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
            "($_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$TITLE TEXT NOT NULL, " +
            "$CONTENT TEXT);"

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(QUERY_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL( "DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    @Throws(SQLException::class)
    fun open(){
        database =  this.writableDatabase
    }

    override fun close(){
        super.close()
        database!!.close()
    }

    fun addNotas(titulo: String, contenido: String){
        val contentValues = ContentValues()
        contentValues.put(TITLE, titulo)
        contentValues.put(CONTENT, contenido)

        database!!.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllNotas():Cursor{
        val projection =  listOf<String>(_ID,TITLE,CONTENT)
        val cursor = database!!.query(TABLE_NAME, projection.toTypedArray(),
            null, null,
            null, null,
            null)
        return cursor
    }

    fun getListNotas():ArrayList<Nota> {

        val listaNotas = ArrayList<Nota>()
        val selectAll = "SELECT * FROM $TABLE_NAME"
        val  cursor: Cursor = database!!.rawQuery(selectAll, null)

        if(cursor.moveToFirst()){
            do {
                val nota = Nota().apply {
                    id = cursor.getInt(cursor.getColumnIndex(_ID))
                    titulo = cursor.getString(cursor.getColumnIndex(TITLE))
                    contenido = cursor.getString(cursor.getColumnIndex(CONTENT))
                }
                listaNotas.add(nota)
            } while (cursor.moveToNext())
        }
        return listaNotas
    }

}