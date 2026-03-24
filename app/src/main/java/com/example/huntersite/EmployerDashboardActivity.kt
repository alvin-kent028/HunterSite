package com.example.huntersite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.huntersite.Room.AppDatabase

class EmployerDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_dashboard)

        val btnPostJob = findViewById<Button>(R.id.btnPostJob)
        val tvActiveJobsCount = findViewById<TextView>(R.id.tvActiveJobsCount)
        val tvTotalApplicantsCount = findViewById<TextView>(R.id.tvTotalApplicantsCount)
        val cvActiveListing = findViewById<View>(R.id.cvActiveListing)
        val tvRecentJobTitle = findViewById<TextView>(R.id.tvRecentJobTitle)
        val tvRecentJobInfo = findViewById<TextView>(R.id.tvRecentJobInfo)

        val navHome = findViewById<TextView>(R.id.navHome)
        val navPostedJobs = findViewById<TextView>(R.id.navFindJob)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        val database = AppDatabase.getDatabase(this)

        // Observe Jobs
        database.jobDao().getAllJobs().observe(this) { jobs ->
            val activeJobs = jobs.filter { it.status == "Active" }
            tvActiveJobsCount.text = activeJobs.size.toString()
            
            if (activeJobs.isNotEmpty()) {
                cvActiveListing.visibility = View.VISIBLE
                val latestJob = activeJobs.first()
                tvRecentJobTitle.text = latestJob.title
                // Placeholder for applicants count as we don't have an applicants table yet
                tvRecentJobInfo.text = "0 Applicants • Posted just now"
            } else {
                cvActiveListing.visibility = View.GONE
            }
        }

        // Observe Applicants (Users)
        database.userDao().readAllData().asLiveData().observe(this) { users ->
            tvTotalApplicantsCount.text = users.size.toString()
        }

        btnPostJob.setOnClickListener {
            val intent = Intent(this, PostJobActivity::class.java)
            startActivity(intent)
        }

        navHome.setOnClickListener {
            // Already home
        }

        navPostedJobs.setOnClickListener {
            val intent = Intent(this, PostedJobsActivity::class.java)
            startActivity(intent)
        }

        navProfile.setOnClickListener {
            val intent = Intent(this, EmployerProfileActivity::class.java)
            startActivity(intent)
        }
    }
}