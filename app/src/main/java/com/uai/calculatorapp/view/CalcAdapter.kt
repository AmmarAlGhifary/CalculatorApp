package com.uai.calculatorapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.uai.calculatorapp.R

class CalcAdapter(
    private val keys: List<CalcButton>,
    private val onKeyClick: (String) -> Unit
) : RecyclerView.Adapter<CalcAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalcAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calculator_button, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalcAdapter.ViewHolder, position: Int) {
        holder.bind(keys[position])
    }

    override fun getItemCount(): Int = keys.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val btnLabel : MaterialButton = itemView.findViewById(R.id.btn_numberKeyPad)

        fun bind(itemView: CalcButton) {
            btnLabel.text = itemView.toString()
            btnLabel.setOnClickListener {
//                on
            }
        }
    }
}


