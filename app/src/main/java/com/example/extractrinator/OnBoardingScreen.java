//there are multiple onboarding screen/tutorial screen related 3rd party dependencies that are in java
//tutorialviewLibrary: https://github.com/msayan/tutorial-view & follow the procedure given in this dependency
//NOTE: In the 'gradle_properties', add the parameter, 'android.enableJetifier': to enable the old depen
// 'android.enableJetifier= true': to enable these old dependencies to be compatible with the new androidX

//After the first 2 major steps: downloaded and added part. Now,
//steps in the document to follow:
//1. extend 'TutorialActivity' & implement its methods
//2. remove 'setContentView(R.layout.activity_on_boarding_screen);' from the xml, as we are gonna use the tutorial screen that they have provided
//3.

//Note: we need 3 onboarding screens, as we have 3 stuffs in my main screen


package com.example.extractrinator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity; //TutorialActivity is a 3rd party dependency

public class OnBoardingScreen extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_on_boarding_screen); removed it as the 'TutorialActivity ' will create its own view, IMPORTANT
        addFragment(new Step.Builder().setTitle("Extract Text From Image") //copied fxn, 3 times
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard1) // int top drawable
                .setSummary("You can use ML Kit to recognize text in images or video, such as the text of a street sign. The main characteristics of this feature are:")
                .build());

        addFragment(new Step.Builder().setTitle("Copy & Use teh Extracted Text in Seconds!") //copied fxn, 3 times
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard2) // int top drawable
                .setSummary("You can use ML Kit to recognize text in images or video, such as the text of a street sign. The main characteristics of this feature are:")
                .build());

        addFragment(new Step.Builder().setTitle("This is header") //copied fxn, 3 times
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard1) // int top drawable
                .setSummary("You can use ML Kit to recognize text in images or video, such as the text of a street sign. The main characteristics of this feature are:")
                .build());

    }

    @Override //copied part
    public void finishTutorial() {
        // Your implementation //tutorial finished
        Intent i= new Intent(this, MainActivity.class); //Linking the mIn Activity after the onboarding screen
        startActivity(i);
        finish();
        //NOW, we need to go to the main screen, where we have the camera icon and do the backend stuff

    }


    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}