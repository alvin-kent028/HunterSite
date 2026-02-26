package com.example.huntersite


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)


        // Initialize Navigation Views
        val navHome = findViewById<TextView>(R.id.navHome)
        val navFindJob = findViewById<TextView>(R.id.navFindJob)
        val btnLogout = findViewById<Button>(R.id.btnLogout)


        // Navigation Listeners
        navHome.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish() // Closes profile so you go "back" to dashboard properly
        }


        navFindJob.setOnClickListener {
            val intent = Intent(this, JobListActivity::class.java)
            startActivity(intent)
        }


        btnLogout.setOnClickListener {
            val intent = Intent(this, LogoutActivity::class.java)
            startActivity(intent)
        }
    }
}
