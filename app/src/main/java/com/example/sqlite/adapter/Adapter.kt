package com.example.sqlite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.Nota
import com.example.sqlite.R

class NotasAdapter(Notas:ArrayList<Nota>, context:Context): RecyclerView.Adapter<NotasAdapter.ViewHolder>(){

    var Notas:ArrayList<Nota>? = null
    var context:Context? = null

    init {
        this.Notas = Notas
        this.context = context
    }

    class ViewHolder(vista:View): RecyclerView.ViewHolder(vista){
        var tvTitulo:TextView? = null
        var tvCont:TextView? = null

        init {
            tvTitulo = vista.findViewById(R.id.tvTextTituloNota)
            tvCont = vista.findViewById(R.id.tvContenidoTextNota)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vistaNotas:View = LayoutInflater
            .from(context)
            .inflate(R.layout.nota_item,parent,false)
        val nota = ViewHolder(vistaNotas)
        vistaNotas.tag = nota
        return nota
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notita = Notas!![position]
        holder.tvTitulo!!.text = Notas!![position].titulo
        holder.tvCont!!.text = Notas!![position].contenido
    }

    override fun getItemCount(): Int {
        return this.Notas!!.count()
    }

}