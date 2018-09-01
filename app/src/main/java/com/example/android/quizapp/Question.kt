package com.example.android.quizapp

import android.database.Cursor

/**
 * Question
 * Class stores question instance in quiz
 */

class Question(var question: String,
               var answersA: String,
               var answersB: String,
               var answersC: String,
               var answersD: String,
               var correctAnswer: String,
               var questionImage: Int){

    constructor() : this(
            question = "",
            answersA = "",
            answersB = "",
            answersC = "",
            answersD = "",
            correctAnswer = "",
            questionImage = -1
        )


    /**
     * isAnswerCorrect
     * Is player answer correct
     * @param playerAnswer
     * @return Boolean (True or False) is player's answer correct
     */
    fun isAnswerCorrect(playerAnswer: String): Boolean {
        return playerAnswer == correctAnswer
    }

}