package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.huntersite.R // FIX 2: Manually import R if it stays red

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        // Using explicit types to fix the "Cannot infer type" error
        val btnEditProfile = findViewById<TextView>(R.id.btnEditProfile)
        val btnSwitchAccount = findViewById<Button>(R.id.btnSwitchAccount)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        btnSwitchAccount.setOnClickListener {
            val intent = Intent(this, SwitchAccountActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            // Fix for the 'flags' error
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
        }
    }
}