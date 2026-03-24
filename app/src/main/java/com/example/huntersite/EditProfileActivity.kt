package com.example.huntersite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val etName = findViewById<EditText>(R.id.etEditName)
        val etTitle = findViewById<EditText>(R.id.etEditTitle)
        val etLocation = findViewById<EditText>(R.id.etEditLocation)
        val etEmail = findViewById<EditText>(R.id.etEditEmail)
        val etSkills = findViewById<EditText>(R.id.etEditSkills)
        val etExperience = findViewById<EditText>(R.id.etEditExperience)

        val btnSaveChanges = findViewById<Button>(R.id.btnSaveChanges)
        val btnCancelEdit = findViewById<Button>(R.id.btnCancelEdit)

        val currentUser = auth.currentUser
        if (currentUser != null) {
            firestore.collection("users").document(currentUser.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        etName.setText(document.getString("name"))
                        etEmail.setText(document.getString("email"))
                        etTitle.setText(document.getString("title") ?: "")
                        etLocation.setText(document.getString("location") ?: "")
                        etSkills.setText(document.getString("skills") ?: "")
                        etExperience.setText(document.getString("experience") ?: "")
                    }
                }
        }

        btnSaveChanges.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val title = etTitle.text.toString().trim()
            val location = etLocation.text.toString().trim()
            val skills = etSkills.text.toString().trim()
            val experience = etExperience.text.toString().trim()

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Name and Email are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userMap = hashMapOf(
                "name" to name,
                "email" to email,
                "title" to title,
                "location" to location,
                "skills" to skills,
                "experience" to experience
            )

            if (currentUser != null) {
                firestore.collection("users").document(currentUser.uid)
                    .update(userMap as Map<String, Any>)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Update failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        btnCancelEdit.setOnClickListener {
            finish()
        }
    }
}
