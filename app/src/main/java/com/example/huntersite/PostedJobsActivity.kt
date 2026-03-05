package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PostedJobsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this matches your XML filename
        setContentView(R.layout.activity_posted_jobs)

        // Bind Navigation IDs from your XML
        val navHome = findViewById<TextView>(R.id.navHome)
        val navPostedJobs = findViewById<TextView>(R.id.navFindJob)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        // 1. Return to Employer Home (Dashboard)
        navHome.setOnClickListener {
            val intent = Intent(this, EmployerDashboardActivity::class.java)
            // This flag ensures we don't keep piling up pages in the background
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        // 2. Already on this page
        navPostedJobs.setOnClickListener {
            // Do nothing or scroll to top
        }

        // 3. Open Employer Profile
        navProfile.setOnClickListener {
            // Make sure you have created EmployerProfileActivity
            val intent = Intent(this, EmployerProfileActivity::class.java)
            startActivity(intent)
        }
    }
}