package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmployerProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure this matches your XML file name (e.g., activity_employer_profile.xml)
        setContentView(R.layout.activity_profile_employer)

        // 1. Bind the Action Buttons
        val btnEditProfile = findViewById<TextView>(R.id.btnEditProfile)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // 2. Bind the Bottom Navigation
        val navHome = findViewById<TextView>(R.id.navHome)
        val navPostedJobs = findViewById<TextView>(R.id.navPostedJobs)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        // --- BUTTON LOGIC ---

        // Navigate to the Edit Profile Page
        btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditEmployerProfileActivity::class.java)
            startActivity(intent)
        }

        // Log Out Logic
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Clear the activity stack so they can't go "back" to the profile
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
        }

        // --- BOTTOM NAVIGATION LOGIC ---

        navHome.setOnClickListener {
            val intent = Intent(this, EmployerDashboardActivity::class.java)
            startActivity(intent)
            finish() // Close profile so home is the primary screen
        }

        navPostedJobs.setOnClickListener {
            val intent = Intent(this, PostedJobsActivity::class.java)
            startActivity(intent)
        }

        navProfile.setOnClickListener {
            // Already on Profile
            Toast.makeText(this, "You are on your profile", Toast.LENGTH_SHORT).show()
        }
    }
}