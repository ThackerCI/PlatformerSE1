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

import java.util.Timer;
import java.util.TimerTask;

public class V_LevelActivity extends AppCompatActivity implements C_Controls.controlListener {
    double levelTime;
    private Chronometer timeKeeper;
    // set up the game loop timer
    private Timer gameLoopTimer = new Timer();
    // integer used for testing purposes
    private int value = 0;
    // set up an environment
    private C_EnvironmentController environment = C_EnvironmentController.getInstance();
    // set up flags for if the level is started and running
    private boolean started = false;
    private boolean running = false;
    // integer used for getting the desired level's ID
    private int savedLevelInfo;
    // constant is the reciprocal of the framerate
    private final int FRAME_DURATION = 33;

    M_CollectedRecords bonusLibrary;
    private boolean bonusON = false;
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
                    if (!environment.update()) {
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

    public void displayEndscreen() {
        // game is no longer running
        running = false;
        timeKeeper.stop();
        TextView time = (TextView) findViewById(R.id.current_time);
        levelTime = (SystemClock.elapsedRealtime() - timeKeeper.getBase())/1000.0; //convert milliseconds to seconds
        time.setVisibility(View.VISIBLE);
        time.setText("Your time for this level is: " + levelTime + "seconds.");
        time.bringToFront();
        // display the congratulatory text and menu button
        TextView textView = (TextView) findViewById(R.id.congrats_text);
        textView.setVisibility(View.VISIBLE);
        textView.bringToFront();
        Button button = (Button) findViewById(R.id.return_button);
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
        environment.initialize(LevelVault.levelOne(), C_EnvironmentController.player);
        // signal that the level has started
        started = true;
        // initialize the ImageViews
        initView();
        // signal that the level is not paused
        running = true;
        // Starts background music

    }

    // initialize the activity's views
    public void initView() {
        initBlocksView();
        initRecordsView();
        updateEnemyView();
        updateCharacterView();
    }

    // initialize the view for the blocks
    public void initBlocksView() {
        // iterate through the blocks in the environment
        for (int i = 0; i < environment.getBlocks().size(); ++i) {
            M_Block tempBlock = environment.getBlocks().get(i); // get the current block
            // Much of the following code was adapted from principles on stackoverflow
            ImageView imageView = new ImageView(V_LevelActivity.this); // create a new ImageView
            imageView.setImageResource(R.drawable.block);            // set the "block" sprite to it
            // get the level layout
            RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);
            // get the dimensions for the sprite and convert them for the device's screen
            int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBlock.getDimensions().x, getResources().getDisplayMetrics());
            int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBlock.getDimensions().y, getResources().getDisplayMetrics());
            // create new layout parameters for the sprite
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
            // get the location of the block and convert the coordinates for the device's screen
            int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tempBlock.getLocation().x, getResources().getDisplayMetrics());
            int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tempBlock.getLocation().y, getResources().getDisplayMetrics());
            // set the margins for the ImageView (i.e. position on the screen)
            layoutParams.setMargins(newX, newY, 0, 0);
            // add the ImageView to the layout
            RL.addView(imageView, layoutParams);

        }
    }


    // update the ImageViews for the bullets
    public void updateBulletsView() {
        // iterate through the bullets in the environment
        for (int i = 0; i < environment.getBullets().size(); ++i) {
            boolean flag = false;
            ImageView imageView;
            M_Bullet tempBullet = environment.getBullets().get(i); // get the current bullet
            if (tempBullet.getBulletView() == null) {
                flag = true;
                imageView = new ImageView(V_LevelActivity.this); // create a new ImageView
                tempBullet.setBulletView(imageView); // used for deleting said view on bullet despawn
                imageView.setImageResource(tempBullet.getSprite()); // set the bullet's sprite to it
            } else {
                imageView = (ImageView) tempBullet.getBulletView();
            }
            // get the level layout
            RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);
            // if the bullet is flagged for removal
            if (tempBullet.getFlag()) {
                // destroy its view and remove it from the bullet list
                imageView.setVisibility(View.INVISIBLE);
                environment.getBullets().remove(i);
                --i;
                continue;
            }
            // Much of the following code was adapted from principles on stackoverflow
            // get the dimensions for the sprite and convert them for the device's screen
            int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBullet.getDimensions().x, getResources().getDisplayMetrics());
            int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBullet.getDimensions().y, getResources().getDisplayMetrics());
            // create new layout parameters for the sprite
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
            // get the location of the block and convert the coordinates for the device's screen
            int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBullet.getLocation().x, getResources().getDisplayMetrics());
            int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempBullet.getLocation().y, getResources().getDisplayMetrics());
            // set the margins for the ImageView (i.e. position on the screen)
            layoutParams.setMargins(newX, newY, 0, 0);
            // add the ImageView to the layout, or update it if it's already there.
            if (flag) {
                RL.addView(imageView, layoutParams);
            } else {
                imageView.setLayoutParams(layoutParams);
            }
        }
    }

    // initialize the views for the records.
    public void initRecordsView() {
        // TODO: add loop for additional records. Currently just doing the goal, since that's all
        // we have.
        M_Record tempRecord = environment.getGoal(); // get the goal record
        // Much of the following code was adapted from principles on stackoverflow
        ImageView imageView = new ImageView(V_LevelActivity.this); // create a new ImageView
        imageView.setImageResource(R.drawable.record);            // set the "block" sprite to it
        // get the level layout
        RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);

        // get the dimensions for the sprite and convert them for the device's screen
        int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempRecord.getDimensions().x, getResources().getDisplayMetrics());
        int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempRecord.getDimensions().y, getResources().getDisplayMetrics());
        // create new layout parameters for the sprite
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
        // get the location of the block and convert the coordinates for the device's screen
        int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempRecord.getLocation().x, getResources().getDisplayMetrics());
        int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                tempRecord.getLocation().y, getResources().getDisplayMetrics());

        // set the margins for the ImageView (i.e. position on the screen)
        layoutParams.setMargins(newX, newY, 0, 0);
        // add the ImageView to the layout
        RL.addView(imageView, layoutParams);

    }

    // update the character's ImageView
    public void updateCharacterView() {
        // Much of the following code was adapted from principles on stackoverflow
        // get the dimensions for the sprite and convert them for the device's screen
        ImageView imageView = (ImageView) findViewById(R.id.character_sprite);

        int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                C_EnvironmentController.player.getDimensions().x, getResources().getDisplayMetrics());
        int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                C_EnvironmentController.player.getDimensions().y, getResources().getDisplayMetrics());
        // get the location of the block and convert the coordinates for the device's screen
        int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                C_EnvironmentController.player.getLocation().x, getResources().getDisplayMetrics());
        int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                C_EnvironmentController.player.getLocation().y, getResources().getDisplayMetrics());

        // create new layout parameters for the sprite
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
        layoutParams.setMargins(newX, newY, 0, 0);
        imageView.setLayoutParams(layoutParams);
    }

    // update the ImageViews for the enemies
    public void updateEnemyView() {
        // iterate through the enemies in the environment
        for (int i = 0; i < environment.getEnemies().size(); ++i) {
            boolean flag = false;
            ImageView imageView;
            M_Enemy tempEnemy = environment.getEnemies().get(i); // get the current enemy
            if (tempEnemy.getEnemyView() == null) {
                flag = true;
                imageView = new ImageView(V_LevelActivity.this); // create a new ImageView
                tempEnemy.setEnemyView(imageView); // used for deleting said view on enemy despawn
                imageView.setImageResource(tempEnemy.getSprite());          // set the enemy's sprite to it
            } else {
                imageView = (ImageView) tempEnemy.getEnemyView();
            }
            // get the level layout
            RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);
            // if the enemy is out of health
            if (tempEnemy.getHealth() <= 0) {
                // destroy its view and remove it from the enemy list
                imageView.setVisibility(View.INVISIBLE);
                environment.getEnemies().remove(i);
                --i;
                continue;
            }
            // Much of the following code was adapted from principles on stackoverflow
            // get the dimensions for the sprite and convert them for the device's screen
            int dimX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempEnemy.getDimensions().x, getResources().getDisplayMetrics());
            int dimY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempEnemy.getDimensions().y, getResources().getDisplayMetrics());
            // create new layout parameters for the sprite
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);
            // get the location of the block and convert the coordinates for the device's screen
            int newX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempEnemy.getLocation().x, getResources().getDisplayMetrics());
            int newY = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    tempEnemy.getLocation().y, getResources().getDisplayMetrics());
            // set the margins for the ImageView (i.e. position on the screen)
            layoutParams.setMargins(newX, newY, 0, 0);
            // add the ImageView to the layout, or update it if it's already there.
            if (flag) {
                RL.addView(imageView, layoutParams);
            } else {
                imageView.setLayoutParams(layoutParams);
            }
        }
    }

    // set the character moving in the given direction
    public void move(int direction) {
        // make the character move in the given direction.
        C_EnvironmentController.player.horizontalMove(direction);
    }

    public void jump() {
        // make the player jump if possible.
        C_EnvironmentController.player.jump(C_EnvironmentController.onBlock(C_EnvironmentController.player));
    }

    // cause the player to fire a bullet
    public void shoot() {
        environment.getBullets().add(C_EnvironmentController.player.shoot());
    }

    public void setMeter(Chronometer timeKeeper)
    {
        this.timeKeeper = timeKeeper;
        this.timeKeeper.start();
    }

    // stop the character's movement
    public void stopCharacter() {
        C_EnvironmentController.player.setVelocityX(0);
    }


}
