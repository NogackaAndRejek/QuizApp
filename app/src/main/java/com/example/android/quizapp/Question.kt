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
               var correctAnswer: Char,
               var questionImage: Int){

    /**
     * isAnswerCorrect
     * Is player answer correct
     * @param playerAnswer
     * @return Boolean (True or False) is player's answer correct
     */
    fun isAnswerCorrect(playerAnswer: Char): Boolean {
        return playerAnswer == correctAnswer
    }

}