package com.example.huntersite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditEmployerProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile_employer)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // 1. Bind all the EditText fields
        val etName = findViewById<EditText>(R.id.etEditRecruiterName)
        val etTitle = findViewById<EditText>(R.id.etEditRecruiterTitle)
        val etCompany = findViewById<EditText>(R.id.etEditCompanyName)
        val etEmail = findViewById<EditText>(R.id.etEditBusinessEmail)
        val etHiringFocus = findViewById<EditText>(R.id.etEditHiringFocus)

        // 2. Bind the Buttons
        val btnSave = findViewById<Button>(R.id.btnSaveRecruiterChanges)
        val btnCancel = findViewById<Button>(R.id.btnCancelRecruiterEdit)

        // Load current data
        val currentUser = auth.currentUser
        if (currentUser != null) {
            firestore.collection("users").document(currentUser.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        etName.setText(document.getString("name"))
                        etEmail.setText(document.getString("email"))
                        etTitle.setText(document.getString("title") ?: "")
                        etCompany.setText(document.getString("company") ?: "")
                        etHiringFocus.setText(document.getString("hiringFocus") ?: "")
                    }
                }
        }

        // Save Button Logic
        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val title = etTitle.text.toString().trim()
            val company = etCompany.text.toString().trim()
            val hiringFocus = etHiringFocus.text.toString().trim()

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Name and Email are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userMap = hashMapOf(
                "name" to name,
                "email" to email,
                "title" to title,
                "company" to company,
                "hiringFocus" to hiringFocus
            )

            if (currentUser != null) {
                firestore.collection("users").document(currentUser.uid)
                    .update(userMap as Map<String, Any>)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Employer profile updated successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Update failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        // Discard/Cancel Button Logic
        btnCancel.setOnClickListener {
            finish()
        }
    }
}
