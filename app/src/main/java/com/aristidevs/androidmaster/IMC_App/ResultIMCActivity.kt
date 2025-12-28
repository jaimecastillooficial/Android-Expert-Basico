package com.aristidevs.androidmaster.IMC_App

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.aristidevs.androidmaster.R

class ResultIMCActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)

        val result :Double= intent.extras?.getDouble(MainActivity.Companion.IMC_KEY) ?: -1.0

        initComponents()
        initUI(result)
        initListeners()

        }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }


    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> { //Bajo peso

                tvResult.text = getString(R.string.low_weight)
                tvDescription.text = getString(R.string.description_low_weight)
                tvResult.setTextColor(getColor(R.color.low_weighted))
            }

            in 18.51..24.99 -> { //Peso normal

                tvResult.text= getString(R.string.normal_weight)
                tvDescription.text= getString(R.string.description_normal_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal_weighted))
            }
            in 25.00..29.99 -> { //Sobrepeso

                tvResult.text= getString(R.string.overweight)
                tvDescription.text= getString(R.string.description_overweight)
                tvResult.setTextColor(getColor(R.color.overweighted))
            }
            in 30.00.. 99.00-> { //Obesidad

                tvResult.text= getString(R.string.obesity)
                tvDescription.text= getString(R.string.description_obesity)
                tvResult.setTextColor(getColor(R.color.obesityed))

            }
            else -> { //error
                tvIMC.text = "Error"
                tvResult.text = "Error"
                tvDescription.text= "Error"
            }
        }
    }
        private fun initComponents() {
            tvResult = findViewById(R.id.tvResult)
            tvIMC = findViewById(R.id.tvIMC)
            tvDescription = findViewById(R.id.tvDescription)
            btnRecalculate = findViewById(R.id.btnRecalculate)

        }

}