package com.example.firstapp.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn = findViewById<AppCompatButton>(R.id.BtnSubmit)
        val userName = findViewById<AppCompatEditText>(R.id.UserID)
        userName.text.toString()
        btn.setOnClickListener {
            val name = userName.text.toString()
            if (name.isNotEmpty()){
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME",name)
                startActivity(intent)
            }

        }
    }
}