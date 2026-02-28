package com.example.huntersite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SwitchAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This links to the switch account XML we made earlier
        setContentView(R.layout.activity_switch_account)
    }
}