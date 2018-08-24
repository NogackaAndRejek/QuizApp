package com.example.android.quizapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Chronometer
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


    }

    override fun onResume() {
        super.onResume()

        timer.format = "%m"

    }
}
