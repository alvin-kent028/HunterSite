package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.huntersite.Room.AppDatabase
import com.example.huntersite.Room.JobPost
import kotlinx.coroutines.launch

class PostJobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_job)

        val btnBackArrow = findViewById<ImageButton>(R.id.btnBackArrow)
        val btnPublish = findViewById<Button>(R.id.btnPublish)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        val editJobTitle = findViewById<EditText>(R.id.editJobTitle)
        val editCompanyName = findViewById<EditText>(R.id.editCompanyName)
        val editDescription = findViewById<EditText>(R.id.editDescription)

        val database = AppDatabase.getDatabase(this)

        btnBackArrow.setOnClickListener {
            finish()
        }

        btnPublish.setOnClickListener {
            val title = editJobTitle.text.toString().trim()
            val company = editCompanyName.text.toString().trim()
            val description = editDescription.text.toString().trim()

            if (title.isEmpty() || company.isEmpty()) {
                Toast.makeText(this, "Please fill in the Job Title and Company", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val jobPost = JobPost(title = title, company = company, description = description)
                    database.jobDao().insert(jobPost)
                    
                    Toast.makeText(this@PostJobActivity, "Job Published Successfully!", Toast.LENGTH_LONG).show()

                    val intent = Intent(this@PostJobActivity, PostedJobsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}
