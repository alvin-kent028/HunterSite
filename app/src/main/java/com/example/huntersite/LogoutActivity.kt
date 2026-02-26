package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LogoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val btnConfirmLogout = findViewById<Button>(R.id.btnConfirmLogout)
        val btnCancelLogout = findViewById<TextView>(R.id.btnCancelLogout)

        // Handle Logout
        btnConfirmLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Clear the activity stack so they can't go back to the profile
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Handle Cancel (Go back to Profile)
        btnCancelLogout.setOnClickListener {
            finish()
        }
    }
}