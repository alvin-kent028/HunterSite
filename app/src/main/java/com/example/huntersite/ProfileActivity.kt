package com.example.huntersite // Replace with your package name

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val navHome = findViewById<TextView>(R.id.navHome)
        val navFindJob = findViewById<TextView>(R.id.navFindJob)

        // LOGOUT: Go back to Login (MainActivity) and clear the history
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show()
            finish()
        }

        // NAV: Back to Dashboard
        navHome.setOnClickListener {
            // Replace with your actual Dashboard Activity class name
            // val intent = Intent(this, DashboardActivity::class.java)
            // startActivity(intent)
            finish()
        }

        navFindJob.setOnClickListener {
            Toast.makeText(this, "Finding Jobs...", Toast.LENGTH_SHORT).show()
        }
    }
}

