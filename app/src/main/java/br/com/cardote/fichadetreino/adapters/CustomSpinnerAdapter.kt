package br.com.cardote.fichadetreino.adapters

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView

class CustomSpinnerAdapter<T : String>(
        private val context: Context,
        private val lista: List<T>) : BaseAdapter(), SpinnerAdapter {

    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(i: Int): Any {
        return lista[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val txt = TextView(context)
        txt.setPadding(48, 48, 48, 48)
        txt.textSize = 18f
        txt.gravity = Gravity.CENTER_VERTICAL
        txt.text = lista[position]
        txt.setTextColor(Color.parseColor("#000000"))
        return txt
    }

    override fun getView(i: Int, view: View?, viewgroup: ViewGroup): View {
        val txt = TextView(context)
        txt.gravity = Gravity.LEFT
        txt.setPadding(16, 32, 16, 32)
        txt.textSize = 16f
        txt.text = lista[i]
        txt.setTextColor(Color.parseColor("#000000"))
        return txt
    }
}