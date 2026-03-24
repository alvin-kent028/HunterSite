package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.huntersite.Room.AppDatabase

class PostedJobsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posted_jobs)

        val navHome = findViewById<TextView>(R.id.navHome)
        val navPostedJobs = findViewById<TextView>(R.id.navFindJob)
        val navProfile = findViewById<TextView>(R.id.navProfile)
        
        val rvPostedJobs = findViewById<RecyclerView>(R.id.rvPostedJobs)
        val tvEmptyMessage = findViewById<TextView>(R.id.tvEmptyMessage)

        // Initializing with isEmployer = true
        val adapter = JobAdapter(isEmployer = true)
        rvPostedJobs.adapter = adapter
        rvPostedJobs.layoutManager = LinearLayoutManager(this)

        val database = AppDatabase.getDatabase(this)
        database.jobDao().getAllJobs().observe(this) { jobs ->
            if (jobs.isNullOrEmpty()) {
                tvEmptyMessage.visibility = View.VISIBLE
                rvPostedJobs.visibility = View.GONE
            } else {
                tvEmptyMessage.visibility = View.GONE
                rvPostedJobs.visibility = View.VISIBLE
                adapter.submitList(jobs)
            }
        }

        navHome.setOnClickListener {
            val intent = Intent(this, EmployerDashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        navPostedJobs.setOnClickListener {
            // Already on this page
        }

        navProfile.setOnClickListener {
            val intent = Intent(this, EmployerProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}