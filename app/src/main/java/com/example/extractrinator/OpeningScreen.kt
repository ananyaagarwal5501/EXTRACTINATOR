//OPENING SCREEN OF THE APP

package com.example.extractrinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class OpeningScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening_screen)

        supportActionBar?.hide() //hiding the top bar of the app, need a full  screen view

        Handler(Looper.getMainLooper()).postDelayed( //post delayed method
            {
                val i= Intent(this, OnBoardingScreen::class.java)//here, passing the 2 parameters,Linking the Onboarding Activity to open just after this
                startActivity(i)//starting the activity
                finish()//finish intent
            }, 3000) //time in milisec for how long to keep the flash screen


    }
}





//1. xml set

//OPENING SCREEN [SPLASH SCREEN]
//2.  'supportActionBar?.hide()' : hiding the top bar of the app

//3.