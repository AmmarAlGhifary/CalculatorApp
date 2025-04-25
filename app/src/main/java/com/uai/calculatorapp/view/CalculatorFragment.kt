package com.uai.calculatorapp.view

import android.os.Bundle
import android.text.TextUtils
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
    private lateinit var tvExpression: TextView

    private var firstOperand: Double? = null
    private var currentOperator: String? = null

    private lateinit var listNumber: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
        tvExpression = view.findViewById(R.id.tv_expression)

        listNumber = view.findViewById(R.id.recyclerPad)

        val padKeys = listOf(
            PadKeys("7"),
            PadKeys("8"),
            PadKeys("9"),
            PadKeys("4"),
            PadKeys("5"),
            PadKeys("6"),
            PadKeys("1"),
            PadKeys("2"),
            PadKeys("3"),
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

        btnClear.setOnClickListener {
            clearAll()
        }

        btnClearChar.setOnClickListener {
            clearChar()
        }

        btnPercentage.setOnClickListener {
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

        btnAdd.setOnClickListener {
            plus()
        }

        btnEquals.setOnClickListener {
            equals()
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
        val op = currentOperator ?: return
        val left = firstOperand ?: return
        val right = display.toString().toDoubleOrNull() ?: return
        val result = when (op) {
            "+" -> left + right
            "-" -> left - right
            "x" -> left * right
            "÷"  -> if (right == 0.0) Double.NaN else left / right
            else -> Double.NaN
        }
        tvExpression.text = "${left.stripTrailingZeros()} $op ${right.stripTrailingZeros()} ="
        display.clear().append(result.stripTrailingZeros())
        tvDisplay.text = display

        // reset state
        firstOperand = null
        currentOperator = null
//        if (firstOperand != null && currentOperator != null) {
//            val secondOperand = display.toString().toDoubleOrNull() ?: 0.0
//            tvExpression.text = tvExpression.text.toString() + " " + secondOperand + " ="
//            val result = when (currentOperator) {
//                "+" -> firstOperand!! + secondOperand
//                else -> 0.0
//            }
//            display.clear()
//            display.append(result.toString())
//            tvDisplay.text = display
//            firstOperand = null
//            currentOperator = null
//        }
    }

    private fun plus() = commitOperator("+")
//        if (display.isNotEmpty()) {
//            firstOperand = display.toString().toDoubleOrNull()
//            currentOperator = "+"
//            tvExpression.text = display.toString() + "  $currentOperator"
//            display.clear()
//            tvDisplay.text = ""
//        }
//    }

    private fun subtract() =  commitOperator("-")

    private fun multiply() = commitOperator("x")

    private fun division() = commitOperator("÷")

    private fun percentage() {
        if (display.isEmpty()) return
        val value = display.toString().toDouble() / 100.0
        display.clear().append(value.stripTrailingZeros())
        tvDisplay.text = display
    }

    private fun clearChar() {
//        var display = tvDisplay.text.toString()
        if (display.isNotEmpty()) {
//            display =
            display.deleteCharAt(display.length - 1)
            tvDisplay.text = display
        }
    }

    private fun clearAll() {
        display.clear()
        firstOperand = null
        currentOperator = null
        tvExpression.text = ""
        tvDisplay.text = ""
    }

    private fun commitOperator(op: String) {
        if (display.isEmpty()) return

        firstOperand = display.toString().toDoubleOrNull()
        currentOperator = op
        tvExpression.text = "${firstOperand?.stripTrailingZeros()}  $op"
        display.clear()
        tvDisplay.text = ""
    }

    private fun Double.stripTrailingZeros(): String =
        if (this % 1 == 0.0) this.toInt().toString() else toString()
}

