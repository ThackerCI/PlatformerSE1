package com.test.platformerse1;

// Author: Isaiah Thacker
// Last Modified: 4/01/16 by John C. Hale
//Iteration 3
//Added music.
// Iteration 2
// LevelActivity defines the activity responsible for displaying and running the in-game
// environment.

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class V_LevelActivity extends AppCompatActivity {
    private double levelTime;
    private Chronometer timeKeeper;
    // set up the game loop timer
    private final Timer gameLoopTimer = new Timer();
    // set up an environment
    private final M_Environment environment = M_Environment.getInstance();
    // set up flags for if the level is started and running
    private boolean started = false;
    private boolean running = false;
    // integer used for getting the desired level's ID
    private int savedLevelInfo;
    // constant is the reciprocal of the framerate
    private final int FRAME_DURATION = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        // go fullscreen and force landscape orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // get the bundle of saved stuff
        Bundle savedStuff = getIntent().getExtras();
        // get the level and character info from the bundle
        savedLevelInfo = (int) savedStuff.getSerializable("levelID");


    }

    @Override
    protected void onStart() {
        super.onStart();

        // initialize the level in question
        initLevel(savedLevelInfo);


        // define a TimerTask "refresh" to be called every time the game updates
        TimerTask refresh = new TimerTask() {
            @Override
            public void run() {
                // if the game isn't paused (or stopped for some other reason)
                if (running) {
                    // run the update function. If the player hasn't reached the goal, update the views
                    if (!C_EnvironmentController.update()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateCharacterView();
                                updateBulletsView();
                                updateEnemyView();
                            }
                        });
                    } // else, return to the main menu
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                displayEndscreen();

                            }
                        });
                    }
                }
            }
        };
        // set refresh rate to once every 1/30th second, starting .5 seconds after creation.
        gameLoopTimer.schedule(refresh, 500, FRAME_DURATION);
    }

    private void displayEndscreen() {
        // game is no longer running
        running = false;
        timeKeeper.stop();
        //delete timeKeeper?
        TextView time = (TextView) findViewById(R.id.current_time);
        levelTime = (SystemClock.elapsedRealtime() - timeKeeper.getBase()) / 1000.0; //convert milliseconds to seconds
        assert time != null;
        time.setVisibility(View.VISIBLE);
        time.setText("Your time for this level is: " + levelTime + " seconds.");
        time.bringToFront();
        // display the congratulatory text and menu button
        TextView textView = (TextView) findViewById(R.id.congrats_text);
        assert textView != null;
        textView.setVisibility(View.VISIBLE);
        textView.bringToFront();
        Button button = (Button) findViewById(R.id.return_button);
        assert button != null;
        button.setVisibility(View.VISIBLE);
        button.setClickable(true);
        button.bringToFront();
        // set what the button will do when clicked.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                V_LevelActivity.this.finish();
            }
        });
    }


    // set the timer to cancel when the activity stops
    @Override
    protected void onStop() {
        super.onStop();
        gameLoopTimer.cancel();
    }

    // initialize level i (currently set to always initialize level 1. Will be altered when new
    // levels are added
    public void initLevel(int i) {
        // load the level and the player character into the environment
        C_EnvironmentController.initialize(M_LevelVault.levelOne(), M_Character.getInstance());
        // signal that the level has started
        started = true;
        // initialize the ImageViews
        initView();
        // signal that the level is not paused
        running = true;
    }

    // initialize the activity's views
    private void initView() {
        initBlocksView();
        initRecordsView();
        updateEnemyView();
        updateCharacterView();
    }

    // initialize the view for the blocks
    private void initBlocksView() {
        // iterate through the blocks in the environment
        List<M_Block> blocks = environment.getBlocks();
        for (int i = 0; i < blocks.size(); ++i) {
            M_Block tempMBlock = blocks.get(i); // get the current block
            // Much of the following code was adapted from principles on stackoverflow
            ImageView imageView = new ImageView(V_LevelActivity.this); // create a new ImageView
            imageView.setImageResource(R.drawable.block);            // set the "block" sprite to it
            // get the level layout
            RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);
            // get the dimensions for the sprite and convert them for the device's screen
            int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempMBlock.getDimensions().x, getResources().getDisplayMetrics());
            int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempMBlock.getDimensions().y, getResources().getDisplayMetrics());
            // create new layout parameters for the sprite
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
            // get the location of the block and convert the coordinates for the device's screen
            int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tempMBlock.getLocation().x, getResources().getDisplayMetrics());
            int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tempMBlock.getLocation().y, getResources().getDisplayMetrics());
            // set the margins for the ImageView (i.e. position on the screen)
            layoutParams.setMargins(newX, newY, 0, 0);
            // add the ImageView to the layout
            assert RL != null;
            RL.addView(imageView, layoutParams);

        }
    }


    // update the ImageViews for the bullets
    private void updateBulletsView() {
        // iterate through the bullets in the environment
        List<M_Bullet> bullets = environment.getBullets();
        for (int i = 0; i < bullets.size(); ++i) {
            boolean flag = false;
            ImageView imageView;
            M_Bullet tempMBullet = bullets.get(i); // get the current bullet
            if (tempMBullet.getBulletView() == null) {
                flag = true;
                imageView = new ImageView(V_LevelActivity.this); // create a new ImageView
                tempMBullet.setBulletView(imageView); // used for deleting said view on bullet despawn
                imageView.setImageResource(tempMBullet.getSprite()); // set the bullet's sprite to it
            } else {
                imageView = (ImageView) tempMBullet.getBulletView();
            }
            // get the level layout
            RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);
            // if the bullet is flagged for removal
            if (tempMBullet.getFlag()) {
                // destroy its view and remove it from the bullet list
                imageView.setVisibility(View.INVISIBLE);
                bullets.remove(i);
                --i;
                continue;
            }
            // Much of the following code was adapted from principles on stackoverflow
            // get the dimensions for the sprite and convert them for the device's screen
            int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempMBullet.getDimensions().x, getResources().getDisplayMetrics());
            int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempMBullet.getDimensions().y, getResources().getDisplayMetrics());
            // create new layout parameters for the sprite
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
            // get the location of the block and convert the coordinates for the device's screen
            int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempMBullet.getLocation().x, getResources().getDisplayMetrics());
            int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempMBullet.getLocation().y, getResources().getDisplayMetrics());
            // set the margins for the ImageView (i.e. position on the screen)
            layoutParams.setMargins(newX, newY, 0, 0);
            // add the ImageView to the layout, or update it if it's already there.
            if (flag) {
                assert RL != null;
                RL.addView(imageView, layoutParams);
            } else {
                imageView.setLayoutParams(layoutParams);
            }
        }
    }

    // initialize the views for the records.
    private void initRecordsView() {
        // TODO: add loop for additional records. Currently just doing the goal, since that's all
        // we have.
        M_Record tempMRecord = environment.getGoal(); // get the goal record
        // Much of the following code was adapted from principles on stackoverflow
        ImageView imageView = new ImageView(V_LevelActivity.this); // create a new ImageView
        imageView.setImageResource(R.mipmap.goal_record);            // set the "block" sprite to it
        // get the level layout
        RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);

        // get the dimensions for the sprite and convert them for the device's screen
        int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempMRecord.getDimensions().x, getResources().getDisplayMetrics());
        int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempMRecord.getDimensions().y, getResources().getDisplayMetrics());
        // create new layout parameters for the sprite
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
        // get the location of the block and convert the coordinates for the device's screen
        int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempMRecord.getLocation().x, getResources().getDisplayMetrics());
        int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempMRecord.getLocation().y, getResources().getDisplayMetrics());

        // set the margins for the ImageView (i.e. position on the screen)
        layoutParams.setMargins(newX, newY, 0, 0);
        // add the ImageView to the layout
        assert RL != null;
        RL.addView(imageView, layoutParams);

    }

    // update the character's ImageView
    private void updateCharacterView() {
        M_Character character = M_Character.getInstance();
        // Much of the following code was adapted from principles on stackoverflow
        // get the dimensions for the sprite and convert them for the device's screen
        ImageView imageView = (ImageView) findViewById(R.id.character_sprite);

        int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                character.getDimensions().x, getResources().getDisplayMetrics());
        int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                character.getDimensions().y, getResources().getDisplayMetrics());
        // get the location of the block and convert the coordinates for the device's screen
        int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                character.getLocation().x, getResources().getDisplayMetrics());
        int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                character.getLocation().y, getResources().getDisplayMetrics());

        // create new layout parameters for the sprite
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
        layoutParams.setMargins(newX, newY, 0, 0);
        assert imageView != null;
        imageView.setLayoutParams(layoutParams);
    }

    // update the ImageViews for the enemies
    private void updateEnemyView() {
        // iterate through the enemies in the environment
        List<M_Enemy> enemies = environment.getEnemies();
        for (int i = 0; i < enemies.size(); ++i) {
            boolean flag = false;
            ImageView imageView;
            M_Enemy tempMEnemy = enemies.get(i); // get the current enemy
            if (tempMEnemy.getEnemyView() == null) {
                flag = true;
                imageView = new ImageView(V_LevelActivity.this); // create a new ImageView
                tempMEnemy.setEnemyView(imageView); // used for deleting said view on enemy despawn
                imageView.setImageResource(tempMEnemy.getSprite());          // set the enemy's sprite to it
            } else {
                imageView = (ImageView) tempMEnemy.getEnemyView();
            }
            // get the level layout
            RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);
            // if the enemy is out of health
            if (tempMEnemy.getHealth() <= 0) {
                // destroy its view and remove it from the enemy list
                imageView.setVisibility(View.INVISIBLE);
                enemies.remove(i);
                --i;
                continue;
            }
            // Much of the following code was adapted from principles on stackoverflow
            // get the dimensions for the sprite and convert them for the device's screen
            int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempMEnemy.getDimensions().x, getResources().getDisplayMetrics());
            int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempMEnemy.getDimensions().y, getResources().getDisplayMetrics());
            // create new layout parameters for the sprite
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
            // get the location of the block and convert the coordinates for the device's screen
            int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempMEnemy.getLocation().x, getResources().getDisplayMetrics());
            int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempMEnemy.getLocation().y, getResources().getDisplayMetrics());
            // set the margins for the ImageView (i.e. position on the screen)
            layoutParams.setMargins(newX, newY, 0, 0);
            // add the ImageView to the layout, or update it if it's already there.
            if (flag) {
                assert RL != null;
                RL.addView(imageView, layoutParams);
            } else {
                imageView.setLayoutParams(layoutParams);
            }
        }
    }

    public void setMeter(Chronometer timeKeeper) {
        this.timeKeeper = timeKeeper;
        this.timeKeeper.start();
    }


}
