package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Initialize Views
        val etSearchQuery = findViewById<EditText>(R.id.etSearchQuery)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnApply1 = findViewById<Button>(R.id.btnApply1)
        val tvViewAllJobs = findViewById<TextView>(R.id.tvViewAllJobs)

        // Navigation Views
        val navHome = findViewById<TextView>(R.id.navHome)
        val navFindJob = findViewById<TextView>(R.id.navFindJob)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        // --- SEARCH BUTTON LOGIC ---
        btnSearch.setOnClickListener {
            val query = etSearchQuery.text.toString().trim()
            if (query.isNotEmpty()) {
                val intent = Intent(this, JobListActivity::class.java)
                intent.putExtra("SEARCH_QUERY", query)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter a job title", Toast.LENGTH_SHORT).show()
            }
        }

        // --- APPLY NOW BUTTON LOGIC ---
        btnApply1.setOnClickListener {
            val intent = Intent(this, ApplyJobActivity::class.java)
            // Passing the job title dynamically to the apply screen
            intent.putExtra("JOB_TITLE", "Senior Full Stack Developer")
            startActivity(intent)
        }

        // --- VIEW ALL LOGIC ---
        tvViewAllJobs.setOnClickListener {
            val intent = Intent(this, JobListActivity::class.java)
            startActivity(intent)
        }

        // --- BOTTOM NAVIGATION LOGIC ---
        navHome.setOnClickListener {
            // Already on Dashboard
        }

        navFindJob.setOnClickListener {
            val intent = Intent(this, JobListActivity::class.java)
            startActivity(intent)
        }

        navProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
