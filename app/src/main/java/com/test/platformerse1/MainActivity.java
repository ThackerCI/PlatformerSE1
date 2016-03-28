package com.test.platformerse1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// Author: Isaiah Thacker
// Last Modified: 3/21/16 by Isaiah Thacker
// Iteration 2
// MainActivity defines the activity responsible for displaying and running the main menu.

public class MainActivity extends AppCompatActivity {


    // standard onCreate function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    // start is called when the "Level 1" button is pressed
    public void start(View view) {
        int levelID = 1;

        // create a new intent
        Intent levelIntent = new Intent(MainActivity.this, LevelActivity.class);
        levelIntent.putExtra("levelID", levelID); // put the level ID in the intent
        startActivity(levelIntent);  // go to levelActivity with intent levelIntent
    }
}
