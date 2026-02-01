package com.example.huntersite
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This MUST match the name of your dashboard.xml file
        setContentView(R.layout.dashboard)
    }
}