package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Student

class StudentAdapter(
    private val students: List<Student>,
    private val onStudentAction: (Student, String) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.studentImage)
        val name: TextView = view.findViewById(R.id.studentName)
        val id: TextView = view.findViewById(R.id.studentId)
        val checked: CheckBox = view.findViewById(R.id.studentChecked)
        val viewButton: Button = view.findViewById(R.id.viewButton)
        val editButton: Button = view.findViewById(R.id.editButton)
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
        holder.checked.isChecked = student.isChecked
        holder.image.setImageResource(R.drawable.student_placeholder)

        holder.checked.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
        }

        holder.viewButton.setOnClickListener { onStudentAction(student, "view") }
        holder.editButton.setOnClickListener { onStudentAction(student, "edit") }
        holder.deleteButton.setOnClickListener { onStudentAction(student, "delete") }
    }

    override fun getItemCount() = students.size
}
