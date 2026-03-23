package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SwitchAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_account)

        val btnConfirmSwitch: Button = findViewById(R.id.btnConfirmSwitch)
        val btnCancelSwitch: Button = findViewById(R.id.btnCancelSwitch)

        btnConfirmSwitch.setOnClickListener {
            performSwitchAccount()
        }

        btnCancelSwitch.setOnClickListener {
            finish() // Simply goes back to the previous screen
        }
    }

    private fun performSwitchAccount() {
        // 1. Show feedback to the user
        Toast.makeText(this, "Switching accounts...", Toast.LENGTH_SHORT).show()

        // 2. Point the intent to MainActivity (your login page)
        val intent = Intent(this, MainActivity::class.java)

        // 3. Clear the history so they can't go back to the old session
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(intent)

        // 4. Close this screen
        finish()
    }
}