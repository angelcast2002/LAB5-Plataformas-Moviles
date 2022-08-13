package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnToastMessege : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnToastMessege = findViewById(R.id.button_iniciar)
        initListeners()
    }

    private fun initListeners(){
        btnToastMessege.setOnClickListener{
            Toast.makeText(this, "Angel Sebastian Castellanos Pineda", Toast.LENGTH_LONG).show()
        }
    }
}