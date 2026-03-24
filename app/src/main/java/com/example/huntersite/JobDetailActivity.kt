package com.example.huntersite

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.example.huntersite.Room.AppDatabase
import kotlinx.coroutines.launch

class JobDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvCompany = findViewById<TextView>(R.id.tvDetailCompany)
        val tvStatus = findViewById<TextView>(R.id.tvDetailStatus)
        val tvDescription = findViewById<TextView>(R.id.tvDetailDescription)
        val btnApply = findViewById<Button>(R.id.btnApply)

        val jobId = intent.getIntExtra("JOB_ID", -1)
        val isEmployer = intent.getBooleanExtra("IS_EMPLOYER", false)

        if (jobId != -1) {
            val database = AppDatabase.getDatabase(this)
            lifecycleScope.launch {
                val job = database.jobDao().getJobById(jobId)
                job?.let {
                    tvTitle.text = it.title
                    tvCompany.text = it.company
                    tvStatus.text = "Status: ${it.status}"
                    tvDescription.text = it.description
                    
                    if (!isEmployer) {
                        btnApply.visibility = View.VISIBLE
                        btnApply.setOnClickListener {
                            // Link to ApplyJobActivity if needed
                        }
                    }
                }
            }
        }
    }
}