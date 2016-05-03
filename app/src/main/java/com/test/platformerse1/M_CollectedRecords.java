package com.test.platformerse1;

import android.content.Context;
import android.media.MediaPlayer;
// Author: John C. Hale
// Last Modified: 4/25/16 by Isaiah Thacker
// Iteration 4
// The M_CollectedRecords class acts as a library of all players collected and usable.


public class M_CollectedRecords {

    private int[][] recordLibrary;
    private int libraryCounter;
    private int powerUpCounter;
    private int currentPowerUp;
    private MediaPlayer mp;
    private static M_CollectedRecords ourInstance = new M_CollectedRecords();
    public static M_CollectedRecords getInstance() {
        return ourInstance;
    }

    private M_CollectedRecords() {
        recordLibrary = new int[8][5];
        libraryCounter = 0;
        currentPowerUp = 0;
        powerUpCounter = 0;
    }

    public int getLibraryCounter() { return this.libraryCounter;}
    //Checks to see if player still has power-up
    public boolean doWeHavePowerUp() {
        if (this.powerUpCounter > 0)
            return true;
        return false;
    }
    public void decrementPowerUpCounter() {this.powerUpCounter--;}
    public void incrementPowerUpCounter() {this.powerUpCounter++;}
    public void resetPowerUpCounter() {this.powerUpCounter = 1;}


    public void addPowerUp(int[] powerUp) {
        this.recordLibrary[this.libraryCounter][0] = powerUp[0];  //music
        this.recordLibrary[this.libraryCounter][1] = powerUp[1];  //strength
        this.recordLibrary[this.libraryCounter][2] = powerUp[2];  //defense
        this.recordLibrary[this.libraryCounter][3] = powerUp[3];  //speed
        this.recordLibrary[this.libraryCounter][4] = powerUp[4];  //immunity
        this.libraryCounter++;
        this.currentPowerUp++;
        this.incrementPowerUpCounter();
    }


    public int[] getPowerUp() {
        int[] current = new int[5];
        current[0] = this.recordLibrary[this.currentPowerUp][0];  //music
        current[1] = this.recordLibrary[this.currentPowerUp][1];  //strength
        current[2] = this.recordLibrary[this.currentPowerUp][2];  //defense
        current[3] = this.recordLibrary[this.currentPowerUp][3];  //speed
        current[4] = this.recordLibrary[this.currentPowerUp][4];  //immunity
        return current;
    }

    public void nextPowerUp(){
        if (this.currentPowerUp < this.getLibraryCounter()) {
            this.currentPowerUp++;
        }
        else {
            this.currentPowerUp = 0;
        }
    }
    public void previousPowerUp(){
        if (this.currentPowerUp > 0) {
            this.currentPowerUp--;
        }
        else {
            this.currentPowerUp = this.getLibraryCounter();
        }
    }

}
