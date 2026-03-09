package com.example.huntersite.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JobDao {
    @Insert
    suspend fun insert(job: JobPost) // CREATE

    @Query("SELECT * FROM jobs_table ORDER BY id DESC")
    fun getAllJobs(): LiveData<List<JobPost>> // READ (Using LiveData as required)

    @Update
    suspend fun update(job: JobPost) // UPDATE

    @Delete
    suspend fun delete(job: JobPost) // DELETE
}