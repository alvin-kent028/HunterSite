package com.example.huntersite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditEmployerProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure this matches your XML file name (e.g., activity_edit_employer_profile.xml)
        setContentView(R.layout.activity_edit_profile_employer)

        // 1. Bind all the EditText fields
        val etName = findViewById<EditText>(R.id.etEditRecruiterName)
        val etTitle = findViewById<EditText>(R.id.etEditRecruiterTitle)
        val etCompany = findViewById<EditText>(R.id.etEditCompanyName)
        val etEmail = findViewById<EditText>(R.id.etEditBusinessEmail)
        val etHiringFocus = findViewById<EditText>(R.id.etEditHiringFocus)

        // 2. Bind the Buttons
        val btnSave = findViewById<Button>(R.id.btnSaveRecruiterChanges)
        val btnCancel = findViewById<Button>(R.id.btnCancelRecruiterEdit)

        // --- CLICK LISTENERS ---

        // Save Button Logic
        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val company = etCompany.text.toString()

            if (name.isNotEmpty() && company.isNotEmpty()) {
                // Here is where you would normally save to a database or SharedPreferences
                Toast.makeText(this, "Profile Updated for $name at $company", Toast.LENGTH_SHORT).show()
                finish() // Go back to the profile view
            } else {
                Toast.makeText(this, "Please fill in Name and Company", Toast.LENGTH_SHORT).show()
            }
        }

        // Discard/Cancel Button Logic
        btnCancel.setOnClickListener {
            // Just close the activity without saving anything
            finish()
        }
    }
}