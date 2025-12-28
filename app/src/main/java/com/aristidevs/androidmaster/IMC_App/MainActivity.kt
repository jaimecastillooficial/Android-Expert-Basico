package com.aristidevs.androidmaster.IMC_App

import android.content.Intent
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.aristidevs.androidmaster.R
import com.aristidevs.androidmaster.IMC_App.ResultIMCActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var weight: Int = 50
    private var age: Int = 18

    private lateinit var txtHeight: TextView

    private var currentHeight: Double = 1.20

    private lateinit var viewmale: CardView
    private lateinit var viewfemale: CardView
    private lateinit var rsHeight: RangeSlider

    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
        initUI()


    }

    //Inicio los componentes
    private fun initComponents() {
        viewmale = findViewById(R.id.cardView)
        viewfemale = findViewById(R.id.cardView2)
        txtHeight = findViewById(R.id.txtHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)

    }

    // Inicio los listeners
    private fun initListeners() {
        viewmale.setOnClickListener {

            changeGender()
            setGenderColor()
        }
        viewfemale.setOnClickListener {

            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->

            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toDouble()
            txtHeight.text = "${currentHeight.toInt()} cm"

        }
        btnPlusWeight.setOnClickListener {
            weight += 1

            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            weight -= 1
            setWeight()
        }

        btnPlusAge.setOnClickListener {
            age += 1

            setAge()
        }
        btnSubtractAge.setOnClickListener {
            age -= 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC() : Double{                            // Con esto le digo que utilize "." y no "," es decir 23.4 en lugar de 23,4
        val df = DecimalFormat("#.##", DecimalFormatSymbols(Locale.US))
        val height = currentHeight / 100
        val imc = df.format(weight / (height * height)).toDouble()

        return imc

    }

    private fun setAge() {
        tvAge.text = age.toString()
    }

    private fun setWeight() {
        tvWeight.text = weight.toString()
    }

    // Funcion que cambia el genero to-do el rato a su opuesto (Se ejecuto en los listeners antes de cambiar el color asi el valor cambia antes que el color)
    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    // Cambia el color de la tarjeta pasandole el isSelected ya sea en True o False
    private fun setGenderColor() {
        viewmale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewfemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    // Obtengo el color que necesito aplicar (Si el componente [pasado por parametros que debe ser un isSelected] es true devuelve un color y si es false devuelve otro)
    private fun getBackgroundColor(isComponentSelected: Boolean): Int {


        val maleColor = if (isComponentSelected) {
            R.color.background_component_selected

        } else {
            R.color.background_component

        }
        return ContextCompat.getColor(this, maleColor)
    }


    // inicia los botones en su respectivo color (Como en un principio se inicializa male en true y female en false, es para que salga activado el correcto)
    // es decir inicia la interfaz con sus valores por defecto (podria poner que lo inicie desde una base de datos)
    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}