package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PostedJobsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this matches your XML filename (e.g., activity_posted_jobs.xml)
        setContentView(R.layout.activity_posted_jobs)

        // Bind Navigation IDs from your XML
        val navHome = findViewById<TextView>(R.id.navHome)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        // 1. Return to Employer Home
        navHome.setOnClickListener {
            val intent = Intent(this, EmployerDashboardActivity::class.java)
            // Use Flags to prevent opening multiple copies of the same page
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        // 2. Open Employer Profile
        navProfile.setOnClickListener {
            // Ensure you have an EmployerProfileActivity created
            val intent = Intent(this, EmployerProfileActivity::class.java)
            startActivity(intent)
        }
    }
}