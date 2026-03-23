package com.example.huntersite

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val btnSaveChanges = findViewById<Button>(R.id.btnSaveChanges)
        val btnCancelEdit = findViewById<Button>(R.id.btnCancelEdit)

        btnSaveChanges.setOnClickListener {
            // Placeholder for saving logic
            Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnCancelEdit.setOnClickListener {
            finish() // Simply goes back to ProfileActivity
        }
    }
}