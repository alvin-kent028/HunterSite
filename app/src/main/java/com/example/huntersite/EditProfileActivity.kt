package com.example.huntersite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This will link to activity_edit_profile.xml (create this next)
        setContentView(R.layout.activity_edit_profile)
    }
}