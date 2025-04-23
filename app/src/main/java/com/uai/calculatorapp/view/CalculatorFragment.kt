package com.uai.calculatorapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uai.calculatorapp.R

class CalculatorFragment : Fragment() {

    private lateinit var btnClear: Button
    private lateinit var btnClearChar: Button
    private lateinit var btnPercntage: Button
    private lateinit var btnDivision : Button
    private lateinit var btnMultiply: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnPlus : Button
    private lateinit var btnEquals : Button


    private lateinit var listNumber : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        btnClear = view.findViewById(R.id.btn_allClearCalc)
        setOnClick()

        listNumber.layoutManager = GridLayoutManager(context, 4)

        return view
    }

    private fun setOnClick() {
        btnClear.setOnClickListener {
            clearAll()
        }

        btnClearChar.setOnClickListener {
            clearChar()
        }

        btnPercntage.setOnClickListener {
            percentage()
        }
        btnDivision.setOnClickListener {
            division()
        }

        btnMultiply.setOnClickListener {
            multiply()
        }

        btnSubtract.setOnClickListener {
            subtract()
        }

        btnPlus.setOnClickListener {
            plus()
        }

        btnEquals.setOnClickListener {
            equals()
        }
    }

    private fun equals() {
        TODO("Not yet implemented")
    }

    private fun plus() {

    }

    private fun subtract() {
        TODO("Not yet implemented")
    }

    private fun multiply() {
        TODO("Not yet implemented")
    }

    private fun division() {
        TODO("Not yet implemented")
    }

    private fun percentage() {
        TODO("Not yet implemented")
    }

    private fun clearChar() {
        TODO("Not yet implemented")
    }

    private fun clearAll() {
        TODO("Not yet implemented")
    }
}