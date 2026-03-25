package com.example.huntersite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class ApplyJobActivity : AppCompatActivity() {

    private var selectedFileUri: Uri? = null
    private lateinit var tvResumeFileName: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var progressBar: ProgressBar

    // This is the launcher that opens the file picker
    private val getFileContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedFileUri = uri
            val fileName = getFileName(uri)
            tvResumeFileName.text = "Selected: $fileName"
            tvResumeFileName.setTextColor(resources.getColor(android.R.color.holo_green_dark))
            Toast.makeText(this, "Resume attached!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_job)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        val jobTitle = intent.getStringExtra("JOB_TITLE") ?: "Job Position"
        val tvApplyingFor = findViewById<TextView>(R.id.tvApplyingFor)
        tvResumeFileName = findViewById(R.id.tvResumeFileName)
        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etPortfolio = findViewById<EditText>(R.id.etPortfolio)
        val etCoverLetter = findViewById<EditText>(R.id.etCoverLetter)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitApplication)
        
        // Use a progress bar if available in layout, or we can just disable the button
        // Since I don't see one in the layout, I'll just disable the button and show Toast.

        tvApplyingFor.text = "Applying for: $jobTitle"

        findViewById<ImageView>(R.id.btnBackToJobs).setOnClickListener {
            finish()
        }

        findViewById<LinearLayout>(R.id.btnUploadResume).setOnClickListener {
            getFileContent.launch("application/pdf")
        }

        btnSubmit.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val portfolio = etPortfolio.text.toString().trim()
            val coverLetter = etCoverLetter.text.toString().trim()

            if (fullName.isEmpty()) {
                Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedFileUri == null) {
                Toast.makeText(this, "Please upload your resume first", Toast.LENGTH_SHORT).show()
            } else {
                btnSubmit.isEnabled = false
                btnSubmit.text = "Submitting..."
                uploadResumeAndSubmit(fullName, portfolio, coverLetter, jobTitle, btnSubmit)
            }
        }
    }

    private fun uploadResumeAndSubmit(fullName: String, portfolio: String, coverLetter: String, jobTitle: String, btnSubmit: Button) {
        val user = auth.currentUser
        if (user == null) {
            Toast.makeText(this, "Please login to apply", Toast.LENGTH_SHORT).show()
            btnSubmit.isEnabled = true
            btnSubmit.text = "Submit Application"
            return
        }
        val uid = user.uid
        val fileName = "resumes/$uid/${UUID.randomUUID()}.pdf"
        val storageRef = storage.reference.child(fileName)

        Toast.makeText(this, "Uploading resume...", Toast.LENGTH_SHORT).show()

        storageRef.putFile(selectedFileUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    saveApplicationToFirestore(uid, fullName, portfolio, coverLetter, jobTitle, downloadUri.toString(), btnSubmit)
                }
            }
            .addOnFailureListener {
                btnSubmit.isEnabled = true
                btnSubmit.text = "Submit Application"
                Toast.makeText(this, "Resume upload failed: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun saveApplicationToFirestore(uid: String, fullName: String, portfolio: String, coverLetter: String, jobTitle: String, resumeUrl: String, btnSubmit: Button) {
        val applicationMap = hashMapOf(
            "userId" to uid,
            "fullName" to fullName,
            "portfolio" to portfolio,
            "coverLetter" to coverLetter,
            "jobTitle" to jobTitle,
            "resumeUrl" to resumeUrl,
            "timestamp" to System.currentTimeMillis()
        )

        firestore.collection("applications")
            .add(applicationMap)
            .addOnSuccessListener {
                Toast.makeText(this, "Application for $jobTitle submitted successfully!", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener {
                btnSubmit.isEnabled = true
                btnSubmit.text = "Submit Application"
                Toast.makeText(this, "Submission failed: ${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun getFileName(uri: Uri): String {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = contentResolver.query(uri, null, null, null, null)
            cursor.use { c ->
                if (c != null && c.moveToFirst()) {
                    val index = c.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (index != -1) result = c.getString(index)
                }
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result?.lastIndexOf('/') ?: -1
            if (cut != -1) {
                result = result?.substring(cut + 1)
            }
        }
        return result ?: "Selected File"
    }
}
