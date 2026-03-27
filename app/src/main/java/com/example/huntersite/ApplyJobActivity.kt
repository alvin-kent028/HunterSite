package com.example.huntersite

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ApplyJobActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_job)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val jobTitle = intent.getStringExtra("JOB_TITLE") ?: "Job Position"
        val tvApplyingFor = findViewById<TextView>(R.id.tvApplyingFor)
        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etResumeLink = findViewById<EditText>(R.id.etResumeLink)
        val etPortfolio = findViewById<EditText>(R.id.etPortfolio)
        val etCoverLetter = findViewById<EditText>(R.id.etCoverLetter)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitApplication)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        tvApplyingFor.text = "Applying for: $jobTitle"

        findViewById<ImageView>(R.id.btnBackToJobs).setOnClickListener {
            finish()
        }

        btnSubmit.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val resumeLink = etResumeLink.text.toString().trim()
            val portfolio = etPortfolio.text.toString().trim()
            val coverLetter = etCoverLetter.text.toString().trim()

            if (fullName.isEmpty()) {
                Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (resumeLink.isEmpty()) {
                Toast.makeText(this, "Please provide a link to your resume (Google Drive/Dropbox)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = auth.currentUser
            if (user == null) {
                Toast.makeText(this, "Please login to apply", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            btnSubmit.isEnabled = false
            btnSubmit.text = "Submitting..."
            progressBar.visibility = View.VISIBLE

            saveApplicationToFirestore(user.uid, fullName, resumeLink, portfolio, coverLetter, jobTitle, btnSubmit, progressBar)
        }
    }

    private fun saveApplicationToFirestore(
        uid: String,
        fullName: String,
        resumeUrl: String,
        portfolio: String,
        coverLetter: String,
        jobTitle: String,
        btnSubmit: Button,
        progressBar: ProgressBar
    ) {
        val applicationMap = hashMapOf(
            "userId" to uid,
            "fullName" to fullName,
            "resumeUrl" to resumeUrl,
            "portfolio" to portfolio,
            "coverLetter" to coverLetter,
            "jobTitle" to jobTitle,
            "timestamp" to System.currentTimeMillis()
        )

        firestore.collection("applications")
            .add(applicationMap)
            .addOnSuccessListener {
                Toast.makeText(this, "Application submitted successfully!", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener {
                btnSubmit.isEnabled = true
                btnSubmit.text = "Submit Application"
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Submission failed: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }
}
