package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.firstapp.MainActivity
import com.example.firstapp.imccalculator.ImcActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSaludar = findViewById<Button>(R.id.btnSaludarApp)
        val btnIMC = findViewById<Button>(R.id.btnIMCApp)
        btnSaludar.setOnClickListener { navigateToSaludar() }
        btnIMC.setOnClickListener { navigateToImcApp() }
    }

    private fun navigateToSaludar(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToImcApp(){
        val intent = Intent(this, ImcActivity::class.java )
        startActivity(intent)
    }
}