package com.example.huntersite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.huntersite.Room.JobPost

class JobAdapter(private val isEmployer: Boolean = false) : ListAdapter<JobPost, JobAdapter.JobViewHolder>(JobDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
        return JobViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = getItem(position)
        holder.bind(job)
        
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, JobDetailActivity::class.java)
            intent.putExtra("JOB_ID", job.id)
            intent.putExtra("IS_EMPLOYER", isEmployer)
            holder.itemView.context.startActivity(intent)
        }
    }

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvJobTitle: TextView = itemView.findViewById(R.id.tvJobTitle)
        private val tvJobStatus: TextView = itemView.findViewById(R.id.tvJobStatus)

        fun bind(job: JobPost) {
            tvJobTitle.text = job.title
            tvJobStatus.text = "${job.status} • 0 Applicants"
        }
    }

    class JobDiffCallback : DiffUtil.ItemCallback<JobPost>() {
        override fun areItemsTheSame(oldItem: JobPost, newItem: JobPost): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JobPost, newItem: JobPost): Boolean {
            return oldItem == newItem
        }
    }
}