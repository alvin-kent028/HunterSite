package com.example.huntersite.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JobDao {
    @Insert
    suspend fun insert(job: JobPost): Long

    @Query("SELECT * FROM jobs_table ORDER BY id DESC")
    fun getAllJobs(): LiveData<List<JobPost>>

    @Query("SELECT * FROM jobs_table WHERE id = :id LIMIT 1")
    suspend fun getJobById(id: Int): JobPost?

    @Update
    suspend fun update(job: JobPost): Int

    @Delete
    suspend fun delete(job: JobPost): Int
}
