package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class JobListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        // Navigation Buttons
        findViewById<TextView>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<TextView>(R.id.navProfile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Get the search query from Intent
        val searchQuery = intent.getStringExtra("SEARCH_QUERY")?.lowercase()?.trim()

        // Setup Jobs and check if they match search
        setupJob(R.id.btnApply1, "Senior Full Stack Developer", searchQuery)
        setupJob(R.id.btnApply2, "Solutions Engineer", searchQuery)
        setupJob(R.id.btnApply3, "Apple iOS Developer", searchQuery)
        setupJob(R.id.btnApply4, "Data Analyst", searchQuery)
        setupJob(R.id.btnApply5, "Android Developer", searchQuery)
        setupJob(R.id.btnApply6, "Social Media Manager", searchQuery)
        setupJob(R.id.btnApply7, "IT Support Specialist", searchQuery)
        
        if (searchQuery != null && searchQuery.isNotEmpty()) {
            Toast.makeText(this, "Showing results for: $searchQuery", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupJob(buttonId: Int, title: String, query: String?) {
        val button = findViewById<Button>(buttonId)
        // Find the CardView which is the grandparent of the button in activity_job_list.xml
        val parentCard = button?.parent?.parent as? CardView
        
        // If there's a search query, hide jobs that don't match the title
        if (query != null && !title.lowercase().contains(query)) {
            parentCard?.visibility = View.GONE
        } else {
            parentCard?.visibility = View.VISIBLE
        }

        button?.setOnClickListener {
            val intent = Intent(this, ApplyJobActivity::class.java)
            intent.putExtra("JOB_TITLE", title)
            startActivity(intent)
        }
    }
}
