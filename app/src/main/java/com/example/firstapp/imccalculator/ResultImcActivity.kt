package com.example.firstapp.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.R
import com.example.firstapp.imccalculator.ImcActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListener()
    }

    private fun initListener() {
        btnRecalculate.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnCalculate)
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { //bajo
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_bajo_peso)

            }

            in 18.51..24.99 -> { //normal
                tvResult.text = getString(R.string.title_peso_normal)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                tvDescription.text = getString(R.string.description_peso_normal)

            }

            in 25.00..29.99 -> { //sobrepeso
                tvResult.text = getString(R.string.title_sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_sobrepeso))
                tvDescription.text = getString(R.string.description_sobrepeso)

            }

            in 30.00..99.00 -> { //obesidad
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_obesidad))
                tvDescription.text = getString(R.string.description_obesidad)

            }

            else -> { //Error
                tvResult.text = getString(R.string.error)
                tvIMC.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

}