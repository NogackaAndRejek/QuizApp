package com.example.android.quizapp

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*

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
            Toast.makeText(applicationContext,"OnClickListener is correct",Toast.LENGTH_SHORT).show()
        }

        //Click gitHub icon and move to organisation site
        gitHub_image.setOnClickListener{
            val gitHubSiteWWW = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/NogackaAndRejek"))
            startActivity(gitHubSiteWWW)
        }

    }


}
