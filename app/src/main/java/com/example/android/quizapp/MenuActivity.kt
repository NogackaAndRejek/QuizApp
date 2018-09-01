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
            // Here db was created
        }

        //Click gitHub icon and move to organisation site
        gitHub_image.setOnClickListener{
            val gitHubSiteWWW = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/NogackaAndRejek"))
            startActivity(gitHubSiteWWW)
        }

    }


}



