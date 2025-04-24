package com.uai.calculatorapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.uai.calculatorapp.R
import com.uai.calculatorapp.R.color.calc_digit

class CalcAdapter(
    private val keys: List<PadKeys>,
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val btnLabel: MaterialButton = itemView.findViewById(R.id.btn_key)

        fun bind(item: PadKeys) {
            btnLabel.text = item.label
            val colorId = if (item.isOperator) R.color.calc_operator else calc_digit
            btnLabel.setTextColor(ContextCompat.getColor(itemView.context, colorId))
            btnLabel.setOnClickListener {
                onKeyClick(item.label)
            }
        }

    }
}


