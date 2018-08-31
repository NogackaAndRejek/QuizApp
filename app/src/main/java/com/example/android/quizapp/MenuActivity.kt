package com.example.android.quizapp

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.android.quizapp.DB_INFO
import com.example.android.quizapp.DB_INFO.TABLE_COLUMN_ANSWER_A
import com.example.android.quizapp.DB_INFO.TABLE_COLUMN_ANSWER_B
import com.example.android.quizapp.DB_INFO.TABLE_COLUMN_ANSWER_C
import com.example.android.quizapp.DB_INFO.TABLE_COLUMN_ANSWER_D
import com.example.android.quizapp.DB_INFO.TABLE_COLUMN_QUESTION
import com.example.android.quizapp.DB_INFO.TABLE_NAME
import kotlinx.android.synthetic.main.activity_menu.*
import java.io.File


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //Set OnClickListener on playButton
        play_menu_button.setOnClickListener{
            //Intent which will start Quiz
            val startGameIntent = Intent(applicationContext, QuizActivity::class.java)

            //Checking and sending in Extras player's name. If empty, user can't start playing
            if(player_name.text.isNullOrEmpty()){
                Toast.makeText(applicationContext,"Proszę podać imię", Toast.LENGTH_SHORT).show()
            }
            else{
                startGameIntent.putExtra("name", "${player_name.text}")
                startActivity(startGameIntent)
            }


        }
        //Set OnClickListener on rankingButton
        ranking_button.setOnClickListener{
            try {

                //Create Writable DataBase using DataBaseHelper
                var dbHelper = DataBaseHelper(applicationContext)
                var db_writable = dbHelper.writableDatabase

                //Values which insert into database using instance of ContentValues
                val values = ContentValues().apply {
                    put("question", "Jaki jest najstarszy język programowania?")
                    put("answer_a", "Java")
                    put("answer_b", "Python")
                    put("answer_c", "C++")
                    put("answer_d", "Pascal")
                    put("correct_answer", "Pascal")
                    put("wasDisplayed", 0)
                }
                db_writable.insert(TABLE_NAME,null,values)
            }
            catch (e: Exception) {
                Log.d("PROBLEM", "Cos nie poszlo $e")
            }


        }

        //Click gitHub icon and move to organisation site
        gitHub_image.setOnClickListener{
            val gitHubSiteWWW = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/NogackaAndRejek"))
            startActivity(gitHubSiteWWW)
        }

    }


}



