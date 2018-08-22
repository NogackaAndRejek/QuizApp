package com.example.android.quizapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(applicationContext,"Cheking connection with Github", Toast.LENGTH_SHORT).show()
        //Comment
    }
}
