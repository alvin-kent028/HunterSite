package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JobListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_job)


        // 2. Bottom Navigation Buttons
        findViewById<TextView>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.navProfile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // 3. Apply Buttons Logic (Matching your XML IDs)
        setupApply(R.id.btnApply1, "Senior Full Stack Developer")
        setupApply(R.id.btnApply2, "Solutions Engineer")
        setupApply(R.id.btnApply3, "Apple iOS Developer")
        setupApply(R.id.btnApply4, "Android Developer")

        // Note: btnApply5 and btnApply6 are removed because they aren't in your XML
    }

    private fun setupApply(id: Int, title: String) {
        val button = findViewById<Button>(id)
        button?.setOnClickListener {
            // This takes the user to the application form page
            val intent = Intent(this, ApplyJobActivity::class.java)
            intent.putExtra("JOB_TITLE", title)
            startActivity(intent)

            Toast.makeText(this, "Opening application for $title", Toast.LENGTH_SHORT).show()
        }
    }
}