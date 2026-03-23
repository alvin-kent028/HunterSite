package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JobListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_job)

        // Navigation Buttons
        findViewById<TextView>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.navProfile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Apply Buttons Logic
        setupApply(R.id.btnApply1, "Senior Full Stack Developer")
        setupApply(R.id.btnApply2, "Solutions Engineer")
        setupApply(R.id.btnApply3, "Apple iOS Developer")
        setupApply(R.id.btnApply4, "Data Analyst")
        setupApply(R.id.btnApply5, "Android Developer")
        setupApply(R.id.btnApply6, "Social Media Manager")
        setupApply(R.id.btnApply7, "IT Support Specialist")
    }

    private fun setupApply(id: Int, title: String) {
        val button = findViewById<Button>(id)
        button?.setOnClickListener {
            val intent = Intent(this, ApplyJobActivity::class.java)
            intent.putExtra("JOB_TITLE", title)
            startActivity(intent)
            Toast.makeText(this, "Opening application for $title", Toast.LENGTH_SHORT).show()
        }
    }
}