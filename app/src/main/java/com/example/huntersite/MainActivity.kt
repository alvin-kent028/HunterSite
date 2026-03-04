package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind the views
        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtRegister = findViewById<TextView>(R.id.txtRegister)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupRole)

        btnLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            // Reset errors every time button is clicked
            emailLayout.error = null
            passwordLayout.error = null

            // 1. Check if Email is valid
            if (email.isEmpty()) {
                emailLayout.error = "Email is required"
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLayout.error = "Invalid email format (e.g., name@email.com)"
            }
            // 2. Check if Password is long enough
            else if (password.length < 6) {
                passwordLayout.error = "Password must be at least 6 characters"
            }
            // 3. Success!
            else {
                val selectedId = radioGroup.checkedRadioButtonId
                val role = findViewById<RadioButton>(selectedId).text

                Toast.makeText(this, "Welcome $role!", Toast.LENGTH_SHORT).show()

                // Logic to switch between dashboards based on role
                if (role == "Employer") {
                    // Open the Employer dashboard where they can add jobs
                    val intent = Intent(this, EmployerDashboardActivity::class.java)
                    startActivity(intent)
                } else {
                    // Open the regular Job Seeker dashboard
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}