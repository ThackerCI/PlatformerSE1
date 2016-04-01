package com.test.platformerse1;

// Author: Isaiah Thacker
// Last Modified: 3/21/16 by Isaiah Thacker
// Iteration 2
// The Environment class defines objects which contain all data on the current gameplay environment
// (level layout, character locations, and so forth.) It can load data from levels into its fields,
// and update its current state based on the rules of the game.

import android.graphics.Point;
import android.graphics.Rect;
import android.media.audiofx.EnvironmentalReverb;

import java.util.ArrayList;
import java.util.List;


public class Environment {
    // blocks in the environment
    private List<Block> blocks;
    // non-goal records in the environment
    private List<Record> records;
    // bullets
    private List<Bullet> bullets;
    // the goal record
    private Record goal;
    // the enemies
    private List<Enemy> enemies;
    // integer used for various loops
    private int i;
    // iterationFlag used for a variety of purposes
    private boolean iterationFlag;
    // constant gravity strength
    public static final int GRAVITY = 3;

    // The instance of Environment (Singleton design pattern)
    private static Environment instance;

    // creating a test character
    public static Character player = new Character(new Point(0, 0), new Point(30, 30), 3, 3, 3, 5);


    private Environment() {
        blocks = new ArrayList<>();
        records = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    public static Environment getInstance(){
        if (instance == null){
            instance = new Environment();
        }
        return instance;
    }

    // addBullet(b) adds bullet b to the current environment.
    public void addBullet(Bullet b) {
        this.bullets.add(b);
    }

    // initialize will be used to restart the current level as well as to load a new level
    public void initialize(Level l, Character c) {
        blocks.clear();                              // remove all current blocks from this list
        blocks.addAll(l.getBlocks());                // add all of the blocks from the level to this list
        records.clear();                             // remove all current records from this list
        records.addAll(l.getRecords());              // add all of the records from the level to this list
        bullets.clear();                             // remove all bullets from this list
        goal = l.getGoal();                          // set the goal record
        enemies.addAll(l.getEnemies());              // add all of the enemies from the level to this list
        Point tempPoint = new Point(l.getStartingPoint().x, l.getStartingPoint().y);
        c.reset();                                   // initialize the character's stats
        c.setLocation(tempPoint);                    // initialize the player's starting point
    }


    // update(playerChar) calls updateBullets(), updateCharacter(playerChar), and
    // updateRecords(playerChar), returns the iteration flag
    public boolean update(Character playerChar) {
        updateBullets();
        updateCharacter(playerChar);
        updateRecords(playerChar);
        return iterationFlag;
    }

    private void updateBullets() {
        for (i = 0; i < this.bullets.size(); ++i) {                 // iterate through all bullets
            iterationFlag = false;                                  // reset the flag
            Bullet tempBullet = this.bullets.get(i);
            Point tempLoc = new Point(tempBullet.getLocation());    // get the bullet's current location
            Point tempDims = new Point(tempBullet.getDimensions()); // get the bullet's dimensions
            int j;
            if (tempBullet.getTimeRemaining() == 0) {               // if the bullet has expired
                tempBullet.setFlag(true);                           // flag it for removal
                iterationFlag = true;                               // keep the now nonexistent bullet from moving
                break;                                              // no need to check the other blocks
            } else {
                for (j = 0; j < this.blocks.size(); ++j) {              // iterate through the environment's blocks
                    Block curBlock = this.blocks.get(j);                // get the current block
                    if (boxIntersect(tempLoc, tempDims, curBlock.getLocation(), curBlock.getDimensions())) {// if this bullet is
                        // hitting a block
                        tempBullet.setFlag(true);                       // flag it for removal
                        iterationFlag = true;                           // keep the now nonexistent bullet from moving
                        break;                                          // no need to check the other blocks
                    }
                }
            }
            if (!iterationFlag) {                                     // if the bullet wasn't removed
                tempLoc.offset(this.bullets.get(i).getVelocity().x, this.bullets.get(i).getVelocity().y);   // add the bullet's velocity to the temp
                this.bullets.get(i).setTimeRemaining(this.bullets.get(i).getTimeRemaining() - 1); // decrement time remaining
                this.bullets.get(i).setLocation(tempLoc); // set the bullet's location to the temp
            }
        }
        iterationFlag = false;                                     // Reset the flag for later use
    }

    // Currently, updateRecords(playerChar) throws the iteration flag if character playerChar is
    // intersecting the goal record.
    private void updateRecords(Character playerChar) {
//    for (i = 0; i < this.records.size(); ++i){                 // iterate through all records. WILL BE ADDED WHEN
//                                                               // WE ADD MORE RECORDS
//    }
        Point goalLoc = this.goal.getLocation();
        Point goalDims = this.goal.getDimensions();
        Point charLoc = playerChar.getLocation();
        Point charDims = playerChar.getDimensions();
        if (boxIntersect(goalLoc, goalDims, charLoc, charDims)) {   // see if the character is intersecting the goal
            iterationFlag = true;                                    // if so, set the flag so that we can end the level.
        }
    }


    // updateCharacter(playerChar) first moves character playerChar horizontally if doing so would
    // not cause the player to move into a block
    // If this condition is not satisfied, playerChar's horizontal velocity is set to zero.
    // updateCharacter(playerChar) then moves character playerChar vertically if doing so would not
    // cause the player to move into a block
    // If this condition is not satisfied, playerChar's vertical velocity is set to zero.
    private void updateCharacter(Character playerChar) {
        // load the character's current location into a temp variable
        Point tempLoc = new Point(playerChar.getLocation().x, playerChar.getLocation().y);
        // move the temp variable horizontally according to the character's velocity
        tempLoc.set(tempLoc.x + playerChar.getVelocity().x, tempLoc.y);
        // Iterate through all blocks, seeing if this movement would cause playerChar to intersect the block
        for (i = 0; i < this.getBlocks().size(); ++i) {
            Block tempBlock = this.getBlocks().get(i);
            // if a block would intersect playerChar, move the temp location back and reduce
            // playerChar's horizontal velocity to zero.
            // Also, break.
            if (boxIntersect(tempLoc, playerChar.getDimensions(), tempBlock.getLocation(), tempBlock.getDimensions())) {
                tempLoc.set(tempLoc.x - playerChar.getVelocity().x, tempLoc.y);
                playerChar.setVelocityX(0);
                break;
            }
        }
        // move the temp variable vertically according to the character's velocity
        tempLoc.offset(0, playerChar.getVelocity().y);
        // Iterate through all blocks, seeing if this movement would cause playerChar to intersect the block
        for (i = 0; i < this.getBlocks().size(); ++i) {
            Block tempBlock = this.getBlocks().get(i);
            // if a block would intersect playerChar, move the temp location back and reduce playerChar's vertical velocity to zero.
            // Also, break.
            if (boxIntersect(tempLoc, playerChar.getDimensions(), tempBlock.getLocation(), tempBlock.getDimensions())) {
                tempLoc.offset(0, -playerChar.getVelocity().y);
                playerChar.setVelocityY(0);
                break;
            }
        }
        // if the character isn't jumping and isn't standing on a block, start him falling.
        if (!onBlock(playerChar) && playerChar.getJumpTime() == 0) {
            playerChar.setVelocityY(GRAVITY);
        }
        // decrement playerChar's jump time if applicable
        if (playerChar.getJumpTime() > 0) {
            playerChar.setJumpTime(playerChar.getJumpTime() - 1);
        }

        playerChar.setLocation(tempLoc);   // set the character's new location
    }




    // getGoal() returns the goal record in the current environment.
    public Record getGoal() {
        return this.goal;
    }

    // getBullets() returns the list of bullets in the current environment.
    public List<Bullet> getBullets() {
        return this.bullets;
    }

    // getRecords() returns the list of records in the current environment.
    public List<Record> getRecords() {
        return this.records;
    }

    // getBlocks() returns the list of blocks in the current environment.
    public List<Block> getBlocks() {
        return this.blocks;
    }

    // getEnemies() returns the list of blocks in the current environment.
    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    // boxIntersect(L1, S1, L2, S2) returns true if the box anchored at point L1 with dimensions S1 and the box
    // anchored at point L2 with dimensions S2 overlap. (i.e. if one of the former box's corners is in the latter box,
    // or if all of the latter box's corners are in the former.)
    public static boolean boxIntersect(Point location1, Point dimensions1, Point location2, Point dimensions2) {
        Rect r1 = new Rect(location1.x, location1.y, location1.x + dimensions1.x - 1, location1.y + dimensions1.y - 1);
        Rect r2 = new Rect(location2.x, location2.y, location2.x + dimensions2.x - 1, location2.y + dimensions2.y - 1);
        return r1.intersect(r2);
    }

    // onBlock(entity) returns true if character entity is standing on a block, and false otherwise
    public boolean onBlock(Entity entity) {
        Point tempLoc = new Point(entity.getLocation());
        tempLoc.offset(0, GRAVITY); // suppose the character falls
        // Iterate through all blocks, seeing if this movement would cause entity to intersect the block
        for (i = 0; i < this.getBlocks().size(); ++i) {
            Block tempBlock = this.getBlocks().get(i);
            // if one of the blocks WOULD intersect, return true
            if (boxIntersect(tempLoc, entity.getDimensions(), tempBlock.getLocation(), tempBlock.getDimensions())) {
                return true;
            }
        }
        // if entity wouldn't intersect a block, return false.
        return false;
    }
}