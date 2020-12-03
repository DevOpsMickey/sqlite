package com.example.sqlite

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    var myNotaDBHelper:NotasDBHelper?=null
    val DB_NAME = "notas.db"
    val DB_VERSION = 1

    var fabAdd: FloatingActionButton?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAdd = findViewById(R.id.btnAddNota)

        myNotaDBHelper = NotasDBHelper(this, DB_NAME, null, DB_VERSION)
        myNotaDBHelper!!.open()

        val notas = myNotaDBHelper!!.getListNotas()

        for (nota in notas){
            Log.d("Prueba", "${nota.titulo}")
        }

        fabAdd!!.setOnClickListener{
            val i = Intent(this, Add_Notas_Activity::class.java)
            startActivity(i)
        }

        /*
        fun onDestroy() {
            super.onDestroy()
            myNotaDBHelper!!.close()
        }

        */
    }
}