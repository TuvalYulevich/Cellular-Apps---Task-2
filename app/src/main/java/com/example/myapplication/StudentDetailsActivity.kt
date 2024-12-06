package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.StudentDatabase

class StudentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        // Get the student ID passed from MainActivity
        val studentId = intent.getStringExtra("studentId")

        // Find the student object from the database
        val student = StudentDatabase.students.find { it.id == studentId }

        // Display the student's details
        val nameTextView = findViewById<TextView>(R.id.studentNameTextView)
        val idTextView = findViewById<TextView>(R.id.studentIdTextView)
        val phoneTextView = findViewById<TextView>(R.id.studentPhoneTextView)
        val addressTextView = findViewById<TextView>(R.id.studentAddressTextView)
        val editButton = findViewById<Button>(R.id.editButton)

        if (student != null) {
            nameTextView.text = "Name: ${student.name}"
            idTextView.text = "ID: ${student.id}"
            phoneTextView.text = "Phone: ${student.phone}"
            addressTextView.text = "Address: ${student.address}"
        }

        // Navigate to EditStudentActivity
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("studentId", studentId)
            startActivity(intent)
        }
    }
}
