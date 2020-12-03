package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class Add_Notas_Activity : AppCompatActivity() {
    var myNotaDBHelper:NotasDBHelper?=null
    val DB_NAME = "notas.db"
    val DB_VERSION = 1

    var etTitulo:EditText?=null
    var etContenido:EditText?=null
    var imgBtnGuardar:ImageButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__notas)
        myNotaDBHelper = NotasDBHelper(this, DB_NAME, null, DB_VERSION)
        myNotaDBHelper!!.open()

        etTitulo = findViewById(R.id.tvTituloNota)
        etContenido = findViewById(R.id.tvContenidoNota)
        imgBtnGuardar = findViewById(R.id.imgBtnGuardar)


        imgBtnGuardar!!.setOnClickListener{
            val titulo = etTitulo!!.text.toString()
            val contenido = etContenido!!.text.toString()

            if( titulo != ""){
                myNotaDBHelper!!.addNotas(titulo,contenido)
                etTitulo!!.setText("")
                etContenido!!.setText("")

            }else{
                Toast.makeText(this, "Ingresa un titulo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}