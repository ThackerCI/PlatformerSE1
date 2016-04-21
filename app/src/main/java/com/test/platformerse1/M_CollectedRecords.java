package com.test.platformerse1;

import android.content.Context;
import android.media.MediaPlayer;


public class M_CollectedRecords {

    private int[][] recordLibrary;
    private int libraryCounter;
    private int currentPowerUp;
    private static M_CollectedRecords ourInstance = new M_CollectedRecords();
    public static M_CollectedRecords getInstance() {
        return ourInstance;
    }

    private M_CollectedRecords() {
        recordLibrary = new int[8][5];
        libraryCounter = 0;
        currentPowerUp = 0;
    }

    public void addPowerUp(int music, int[] powerUp) {
        recordLibrary[libraryCounter][0] = music;
        recordLibrary[libraryCounter][1] = powerUp[0];  //strenth
        recordLibrary[libraryCounter][2] = powerUp[1];  //defense
        recordLibrary[libraryCounter][3] = powerUp[2];  //speed
        recordLibrary[libraryCounter][4] = powerUp[3];  //immunity
        libraryCounter++;
    }

    public int[] getCurrentPowerUp() {
        int[] current = new int[4];
        current[0] = recordLibrary[currentPowerUp][0];  //music
        current[1] = recordLibrary[currentPowerUp][1];  //strength
        current[2] = recordLibrary[currentPowerUp][2];  //defense
        current[3] = recordLibrary[currentPowerUp][3];  //speed
        current[4] = recordLibrary[currentPowerUp][4];  //immunity
        return current;
    }
    public void nextPowerUp(){
        if (currentPowerUp < libraryCounter) {
            currentPowerUp++;
        }
        else {
            currentPowerUp = 0;
        }
    }
    public void previousPowerUp(){
        if (currentPowerUp > 0) {
            currentPowerUp--;
        }
        else {
            currentPowerUp = libraryCounter;
        }
    }
}
