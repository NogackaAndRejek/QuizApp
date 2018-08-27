package com.example.android.quizapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Get intent and message to display
        val scoreToDisplay = intent.getStringExtra("scoreToDisplay")

        val yourScoreTextView = findViewById<TextView>(R.id.your_score_text_view)
        yourScoreTextView.text = scoreToDisplay
    }

}
