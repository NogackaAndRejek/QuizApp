package com.example.android.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.*
import com.example.android.quizapp.R.color.incorrectAnswerButton
import com.example.android.quizapp.R.id.message
import com.example.android.quizapp.R.id.question_text_view
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    // Variables
    lateinit var currentQuestion: Question
    var gamePoints: Int = 0
    var questionNumber: Int = 0
    val maxAmoundOfQuestions: Int = 2
    lateinit var questionsArray: Array<Question>
    var playerName: String? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Get message from intent
        playerName = intent.getStringExtra("name")

        // Make sample questions
        val question1 = Question(getString(R.string.sample_question1),
                getString(R.string.sample_answer1_a),
                getString(R.string.sample_answer1_b),
                getString(R.string.sample_answer1_c),
                getString(R.string.sample_answer1_d),
                'C', R.drawable.sample_question_image)

        val question2 = Question(getString(R.string.sample_question2),
                getString(R.string.sample_answer2_a),
                getString(R.string.sample_answer2_b),
                getString(R.string.sample_answer2_c),
                getString(R.string.sample_answer2_d),
                'A', R.drawable.mobile_systems)

        questionsArray = arrayOf(question1, question2)

        loadNextQuestionToViews()
        refreshActivityViews()
    }

    @SuppressLint("ResourceAsColor")

    /**
     * clickAnswerButton
     * Check if answer is correct, refresh activity
     * and load next question
     * @param view one of the answer buttons
     * @return Nothing
     */
    fun clickAnswerButton(view: View){
        // Get current answer letter
        var answer = ' '
        when(view.id){
            R.id.answer_a -> answer = 'A'
            R.id.answer_b -> answer = 'B'
            R.id.answer_c -> answer = 'C'
            R.id.answer_d -> answer = 'D'
        }

        // Check if correct
        if (currentQuestion.isAnswerCorrect(answer)){
            gamePoints++
        }

        // Refresh and load next question
        refreshActivityViews()
        loadNextQuestionToViews()
    }

    fun loadNextQuestionToViews(){
        questionNumber++
        // It isn't the last question
        if(questionNumber <= maxAmoundOfQuestions){
            currentQuestion = questionsArray[questionNumber-1]

            // Set controls
            question_text_view.text = currentQuestion.question
            question_image_view.setImageResource(currentQuestion.questionImage)
            answer_a.text = currentQuestion.answersA
            answer_b.text = currentQuestion.answersB
            answer_c.text = currentQuestion.answersC
            answer_d.text = currentQuestion.answersD
        }
        // If it was last question show score
        else{
            val scoreToDisplay = "$playerName ${getString(R.string.your_score)} $gamePoints"

            val newIntent = Intent(this, ScoreActivity::class.java).apply {
                putExtra("scoreToDisplay", scoreToDisplay)
            }
            startActivity(newIntent)
        }
    }

    fun refreshActivityViews(){
        score_text_view.setText("$gamePoints")
        question_number_text_view.text = "$questionNumber/$maxAmoundOfQuestions"
    }

}
