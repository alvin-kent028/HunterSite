package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PostJobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_job)

        // 1. Bind the views from your XML
        val btnBackArrow = findViewById<ImageButton>(R.id.btnBackArrow)
        val btnPublish = findViewById<Button>(R.id.btnPublish)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        // Input fields for validation
        val editJobTitle = findViewById<EditText>(R.id.editJobTitle)
        val editCompanyName = findViewById<EditText>(R.id.editCompanyName)

        // 2. Back Arrow Logic - Returns to Employer Dashboard
        btnBackArrow.setOnClickListener {
            finish()
        }

        // 3. Publish Button Logic - Moves to the Posted Jobs List
        btnPublish.setOnClickListener {
            val title = editJobTitle.text.toString().trim()
            val company = editCompanyName.text.toString().trim()

            if (title.isEmpty() || company.isEmpty()) {
                // Prevent publishing if fields are empty
                Toast.makeText(this, "Please fill in the Job Title and Company", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Job Published Successfully!", Toast.LENGTH_LONG).show()

                // Navigate to the list of posted jobs (The "Pop Up" flow)
                val intent = Intent(this, PostedJobsActivity::class.java)
                startActivity(intent)

                // Close this activity so the user doesn't come back to the form
                finish()
            }
        }

        // 4. Cancel Button Logic - Returns to Dashboard without saving
        btnCancel.setOnClickListener {
            finish()
        }
    }
}