package com.uai.calculatorapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uai.calculatorapp.R

class CalculatorFragment : Fragment() {

    private lateinit var btnClear: Button
    private lateinit var btnClearChar: Button
    private lateinit var btnPercentage: Button
    private lateinit var btnDivision: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnAdd: Button
    private lateinit var btnEquals: Button

    private val display = StringBuilder()
    private lateinit var tvDisplay: TextView

    private lateinit var listNumber: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_calculator, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnClear = view.findViewById(R.id.btn_allClearCalc)
        btnClearChar = view.findViewById(R.id.btn_deleteCharCalc)
        btnPercentage = view.findViewById(R.id.btn_percentageCalc)
        btnDivision = view.findViewById(R.id.btn_divisionCalc)
        btnMultiply = view.findViewById(R.id.btn_multiply)
        btnSubtract = view.findViewById(R.id.btn_subtract)
        btnAdd = view.findViewById(R.id.btn_add)
        btnEquals = view.findViewById(R.id.btn_equals)
        tvDisplay = view.findViewById(R.id.tv_mainNumber)

        listNumber = view.findViewById(R.id.recyclerPad)

        val padKeys = listOf(
            PadKeys("7"), PadKeys("8"), PadKeys("9"),
            PadKeys("4"), PadKeys("5"), PadKeys("6"),
            PadKeys("1"), PadKeys("2"), PadKeys("3"),
            PadKeys("0", span = 2),
            PadKeys(".", isOperator = false)
        )

        val grid = GridLayoutManager(requireContext(), 3)
        grid.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = padKeys[position].span

        }

        listNumber.apply {
            layoutManager = grid
            adapter = CalcAdapter(padKeys) { label ->
                onPadPressed(label)
            }
            setHasFixedSize(true)
        }
    }

    private fun onPadPressed(label: String) {
        display.append(label)
        tvDisplay.text = display
        tvDisplay.post {
            tvDisplay.layout?.let {
                val scroll = tvDisplay.layout.getLineWidth(0) - tvDisplay.width
                if (scroll > 0) tvDisplay.scrollTo(scroll.toInt(), 0)
            }
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