package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Find the "View all" text by its ID
        val viewAllJobs = findViewById<TextView>(R.id.tvViewAllJobs)

        // Set the click listener
        viewAllJobs.setOnClickListener {
            // This is the "Intent" that opens the list screen
            val intent = Intent(this, JobListActivity::class.java)
            startActivity(intent)
        }
    }
}