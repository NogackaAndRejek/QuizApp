package com.example.android.quizapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.provider.BaseColumns
import android.util.Log
import android.view.View
import android.widget.*
import com.example.android.quizapp.R.color.incorrectAnswerButton
import com.example.android.quizapp.R.id.message
import com.example.android.quizapp.R.id.question_text_view
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_quiz.*
import java.lang.Long.getLong

class QuizActivity : AppCompatActivity() {
    // Variables
    lateinit var currentQuestion: Question
    var gamePoints: Int = 0
    var questionNumber: Int = 0
    val maxAmoundOfQuestions: Int = 1
    lateinit var questionsArray: Array<Question>
    var playerName: String? = null

    //Question db
    lateinit var dbHelper:DataBaseHelper

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Get message from intent
        playerName = intent.getStringExtra("name")

        // Create questions db
        try {
            //Create Writable DataBase using DataBaseHelper
            dbHelper = DataBaseHelper(applicationContext)
            var db_writable = dbHelper.writableDatabase

            //Values which insert into database using instance of ContentValues
            val values = ContentValues().apply {
                put(DB_INFO.TABLE_COLUMN_QUESTION, R.string.sample_question1)
                put(DB_INFO.TABLE_COLUMN_ANSWER_A, R.string.sample_answer1_a)
                put(DB_INFO.TABLE_COLUMN_ANSWER_B, R.string.sample_answer1_b)
                put(DB_INFO.TABLE_COLUMN_ANSWER_C, R.string.sample_answer1_c)
                put(DB_INFO.TABLE_COLUMN_ANSWER_D, R.string.sample_answer1_d)
                put(DB_INFO.TABLE_COLUMN_CORRECT,  "C")
                put(DB_INFO.TABLE_COLUMN_IMAGE, R.drawable.sample_question_image)
            }
            db_writable?.insert(DB_INFO.TABLE_NAME,null,values)
        }
        catch (e: Exception) {
            Log.d("PROBLEM", "Cos nie poszlo $e")
        }

        // Make sample questions
        val question1 = Question(getString(R.string.sample_question1),
                getString(R.string.sample_answer1_a),
                getString(R.string.sample_answer1_b),
                getString(R.string.sample_answer1_c),
                getString(R.string.sample_answer1_d),
                "C", R.drawable.sample_question_image)

        val question2 = Question(getString(R.string.sample_question2),
                getString(R.string.sample_answer2_a),
                getString(R.string.sample_answer2_b),
                getString(R.string.sample_answer2_c),
                getString(R.string.sample_answer2_d),
                "A", R.drawable.mobile_systems)

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
        var answer = ""
        when(view.id){
            R.id.answer_a -> answer = "A"
            R.id.answer_b -> answer = "B"
            R.id.answer_c -> answer = "C"
            R.id.answer_d -> answer = "D"
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
        // Questions from db
        currentQuestion = getQuestionFromDB()

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

    //Get question from db
    fun getQuestionFromDB(): Question {
        val db = dbHelper.readableDatabase

        // Choose an answer

        // Filter results WHERE "id" = random
        //val selection = "${DB_INFO.COLUMN_ID} = ?"
        //val selectionArgs = arrayOf(random)

        val cursor = db.query(
                DB_INFO.TABLE_NAME,    // The table to query
                null,          // The array of columns to return (pass null to get all)
                null,         // The columns for the WHERE clause
                null,      // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                null           // The sort order
        )

        var question:Question = Question()
        with(cursor) {
            while (moveToNext()) {
                question.question = cursor.getString(getColumnIndex(DB_INFO.TABLE_COLUMN_QUESTION))
                question.answersA = cursor.getString(getColumnIndex(DB_INFO.TABLE_COLUMN_ANSWER_A))
                question.answersB = cursor.getString(getColumnIndex(DB_INFO.TABLE_COLUMN_ANSWER_B))
                question.answersC = cursor.getString(getColumnIndex(DB_INFO.TABLE_COLUMN_ANSWER_C))
                question.answersD = cursor.getString(getColumnIndex(DB_INFO.TABLE_COLUMN_ANSWER_D))
                question.correctAnswer = cursor.getString(getColumnIndex(DB_INFO.TABLE_COLUMN_CORRECT))
                //question.questionImage = cursor.getInt(getColumnIndex(DB_INFO.TABLE_COLUMN_IMAGE))
                questionsArray.plus(question)
            }
        }
        return question
    }

}
