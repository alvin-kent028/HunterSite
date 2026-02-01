package com.example.huntersite

import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val txtBackToLogin = findViewById<TextView>(R.id.txtBackToLogin)

        // Layouts for error messages
        val nameLayout = findViewById<TextInputLayout>(R.id.nameLayout)
        val emailLayout = findViewById<TextInputLayout>(R.id.regEmailLayout)
        val passLayout = findViewById<TextInputLayout>(R.id.regPasswordLayout)
        val confirmPassLayout = findViewById<TextInputLayout>(R.id.confirmPasswordLayout)

        btnRegister.setOnClickListener {
            val name = findViewById<EditText>(R.id.editName).text.toString().trim()
            val email = findViewById<EditText>(R.id.editRegEmail).text.toString().trim()
            val pass = findViewById<EditText>(R.id.editRegPassword).text.toString()
            val confirmPass = findViewById<EditText>(R.id.editConfirmPassword).text.toString()

            // Reset errors
            nameLayout.error = null
            emailLayout.error = null
            passLayout.error = null
            confirmPassLayout.error = null

            // Validation Logic
            if (name.isEmpty()) {
                nameLayout.error = "Please enter your full name"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLayout.error = "Please enter a valid email"
            } else if (pass.length < 6) {
                passLayout.error = "Password is too short"
            } else if (pass != confirmPass) {
                confirmPassLayout.error = "Passwords do not match!"
            } else {
                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_LONG).show()
                finish() // Goes back to Login screen
            }
        }

        txtBackToLogin.setOnClickListener {
            finish() // Closes this screen and goes back
        }
    }
}