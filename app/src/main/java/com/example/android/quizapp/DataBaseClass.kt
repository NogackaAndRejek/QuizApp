package com.example.android.quizapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.android.quizapp.DB_INFO.DB_VERSION
import com.example.android.quizapp.DB_INFO.SQL_CREATE_ENTRIES
import com.example.android.quizapp.DB_INFO.SQL_DELETE_ENTRIES
import com.example.android.quizapp.DB_INFO.TABLE_NAME


/**
 * Kotlin object which contains an informations about Question data base
 *
 */

object DB_INFO: BaseColumns{
    const val TABLE_NAME = "question"
    const val DB_VERSION = 1


    /**
     * Data base columns
     */
    const val TABLE_COLUMN_QUESTION = "question"
    const val TABLE_COLUMN_ANSWER_A = "answer_a"
    const val TABLE_COLUMN_ANSWER_B = "answer_b"
    const val TABLE_COLUMN_ANSWER_C = "answer_c"
    const val TABLE_COLUMN_ANSWER_D = "answer_d"
    const val TABLE_COLUMN_DISPLAYED = "was_displayed"
    const val TABLE_COLUMN_IMAGE = "question_image"
    const val TABLE_COLUMN_CORRECT = "correct_answer"


    /**
     *  SQL query. Create table
     */
    const val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$TABLE_COLUMN_QUESTION TEXT," + //question
            "$TABLE_COLUMN_ANSWER_A TEXT,"  + //answer_a
            "$TABLE_COLUMN_ANSWER_B TEXT,"  + //answer_b
            "$TABLE_COLUMN_ANSWER_C TEXT,"  + //answer_c
            "$TABLE_COLUMN_ANSWER_D TEXT,"  + //answer_d
            "$TABLE_COLUMN_CORRECT TEXT"  + //correct answer
            "$TABLE_COLUMN_IMAGE INTEGER" + //id of question's image
            "$TABLE_COLUMN_DISPLAYED INTEGER)"//if was displayed
    /**
     * SQL query. Delete table
     */
    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TABLE_NAME}"
}

/**
 * SQLOpenHelper. Class which help open and update Database.
 */
class DataBaseHelper(context: Context): SQLiteOpenHelper(context,TABLE_NAME,null,DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

}