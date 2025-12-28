package com.aristidevs.androidmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aristidevs.androidmaster.IMC_App.MainActivity
import com.aristidevs.androidmaster.IMC_App.ResultIMCActivity
import com.aristidevs.androidmaster.settings.SettingsActivity
import com.aristidevs.androidmaster.superheroapp.SuperHeroListActivity
import com.aristidevs.androidmaster.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var btnIMC: Button
    private lateinit var btnTODO: Button
    private lateinit var btnSuperHero: Button

    private lateinit var btnSettings: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        initComponents()
        initListeners()

        }

    private fun initListeners() {
        btnIMC.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnTODO.setOnClickListener {
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        }
        btnSuperHero.setOnClickListener {
            val intent = Intent(this, SuperHeroListActivity::class.java)
            startActivity(intent)
        }
        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initComponents() {
        btnIMC = findViewById(R.id.btnIMC)
        btnTODO = findViewById(R.id.btnTODO)
        btnSuperHero = findViewById(R.id.btnSuperHero)
        btnSettings = findViewById(R.id.btnSettings)
    }
}
