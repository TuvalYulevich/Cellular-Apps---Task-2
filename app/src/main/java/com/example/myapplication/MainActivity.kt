package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.StudentDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.studentsRecyclerView)
        val addStudentButton = findViewById<Button>(R.id.addStudentButton)
        val viewAllStudentsButton = findViewById<Button>(R.id.viewAllStudentsButton)

        adapter = StudentAdapter(StudentDatabase.students, { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("studentId", student.id)
            startActivity(intent)
        }, { student, isChecked ->
            student.isChecked = isChecked
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Add Student button click
        addStudentButton.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }

        // View All Students button click
        viewAllStudentsButton.setOnClickListener {
            if (StudentDatabase.students.isNotEmpty()) {
                // Scroll to the top of the RecyclerView
                recyclerView.smoothScrollToPosition(0)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged() // Refresh RecyclerView with the latest data
    }
}
