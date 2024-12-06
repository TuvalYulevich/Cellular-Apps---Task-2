package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Student
import com.example.myapplication.data.StudentDatabase

class StudentAdapter(
    private val students: MutableList<Student>,
    private val onEdit: (Student) -> Unit,
    private val onCheckChanged: (Student, Boolean) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.studentName)
        val id: TextView = view.findViewById(R.id.studentId)
        val checkbox: CheckBox = view.findViewById(R.id.studentCheckbox)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.name.text = student.name
        holder.id.text = student.id
        holder.checkbox.isChecked = student.isChecked

        // Handle checkbox changes
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            onCheckChanged(student, isChecked)
        }

        // Handle delete button click
        holder.deleteButton.setOnClickListener {
            students.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, students.size)
        }

        // Edit button functionality (row click)
        holder.itemView.setOnClickListener {
            onEdit(student)
        }
    }

    override fun getItemCount(): Int = students.size
}
