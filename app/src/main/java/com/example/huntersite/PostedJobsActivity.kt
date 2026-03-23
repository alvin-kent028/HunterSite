package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PostedJobsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posted_jobs)

        val navHome = findViewById<TextView>(R.id.navHome)
        val navPostedJobs = findViewById<TextView>(R.id.navFindJob)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        navHome.setOnClickListener {
            val intent = Intent(this, EmployerDashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        navPostedJobs.setOnClickListener {
            // Already on this page
        }

        navProfile.setOnClickListener {
            val intent = Intent(this, EmployerProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}