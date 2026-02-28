package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.TextView // or Button, depending on your XML tag
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure activity_main.xml is the one containing your "Register" button
        setContentView(R.layout.activity_main)

        // 1. Find the "Click here to register" view by its ID
        // Replace 'tvGoToRegister' with the actual ID you set in your XML
        val btnRegisterPage = findViewById<TextView>(R.id.txtRegister)

        // 2. Set the click listener
        btnRegisterPage.setOnClickListener {
            // 3. Create the Intent to move to the RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}