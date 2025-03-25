package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Welcome : AppCompatActivity() {
    private lateinit var welcometext1: TextView
    private lateinit var welcometext2: TextView
    private lateinit var welcomebuttonnext1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        welcometext1 = findViewById(R.id.welcometext1)
        welcometext2 = findViewById(R.id.welcometext2)
        welcomebuttonnext1 = findViewById(R.id.welcomebuttonnext1)

        welcomebuttonnext1.setOnClickListener {
            val intent = Intent(this, Weight::class.java)
            startActivity(intent)
        }
    }
}
