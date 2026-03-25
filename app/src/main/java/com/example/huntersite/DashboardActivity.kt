package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.example.huntersite.Room.AppDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Initialize Views
        val etSearchQuery = findViewById<EditText>(R.id.etSearchQuery)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnApply1 = findViewById<Button>(R.id.btnApply1)
        val tvViewAllJobs = findViewById<TextView>(R.id.tvViewAllJobs)
        
        val cvAppliedJobs = findViewById<CardView>(R.id.cvAppliedJobs)
        val tvAppliedCount = findViewById<TextView>(R.id.tvAppliedCount)
        
        val cvActiveJobs = findViewById<CardView>(R.id.cvActiveJobs)
        val tvActiveJobsCount = findViewById<TextView>(R.id.tvActiveJobsCount)

        // Navigation Views
        val navHome = findViewById<TextView>(R.id.navHome)
        val navFindJob = findViewById<TextView>(R.id.navFindJob)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        // Update Counts
        updateAppliedJobsCount(tvAppliedCount)
        updateActiveJobsCount(tvActiveJobsCount)

        // --- SEARCH BUTTON LOGIC ---
        btnSearch.setOnClickListener {
            val query = etSearchQuery.text.toString().trim()
            if (query.isNotEmpty()) {
                val intent = Intent(this, JobListActivity::class.java)
                intent.putExtra("SEARCH_QUERY", query)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter a job title", Toast.LENGTH_SHORT).show()
            }
        }

        // --- APPLIED JOBS CARD LOGIC ---
        cvAppliedJobs.setOnClickListener {
            val intent = Intent(this, JobListActivity::class.java)
            intent.putExtra("FILTER_APPLIED", true)
            startActivity(intent)
        }

        // --- ACTIVE JOBS CARD LOGIC ---
        cvActiveJobs.setOnClickListener {
            val intent = Intent(this, JobListActivity::class.java)
            startActivity(intent)
        }

        // --- APPLY NOW BUTTON LOGIC ---
        btnApply1.setOnClickListener {
            val intent = Intent(this, ApplyJobActivity::class.java)
            intent.putExtra("JOB_TITLE", "Senior Full Stack Developer")
            startActivity(intent)
        }

        // --- VIEW ALL LOGIC ---
        tvViewAllJobs.setOnClickListener {
            val intent = Intent(this, JobListActivity::class.java)
            startActivity(intent)
        }

        // --- BOTTOM NAVIGATION LOGIC ---
        navHome.setOnClickListener {
            // Already on Dashboard
        }

        navFindJob.setOnClickListener {
            val intent = Intent(this, JobListActivity::class.java)
            startActivity(intent)
        }

        navProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateAppliedJobsCount(textView: TextView) {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("applications")
            .whereEqualTo("userId", uid)
            .get()
            .addOnSuccessListener { documents ->
                textView.text = documents.size().toString()
            }
            .addOnFailureListener {
                textView.text = "0"
            }
    }

    private fun updateActiveJobsCount(textView: TextView) {
        // Since jobs are currently hardcoded in JobListActivity but also saved in Room
        // let's fetch from Room to show the count of dynamically posted jobs.
        val database = AppDatabase.getDatabase(this)
        database.jobDao().getAllJobs().observe(this) { jobs ->
            // Adding the 7 hardcoded jobs from JobListActivity to the dynamic ones
            val totalActiveJobs = 7 + (jobs?.size ?: 0)
            textView.text = totalActiveJobs.toString()
        }
    }
}
