package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EmployerDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_dashboard) // Ensure this matches your XML file name

        // 1. Bind the "Post a New Job" Button
        val btnPostJob = findViewById<Button>(R.id.btnPostJob)

        // 2. Bind the Bottom Navigation items
        val navHome = findViewById<TextView>(R.id.navHome)
        val navPostedJobs = findViewById<TextView>(R.id.navFindJob) // This is the ID from your XML
        val navProfile = findViewById<TextView>(R.id.navProfile)

        // --- CLICK LISTENERS ---

        // Open the Post Job Form
        btnPostJob.setOnClickListener {
            val intent = Intent(this, PostJobActivity::class.java)
            startActivity(intent)
        }

        // Home Navigation (Currently stays on this page)
        navHome.setOnClickListener {
            Toast.makeText(this, "You are already home!", Toast.LENGTH_SHORT).show()
        }

        // Open Posted Jobs Page
        navPostedJobs.setOnClickListener {
            val intent = Intent(this, PostedJobsActivity::class.java)
            startActivity(intent)
        }

        // Open Recruiter Profile Page
        navProfile.setOnClickListener {
            val intent = Intent(this, RecruiterProfileActivity::class.java)
            startActivity(intent)
        }
    }
}