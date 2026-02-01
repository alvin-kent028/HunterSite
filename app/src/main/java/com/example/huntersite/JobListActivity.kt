package com.example.huntersite

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JobListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_job)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            // This tells us the click is working
            Toast.makeText(this, "Back button clicked!", Toast.LENGTH_SHORT).show()
            finish()
        }

        // 2. Apply Buttons Logic
        setupApply(R.id.btnApply1, "Senior Full Stack Developer")
        setupApply(R.id.btnApply2, "Solutions Engineer")
        setupApply(R.id.btnApply3, "Apple iOS Developer")
        setupApply(R.id.btnApply4, "Android Developer")
        setupApply(R.id.btnApply5, "Data Engineer")
        setupApply(R.id.btnApply6, "Frontend Developer")
    }

    private fun setupApply(id: Int, title: String) {
        findViewById<Button>(id).setOnClickListener {
            Toast.makeText(this, "Application sent for $title!", Toast.LENGTH_SHORT).show()
        }
    }
}