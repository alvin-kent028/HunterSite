package com.example.huntersite

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ApplyJobActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_job)

        // 1. Get the job title passed from JobListActivity
        val jobTitle = intent.getStringExtra("JOB_TITLE") ?: "Job Position"

        // 2. Display the job title in the TextView
        val tvApplyingFor = findViewById<TextView>(R.id.tvApplyingFor)
        tvApplyingFor.text = "Applying for: $jobTitle"

        // 3. Back Button logic
        findViewById<ImageView>(R.id.btnBackToJobs).setOnClickListener {
            finish()
        }

        // 4. Submit Button logic
        findViewById<Button>(R.id.btnSubmitApplication).setOnClickListener {
            Toast.makeText(this, "Application for $jobTitle submitted successfully!", Toast.LENGTH_LONG).show()
            finish() // Close page after submitting
        }
    }
}