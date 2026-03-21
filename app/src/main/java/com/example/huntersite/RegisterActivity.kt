package com.example.huntersite

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val txtBackToLogin = findViewById<TextView>(R.id.txtBackToLogin)

        val editName = findViewById<EditText>(R.id.editName)
        val editEmail = findViewById<EditText>(R.id.editRegEmail)
        val editPass = findViewById<EditText>(R.id.editRegPassword)
        val editConfirmPass = findViewById<EditText>(R.id.editConfirmPassword)

        val nameLayout = findViewById<TextInputLayout>(R.id.nameLayout)
        val emailLayout = findViewById<TextInputLayout>(R.id.regEmailLayout)
        val passLayout = findViewById<TextInputLayout>(R.id.regPasswordLayout)
        val confirmPassLayout = findViewById<TextInputLayout>(R.id.confirmPasswordLayout)

        btnRegister.setOnClickListener {
            val name = editName.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val pass = editPass.text.toString()
            val confirmPass = editConfirmPass.text.toString()

            // Reset errors
            nameLayout.error = null
            emailLayout.error = null
            passLayout.error = null
            confirmPassLayout.error = null

            if (name.isEmpty()) {
                nameLayout.error = "Please enter your full name"
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLayout.error = "Please enter a valid email"
                return@setOnClickListener
            }
            if (pass.length < 6) {
                passLayout.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }
            if (pass != confirmPass) {
                confirmPassLayout.error = "Passwords do not match!"
                return@setOnClickListener
            }

            // Firebase Registration
            auth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener { result ->
                    val uid = result.user?.uid ?: return@addOnSuccessListener

                    val userMap = hashMapOf(
                        "uid" to uid,
                        "name" to name,
                        "email" to email
                    )

                    firestore.collection("users")
                        .document(uid)
                        .set(userMap)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Firestore Error: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Registration Failed: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }

        txtBackToLogin.setOnClickListener {
            finish()
        }
    }
}
