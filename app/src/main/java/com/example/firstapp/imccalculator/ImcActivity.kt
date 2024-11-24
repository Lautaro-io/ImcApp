package com.example.firstapp.imccalculator

import android.content.Context
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcActivity : AppCompatActivity() {
    private var currentWeight: Int = 60
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentAge:Int = 20
    private var currentHeight:Int = 120
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var slider: RangeSlider
    private lateinit var weightText: TextView
    private lateinit var btnPlus: FloatingActionButton
    private lateinit var btnSubstract: FloatingActionButton
    private lateinit var ageText:TextView
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var btnCalculate:Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) { // constructor
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponets()
        initListener()
        initUI()
    }

    private fun initUI() { //inicializa la interfaz/componentes
        setGenderColor()
        setWeight()
        setAge()
    }

    private fun initComponets() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewWomen)
        tvHeight = findViewById(R.id.heightN)
        slider = findViewById(R.id.rangeSliderHeight)
        btnPlus = findViewById(R.id.btnPlus)
        btnSubstract = findViewById(R.id.btnSubstract)
        weightText = findViewById(R.id.weightText)
        ageText = findViewById(R.id.ageText)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnSubstractAge = findViewById(R.id.btnSubstractAge)
        btnCalculate = findViewById(R.id.btnCalculate)

    }

    private fun initListener() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()

        }
        slider.addOnChangeListener { _, value, _ -> // los atributos q no se usan los remplazamos con _
            val df = DecimalFormat("#.##")
            currentHeight  = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }
        btnPlus.setOnClickListener {

            currentWeight +=1
            setWeight()
        }
        btnSubstract.setOnClickListener {
            if (currentWeight <= 0){
                return@setOnClickListener
            }
            currentWeight -=1
            setWeight()

        }
        btnPlusAge.setOnClickListener {
            currentAge+=1
            setAge()
        }
        btnSubstractAge.setOnClickListener {
            if (currentAge <= 0){
                return@setOnClickListener
            }
            currentAge-=1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calcImc()
            navigateToResult(result)
        }

    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelected: Boolean): Int {
        var colorReference = if (isSelected) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun setWeight(){
        weightText.text = currentWeight.toString()
    }
    private fun setAge(){
        ageText.text = currentAge.toString()
    }

    private fun calcImc():Double{
        val df =DecimalFormat("#,##")
        val imc = currentWeight / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
    }
    private fun navigateToResult(result:Double){
        val intent = Intent(this,ResultImcActivity::class.java)
        intent.putExtra(IMC_KEY,result)
        startActivity(intent)
    }
}