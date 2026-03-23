package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val btnEditProfile = findViewById<TextView>(R.id.btnEditProfile)
        val btnSwitchAccount = findViewById<Button>(R.id.btnSwitchAccount)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

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
            // Redirect to LogoutActivity for confirmation
            val intent = Intent(this, LogoutActivity::class.java)
            startActivity(intent)
        }
    }
}