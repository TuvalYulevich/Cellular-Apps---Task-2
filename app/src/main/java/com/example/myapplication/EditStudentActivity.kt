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

        val studentId = intent.getStringExtra("studentId")
        val student = StudentDatabase.students.find { it.id == studentId }

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val idInput = findViewById<EditText>(R.id.idInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val addressInput = findViewById<EditText>(R.id.addressInput)
        val checkedInput = findViewById<CheckBox>(R.id.checkedInput)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val goBackButton = findViewById<Button>(R.id.goBackButton)

        // Pre-fill student data
        nameInput.setText(student?.name)
        idInput.setText(student?.id)
        phoneInput.setText(student?.phone)
        addressInput.setText(student?.address)
        checkedInput.isChecked = student?.isChecked == true

        // Save button
        saveButton.setOnClickListener {
            student?.name = nameInput.text.toString()
            student?.id = idInput.text.toString()
            student?.phone = phoneInput.text.toString()
            student?.address = addressInput.text.toString()
            student?.isChecked = checkedInput.isChecked
            finish() // Return to the students list after saving
        }

        // Go Back button
        goBackButton.setOnClickListener {
            finish() // Return to the students list without saving
        }
    }
}
