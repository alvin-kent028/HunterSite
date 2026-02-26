package com.example.huntersite

import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ApplyJobActivity : AppCompatActivity() {

    private var selectedFileUri: Uri? = null
    private lateinit var tvResumeFileName: TextView

    // This is the launcher that opens the file picker
    private val getFileContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedFileUri = uri
            // Get the file name from the Uri and update the text
            val fileName = getFileName(uri)
            tvResumeFileName.text = "Selected: $fileName"
            tvResumeFileName.setTextColor(resources.getColor(android.R.color.holo_green_dark))
            Toast.makeText(this, "Resume attached!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_job)

        val jobTitle = intent.getStringExtra("JOB_TITLE") ?: "Job Position"
        val tvApplyingFor = findViewById<TextView>(R.id.tvApplyingFor)
        tvResumeFileName = findViewById(R.id.tvResumeFileName)

        tvApplyingFor.text = "Applying for: $jobTitle"

        // Back Button logic
        findViewById<ImageView>(R.id.btnBackToJobs).setOnClickListener {
            finish()
        }

        // 1. Upload Resume Click Logic
        findViewById<LinearLayout>(R.id.btnUploadResume).setOnClickListener {
            // "application/pdf" restricts the picker to PDF files only
            getFileContent.launch("application/pdf")
        }

        // 2. Submit Button logic
        findViewById<Button>(R.id.btnSubmitApplication).setOnClickListener {
            if (selectedFileUri == null) {
                Toast.makeText(this, "Please upload your resume first", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Application for $jobTitle submitted successfully!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    // Helper function to extract file name from Uri
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