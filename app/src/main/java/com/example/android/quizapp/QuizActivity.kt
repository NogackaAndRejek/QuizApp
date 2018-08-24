package com.example.android.quizapp

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        var answerAButton = findViewById<Button>(R.id.answareA)
        answerAButton.setOnClickListener{
            answerAButton.setBackgroundColor(R.color.buttonColor)
        }
    }

}