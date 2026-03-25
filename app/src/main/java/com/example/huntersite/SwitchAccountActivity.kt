package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class SwitchAccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_account)

        auth = FirebaseAuth.getInstance()

        // Bind Views from activity_switch_account.xml
        val emailLayout = findViewById<TextInputLayout>(R.id.regEmailLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.regPasswordLayout)
        val editEmail = findViewById<TextInputEditText>(R.id.editRegEmail)
        val editPassword = findViewById<TextInputEditText>(R.id.editRegPassword)
        val btnConfirmSwitch = findViewById<Button>(R.id.btnConfirmSwitch)
        val btnCancelSwitch = findViewById<Button>(R.id.btnCancelSwitch)

        btnConfirmSwitch.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            // Reset errors
            emailLayout.error = null
            passwordLayout.error = null

            // Simple Validation
            if (email.isEmpty()) {
                emailLayout.error = "Email is required"
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLayout.error = "Invalid email format"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                passwordLayout.error = "Password is required"
                return@setOnClickListener
            }

            // Firebase Sign In Logic
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Success: Go to Dashboard
                        Toast.makeText(this, "Switched successfully!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, DashboardActivity::class.java)
                        // Clear activity stack so they can't "back" into the old account
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        val exception = task.exception
                        handleLoginError(exception, email, emailLayout, passwordLayout)
                    }
                }
        }

        btnCancelSwitch.setOnClickListener {
            finish() // Just goes back to the previous screen
        }
    }

    private fun handleLoginError(
        exception: Exception?,
        email: String,
        emailLayout: TextInputLayout,
        passwordLayout: TextInputLayout
    ) {
        when (exception) {
            is FirebaseAuthInvalidUserException -> {
                // User does not exist in Firebase
                emailLayout.error = "No Account found"
                Toast.makeText(this, "No Account found", Toast.LENGTH_LONG).show()
            }
            is FirebaseAuthInvalidCredentialsException -> {
                /* Firebase often uses this for BOTH wrong password AND wrong email
                   for security. We check if the email exists to be specific.
                */
                auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val result = task.result?.signInMethods
                        if (result.isNullOrEmpty()) {
                            emailLayout.error = "No Account found"
                            Toast.makeText(this, "No Account found", Toast.LENGTH_LONG).show()
                        } else {
                            passwordLayout.error = "Incorrect password"
                            Toast.makeText(this, "Incorrect password", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            else -> {
                Toast.makeText(this, "Error: ${exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}