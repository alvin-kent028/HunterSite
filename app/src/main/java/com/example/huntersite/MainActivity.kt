package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

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

            emailLayout.error = null
            passwordLayout.error = null

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

            // Firebase Login
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Logic 1: Success - Move to Dashboard
                        val selectedId = radioGroup.checkedRadioButtonId
                        val radioButton = findViewById<RadioButton>(selectedId)
                        val role = radioButton.text.toString()

                        Toast.makeText(this, "Welcome back, $role!", Toast.LENGTH_SHORT).show()

                        val intent = if (role == "Employer") {
                            Intent(this, EmployerDashboardActivity::class.java)
                        } else {
                            Intent(this, DashboardActivity::class.java)
                        }
                        startActivity(intent)
                        finish()
                    } else {
                        // Handle Failures
                        val exception = task.exception
                        if (exception is FirebaseAuthException) {
                            when (exception.errorCode) {
                                "ERROR_INVALID_EMAIL", "ERROR_USER_NOT_FOUND" -> {
                                    // Logic 2: Account does not exist
                                    emailLayout.error = "No Account found"
                                    Toast.makeText(this, "No Account found", Toast.LENGTH_SHORT).show()
                                }
                                "ERROR_WRONG_PASSWORD" -> {
                                    // Logic 3: Wrong password
                                    passwordLayout.error = "The Password is Incorrect"
                                    Toast.makeText(this, "The Password is Incorrect", Toast.LENGTH_SHORT).show()
                                }
                                "ERROR_USER_DISABLED" -> {
                                    emailLayout.error = "This account has been disabled"
                                }
                                else -> {
                                    // Modern Firebase grouped error (if protection is enabled)
                                    // If you get "INVALID_LOGIN_CREDENTIALS", it's a security catch-all
                                    passwordLayout.error = "Invalid email or password"
                                    Toast.makeText(this, "Login failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
        }

        txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}