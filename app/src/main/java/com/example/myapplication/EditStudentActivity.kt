package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.StudentDatabase

class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Get the student ID passed from StudentDetailsActivity
        val studentId = intent.getStringExtra("studentId")

        // Find the student object from the database
        val student = StudentDatabase.students.find { it.id == studentId }

        // Bind views
        val nameInput = findViewById<EditText>(R.id.nameInput)
        val idInput = findViewById<EditText>(R.id.idInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val checkedInput = findViewById<CheckBox>(R.id.checkedInput)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        // Populate the fields with existing data
        if (student != null) {
            nameInput.setText(student.name)
            idInput.setText(student.id)
            phoneInput.setText(student.phone)
            addressInput.setText(student.address)
            checkedInput.isChecked = student.isChecked
        }

        // Save changes
        saveButton.setOnClickListener {
            if (student != null) {
                student.name = nameInput.text.toString()
                student.id = idInput.text.toString()
                student.phone = phoneInput.text.toString()
                student.address = addressInput.text.toString()
                student.isChecked = checkedInput.isChecked
            }
            finish() // Close the activity
        }

        // Delete the student
        deleteButton.setOnClickListener {
            if (student != null) {
                StudentDatabase.students.remove(student)
            }
            finish() // Close the activity
        }
    }
}
