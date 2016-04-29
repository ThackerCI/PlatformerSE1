package com.test.platformerse1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// Author: Isaiah Thacker
// Last Modified: 4/11/16 by Isaiah Thacker
// Iteration 3
// V_MainActivity defines the activity responsible for displaying and running the main menu.

public class V_MainActivity extends AppCompatActivity {


    // standard onCreate function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // start0 is called when the "Tutorial" button is pressed
    public void start0(View view){
        start(0);
    }

    // start1 is called when the "Level 1" button is pressed
    public void start1(View view) {
        start(1);
    }

    public void start(int levelID){
        // create a new intent
        Intent levelIntent = new Intent(V_MainActivity.this, V_LevelActivity.class);
        levelIntent.putExtra("levelID", levelID); // put the level ID in the intent
        startActivity(levelIntent);  // go to levelActivity with intent levelIntent
    }
}
