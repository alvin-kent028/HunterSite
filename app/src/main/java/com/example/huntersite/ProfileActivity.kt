package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val tvProfileName = findViewById<TextView>(R.id.tvProfileName)
        val tvProfileTitle = findViewById<TextView>(R.id.tvProfileTitle)
        val tvProfileLocation = findViewById<TextView>(R.id.tvProfileLocation)
        val tvProfileEmail = findViewById<TextView>(R.id.tvProfileEmail)
        val tvProfileSkills = findViewById<TextView>(R.id.tvProfileSkills)
        val tvProfileExperience = findViewById<TextView>(R.id.tvProfileExperience)

        val btnEditProfile = findViewById<TextView>(R.id.btnEditProfile)
        val btnSwitchAccount = findViewById<Button>(R.id.btnSwitchAccount)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Fetch User Data from Firestore
        val currentUser = auth.currentUser
        if (currentUser != null) {
            firestore.collection("users").document(currentUser.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        tvProfileName.text = document.getString("name") ?: "No Name"
                        tvProfileEmail.text = "Email: ${document.getString("email") ?: ""}"
                        tvProfileTitle.text = document.getString("title") ?: "No Title"
                        tvProfileLocation.text = document.getString("location") ?: "No Location"
                        tvProfileSkills.text = document.getString("skills") ?: "No skills listed"
                        tvProfileExperience.text = document.getString("experience") ?: "No experience listed"
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error fetching profile: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        // Navigation
        findViewById<TextView>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<TextView>(R.id.navFindJob).setOnClickListener {
            val intent = Intent(this, JobListActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        btnSwitchAccount.setOnClickListener {
            val intent = Intent(this, SwitchAccountActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
