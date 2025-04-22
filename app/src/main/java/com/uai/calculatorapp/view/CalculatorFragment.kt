package com.uai.calculatorapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.uai.calculatorapp.R

class CalculatorFragment : Fragment() {

    private lateinit var btnClear: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        btnClear = view.findViewById(R.id.btn_allClearCalc)
        setOnClick()

        return view
    }

    private fun setOnClick() {
        btnClear.setOnClickListener {
            clearChar()
        }
    }

    private fun clearChar() {
        TODO("Not yet implemented")
    }
}