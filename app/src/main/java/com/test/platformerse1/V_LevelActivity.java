package com.test.platformerse1;

// Author: Isaiah Thacker
// Last Modified: 4/28/16 by Isaiah Thacker
// Iteration 3
// V_LevelActivity defines the class responsible for displaying the in-game
// environment, and starts the game controllers running.

import android.content.pm.ActivityInfo;
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
    // set up flag for if the level is started
    private boolean started = false;
    // integer used for getting the desired level's ID
    private int savedLevelInfo;
    // constant is the reciprocal of the framerate
    private final int FRAME_DURATION = 33;

    V_PopupFragment popupFragment;

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

        popupFragment = (V_PopupFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_popup);
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
                if (!environment.isPaused()) {
                    // update the game's views
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateCharacterView();
                            updateBulletsView();
                            updateEnemyView();
                            updatePopupView();
                        }
                    });
                    // run the update function. If the player has reached the goal, display the
                    // endscreen
                    if (C_EnvironmentController.update()) {
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
        C_EnvironmentController.pauseGame();
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

    // initialize level with ID id
    public void initLevel(int id) {
        // load the level corresponding to ID id into the environment
        C_EnvironmentController.initialize(M_LevelVault.getLevel(id));
        // signal that the level has started
        started = true;
        // initialize the ImageViews
        initView();
    }

    // initialize the activity's views
    public void initView() {
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
            initObjView(tempMBlock);            // initialize a view for it.
            updateWorldObjectView(tempMBlock, false);               // add it to the level layout
        }
    }


    // update the ImageViews for the bullets
    private void updateBulletsView() {
        // iterate through the bullets in the environment
        List<M_Bullet> bullets = environment.getBullets();
        for (int i = 0; i < bullets.size(); ++i) {
            boolean alreadyDisplayed = true;

            M_Bullet tempMBullet = bullets.get(i); // get the current bullet
            ImageView imageView = tempMBullet.getImageView();       // get its view

            if (imageView == null) {                                // if the view is null
                alreadyDisplayed = false;                           // it hasn't yet been displayed
                imageView = initObjView(tempMBullet);               // initialize a view
            }

            // if the bullet is flagged for removal
            if (tempMBullet.getFlag()) {
                // destroy its view and remove it from the bullet list
                imageView.setVisibility(View.GONE);
                bullets.remove(i);
                --i;
                continue;
            }
            // update the bullet's view
            updateWorldObjectView(tempMBullet, alreadyDisplayed);
        }
    }

    // initialize the views for the records.
    private void initRecordsView() {
        M_Record tempMRecord = environment.getGoal();               // get the goal record
        initObjView(tempMRecord);                                   // give the record a view
        updateWorldObjectView(tempMRecord, false);                  // add its view to the layout
    }

    // update the character's ImageView
    private void updateCharacterView() {
        // get the character
        M_Character character = M_Character.getInstance();
        // if the character doesn't yet have its image view associated with it, remedy that.
        character.setImageView((ImageView) findViewById(R.id.character_sprite));
        // update the character's view. It already exists in the relative layout.
        updateWorldObjectView(character, true);
    }

    // update the ImageViews for the enemies
    private void updateEnemyView() {
        // iterate through the enemies in the environment
        List<M_Enemy> enemies = environment.getEnemies();
        for (int i = 0; i < enemies.size(); ++i) {
            boolean alreadyDisplayed = true;

            M_Enemy tempMEnemy = enemies.get(i); // get the current enemy
            ImageView imageView = tempMEnemy.getImageView(); // get its view

            // if the enemy doesn't have a view, give it one and set alreadyDisplayed to false
            if (imageView == null) {
                imageView = initObjView(tempMEnemy);
                alreadyDisplayed = false;
            }

            // if the enemy is out of health
            if (tempMEnemy.getHealth() <= 0) {
                // destroy its view and remove it from the enemy list
                imageView.setVisibility(View.INVISIBLE);
                enemies.remove(i);
                --i;
                continue;
            }
            updateWorldObjectView(tempMEnemy, alreadyDisplayed);
        }
    }

    // If alreadyDisplayed is true, and Obj is a world object associated with ImageView IV in the
    // current level's layout, then updateWorldObjectView(Obj, alreadyDisplayed) updates IV based on
    // Obj's location.
    // If alreadyDisplayed is false, Obj is a world object associated with ImageView IV, then
    // updateWorldObjectView(Obj, alreadyDisplayed) updates IV based on Obj's location and adds
    // it to the current level's layout.
    public void updateWorldObjectView(M_WorldObject worldObject, boolean alreadyDisplayed) {
        // get the level layout
        RelativeLayout RL = (RelativeLayout) findViewById(R.id.level_layout);
        // get the object's image view
        ImageView imageView = worldObject.getImageView();

        // get the dimensions for the sprite and convert them for the device's screen
        int dimX = convertForScreen(worldObject.getDimensions().x);
        int dimY = convertForScreen(worldObject.getDimensions().y);

        // create new layout parameters for the sprite
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimX, dimY);

        // get the location of the block and convert the coordinates for the device's screen
        int newX = convertForScreen(worldObject.getLocation().x);
        int newY = convertForScreen(worldObject.getLocation().y);

        // set the margins for the ImageView (i.e. position on the screen)
        layoutParams.setMargins(newX, newY, 0, 0);

        // add the ImageView to the layout, or update it if it's already there.
        if (alreadyDisplayed) {
            imageView.setLayoutParams(layoutParams);
        } else {
            assert RL != null;
            RL.addView(imageView, layoutParams);
        }
    }

    // this method is loosely adapted from some principles I found on stackoverflow
    private int convertForScreen(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getResources().getDisplayMetrics());
    }

    private ImageView initObjView(M_WorldObject tempWorldObject) {
        ImageView imageView = new ImageView(V_LevelActivity.this); // create a new ImageView
        tempWorldObject.setImageView(imageView); // used for deleting said view on enemy despawn
        imageView.setImageResource(tempWorldObject.getSprite());          // set the enemy's sprite to it
        return imageView;
    }

    public void setMeter(Chronometer timeKeeper) {
        this.timeKeeper = timeKeeper;
        this.timeKeeper.start();
    }

    private void updatePopupView() {
        if (environment.isShowingPopup()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    View fragmentFrame = findViewById(R.id.fragment_popup);
                    assert fragmentFrame != null;
                    fragmentFrame.setVisibility(View.VISIBLE);
                    popupFragment.displayMessage(environment.getPopupTitle(), environment.getPopupText());
                }
            });
        }
    }

    // displayPauseMenu() pauses the game and displays the pause menu.
    public void displayPauseMenu() {
        // pause the game
        C_EnvironmentController.pauseGame();
        // get the pause fragment and toggle its visibility
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View pauseFrame = findViewById(R.id.fragment_pause);
                assert pauseFrame != null;
                pauseFrame.bringToFront();
                pauseFrame.setVisibility(View.VISIBLE);

            }
        });
        timeKeeper.stop();
    }

    public Chronometer getTimeKeeper() {
        return this.timeKeeper;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}