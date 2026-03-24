package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EmployerProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_employer)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // 1. Bind the Profile Data Views
        val tvProfileName = findViewById<TextView>(R.id.tvProfileName)
        val tvProfileTitle = findViewById<TextView>(R.id.tvProfileTitle)
        val tvProfileCompany = findViewById<TextView>(R.id.tvProfileCompany)
        val tvProfileEmail = findViewById<TextView>(R.id.tvProfileEmail)
        val tvProfileHiringFocus = findViewById<TextView>(R.id.tvProfileHiringFocus)

        // 2. Bind the Action Buttons
        val btnEditProfile = findViewById<TextView>(R.id.btnEditProfile)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // 3. Bind the Bottom Navigation
        val navHome = findViewById<TextView>(R.id.navHome)
        val navPostedJobs = findViewById<TextView>(R.id.navPostedJobs)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        // Initial fetch
        fetchUserProfile()

        // --- BUTTON LOGIC ---

        btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditEmployerProfileActivity::class.java)
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

        // --- BOTTOM NAVIGATION LOGIC ---

        navHome.setOnClickListener {
            val intent = Intent(this, EmployerDashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        navPostedJobs.setOnClickListener {
            val intent = Intent(this, PostedJobsActivity::class.java)
            startActivity(intent)
        }

        navProfile.setOnClickListener {
            Toast.makeText(this, "You are on your profile", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh data when returning from EditEmployerProfileActivity
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        val tvProfileName = findViewById<TextView>(R.id.tvProfileName)
        val tvProfileTitle = findViewById<TextView>(R.id.tvProfileTitle)
        val tvProfileCompany = findViewById<TextView>(R.id.tvProfileCompany)
        val tvProfileEmail = findViewById<TextView>(R.id.tvProfileEmail)
        val tvProfileHiringFocus = findViewById<TextView>(R.id.tvProfileHiringFocus)

        val currentUser = auth.currentUser
        if (currentUser != null) {
            firestore.collection("users").document(currentUser.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        tvProfileName.text = document.getString("name") ?: "No Name"
                        tvProfileTitle.text = document.getString("title") ?: "No Title"
                        tvProfileCompany.text = document.getString("company") ?: "No Company"
                        val email = document.getString("email") ?: ""
                        tvProfileEmail.text = if (email.isNotEmpty()) "Business Email: $email" else "No Email"
                        tvProfileHiringFocus.text = document.getString("hiringFocus") ?: "No focus listed"
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error fetching profile: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
