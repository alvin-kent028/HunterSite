package com.example.huntersite.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs_table")
data class JobPost(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val company: String,
    val description: String,
    val status: String = "Active"
)
