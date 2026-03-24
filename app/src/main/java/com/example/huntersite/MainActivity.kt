package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

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

            // Reset errors
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
                        val selectedId = radioGroup.checkedRadioButtonId
                        val radioButton = findViewById<RadioButton>(selectedId)
                        val role = radioButton.text.toString()

                        Toast.makeText(this, "Welcome back, $role!", Toast.LENGTH_SHORT).show()

                        if (role == "Employer") {
                            val intent = Intent(this, EmployerDashboardActivity::class.java)
                            startActivity(intent)
                        } else {
                            val intent = Intent(this, DashboardActivity::class.java)
                            startActivity(intent)
                        }
                        finish()
                    } else {
                        val exception = task.exception
                        when (exception) {
                            is FirebaseAuthInvalidUserException -> {
                                // Specific case: Account not found
                                Toast.makeText(this, "No account found with this email. Please register first.", Toast.LENGTH_LONG).show()
                                emailLayout.error = "Account not found"
                                // Also matching the "Login Failed Incorrect Email" request via a helper or specific message if preferred
                                // But the Toast is what the user explicitly asked for
                            }
                            is FirebaseAuthInvalidCredentialsException -> {
                                // This usually covers both wrong password and sometimes malformed email
                                // But specifically for "Incorrect Password"
                                Toast.makeText(this, "Login Failed Incorrect Password", Toast.LENGTH_LONG).show()
                                passwordLayout.error = "Incorrect Password"
                            }
                            else -> {
                                // General failure
                                Toast.makeText(this, "Login Failed: ${exception?.message}", Toast.LENGTH_SHORT).show()
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
