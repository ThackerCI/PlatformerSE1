package com.test.platformerse1;

import android.content.Context;
import android.media.MediaPlayer;
// Author: John C. Hale
// Last Modified: 4/30/16 by John C. Hale
// Iteration 4
// The C_MusicController class, regulates the background music, bonus music, and sound effects of
// the game.


public class C_MusicController {



    //Manipulates collected records, therefor calls instance
    private static final M_CollectedRecords collectedRecords = M_CollectedRecords.getInstance();
    //A mediaPlayer for each class of sounds to be heard
    private static MediaPlayer bonus;
    private static MediaPlayer background;
    //Flags, indicating which is playing
    private static boolean backgroundLastPlaying;
    private static boolean bonusLastPlaying;
   // private static MediaPlayer playerSoundEffects;
   // private static MediaPlayer enemySoundEffects;

    //Checks Bonus time if bonus is playing
    public static void updateMusic() {
        if (!backgroundLastPlaying) {
            bonusTime();
        }

    }
    //Resets music/bonus
    public static void resetBonus(Context context, int music) {
        startBackgroundMusic(context, music);
        M_CollectedRecords.getInstance().resetPowerUpCounter();
    }

        //Initializes background music
    public static void startBackgroundMusic(Context context, int music) {
        background = new MediaPlayer().create(context, music);
        background.start();
        background.setLooping(true);
        backgroundLastPlaying = true;
        bonusLastPlaying = false;

    }

    //Pauses background music, begins bonus music
    public static void applyPowerUp(Context context) {
        bonus = new MediaPlayer().create(context, R.raw.dotz);
        background.pause();
        backgroundLastPlaying = false;
        bonus.start();
        bonusLastPlaying = true;
    }

    //Stops power up music, begins background music
    public static void stopPowerUp() {
        if (bonusLastPlaying) {
            bonus.stop();
            bonus.release();
            bonusLastPlaying = false;
            background.start();
            backgroundLastPlaying = true;
            C_CharacterController.stopPowerUp();
        }
    }

    //Pauses all music
    public static void pauseMusic(boolean p) {
        if (p) {
            if (backgroundLastPlaying) {
                background.pause();
            }
            else if (bonusLastPlaying)
                bonus.pause();
        }
        else {
            if (backgroundLastPlaying) {
                background.start();
            } else if (bonusLastPlaying){
                bonus.start();
            }
        }

    }

    //Stops all music
    public static void stopMusic() {
        if (backgroundLastPlaying) {
            background.stop();
            background.release();
        }
        else if(bonusLastPlaying) {
            bonus.stop();
            bonus.release();
            background.release();
        }
    }
    //Time left for bonus
    public static void bonusTime() {
        if ((15000 - getBonusTimeMS()) < 0)
            stopPowerUp();
    }

    //checks to see if bonus is on
    public static boolean isBonusMusicOn() {
        if (!backgroundLastPlaying) {
            return true;
        }
        return false;
    }
    //Returns how many miliseconds have gone by since bonus music started
    public static int getBonusTimeMS() {
        return bonus.getCurrentPosition();
    }
}
