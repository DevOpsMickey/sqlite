package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.sqlite.adapter.NotasAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.sqlite.Nota


class MainActivity : AppCompatActivity() {
    var myNotaDBHelper:NotasDBHelper?=null
    val DB_NAME = "notas.db"
    val DB_VERSION = 1

    var fabAdd: FloatingActionButton?=null
    var miRecycler:RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabAdd = findViewById(R.id.btnAddNota)
        miRecycler = findViewById(R.id.myRecyclerNotas)
        val listaNotasShow = ArrayList<Nota>()

        myNotaDBHelper = NotasDBHelper(this, DB_NAME, null, DB_VERSION)
        myNotaDBHelper!!.open()

        val notas = myNotaDBHelper!!.getListNotas()


        for (nota in notas){
            listaNotasShow.add(nota)
            Log.d("Prueba", "${nota}")

        }

        miRecycler!!.layoutManager = StaggeredGridLayoutManager(2,1)
        miRecycler!!.setHasFixedSize(true)
        miRecycler!!.adapter = NotasAdapter(listaNotasShow, this)

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