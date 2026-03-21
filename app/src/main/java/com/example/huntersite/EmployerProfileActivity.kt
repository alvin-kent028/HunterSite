package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.huntersite.Room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployerProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_employer)

        // 1. Bind the Profile Data Views (Ensure these IDs match your XML)
        val tvProfileName = findViewById<TextView>(R.id.tvProfileName)
        val tvProfileEmail = findViewById<TextView>(R.id.tvProfileEmail)

        // 2. Bind the Action Buttons
        val btnEditProfile = findViewById<TextView>(R.id.btnEditProfile)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // 3. Bind the Bottom Navigation
        val navHome = findViewById<TextView>(R.id.navHome)
        val navPostedJobs = findViewById<TextView>(R.id.navPostedJobs)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        // --- DATABASE LOGIC: FETCH USER DATA ---

        // Retrieve the email passed from Login/MainActivity
        val userEmail = intent.getStringExtra("USER_EMAIL") ?: ""

        if (userEmail.isNotEmpty()) {
            lifecycleScope.launch(Dispatchers.IO) {
                val db = AppDatabase.getDatabase(applicationContext)
                val user = db.userDao().getUserByEmail(userEmail)

                withContext(Dispatchers.Main) {
                    if (user != null) {
                        // Dynamically set the text from the database
                        tvProfileName.text = "${user.firstName} ${user.lastName}"
                        tvProfileEmail.text = user.email
                    }
                }
            }
        }

        // --- BUTTON LOGIC ---

        btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditEmployerProfileActivity::class.java)
            // Pass the email to Edit activity so it knows which user to update
            intent.putExtra("USER_EMAIL", userEmail)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
        }

        // --- BOTTOM NAVIGATION LOGIC ---

        navHome.setOnClickListener {
            val intent = Intent(this, EmployerDashboardActivity::class.java)
            intent.putExtra("USER_EMAIL", userEmail)
            startActivity(intent)
            finish()
        }

        navPostedJobs.setOnClickListener {
            val intent = Intent(this, PostedJobsActivity::class.java)
            intent.putExtra("USER_EMAIL", userEmail)
            startActivity(intent)
        }

        navProfile.setOnClickListener {
            Toast.makeText(this, "You are on your profile", Toast.LENGTH_SHORT).show()
        }
    }
}
