package com.example.huntersite

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PostJobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure this matches the name of your XML file for the post job page
        setContentView(R.layout.activity_post_job)

        // Bind the buttons from your XML
        val btnBackArrow = findViewById<ImageButton>(R.id.btnBackArrow)
        val btnPublish = findViewById<Button>(R.id.btnPublish)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        // 1. Back Arrow Logic
        btnBackArrow.setOnClickListener {
            finish() // Closes this page and goes back to Dashboard
        }

        // 2. Publish Button Logic
        btnPublish.setOnClickListener {
            // Later, you can add code here to save the job to a database
            Toast.makeText(this, "Job Published Successfully!", Toast.LENGTH_LONG).show()
            finish() // Return to dashboard after posting
        }

        // 3. Cancel Button Logic
        btnCancel.setOnClickListener {
            finish() // Just goes back without saving
        }
    }
}