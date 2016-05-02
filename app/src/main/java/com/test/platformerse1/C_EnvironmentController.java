package com.test.platformerse1;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

// Author: Isaiah Thacker
// Last Modified: 4/30/16 by Isaiah Thacker
// Iteration 4
// The C_EnvironmentController class manipulates the environment and calls the methods from the other
// controller classes. It can load data from levels into the environment's fields,
// and update the environment's current state based on the rules of the game.
public class C_EnvironmentController {
    // iterationFlag used for a variety of purposes
    private static boolean iterationFlag;
    // constant gravity strength
    public static final int GRAVITY = 3;
    // creating a character
    private static final M_Character player = M_Character.getInstance();
    // the environment instance
    private static final M_Environment environment = M_Environment.getInstance();
    // environment's blocks
    private static final List<M_Block> blocks = environment.getBlocks();
    // environment's bullets
    private static final List<M_Bullet> bullets = environment.getBullets();
    // environment's records
    private static final List<M_Record> records = environment.getRecords();
    // environment's enemies
    private static final List<M_Enemy> enemies = environment.getEnemies();
    // environment's popup triggers
    private static final List<M_PopupTrigger> popups = environment.getPopups();

    // initialize will be used to restart the current level as well as to load a new level
    public static void initialize(M_Level l) {
        environment.setCurrentLevel(l.getId());
        blocks.clear();                              // remove all current blocks from this list
        blocks.addAll(l.getMBlocks());               // add all of the blocks from the level to this list
        records.clear();                             // remove all current records from this list
        records.addAll(l.getMRecords());             // add all of the records from the level to this list
        bullets.clear();                             // remove all bullets from this list
        environment.setGoal(l.getGoal());            // set the goal record
        enemies.clear();                             // remove all enemies from this list
        enemies.addAll(l.getEnemies());              // add all of the enemies from the level to this list
        popups.clear();                              // remove all popups from this list
        popups.addAll(l.getPopups());                // add all of the popups from the level to this list
        Point tempPoint = new Point(l.getStartingPoint().x, l.getStartingPoint().y);
        unpause();                                   // unpause the environment
        C_CharacterController.reset();                              // initialize the character's stats
        player.setLocation(tempPoint);               // initialize the player's starting point
    }


    // update() calls updateBullets(), updateCharacter(),
    // updateRecords(), and updateEnemies(), then returns the iteration flag
    public static boolean update() {
        updateBullets();
        C_CharacterController.updateCharacter();
        updateRecords();
        C_EnemyController.updateEnemies();
        C_PopupController.updatePopups();
        return iterationFlag;
    }

    private static void updateBullets() {
        for (int i = 0; i < bullets.size(); ++i) {                   // iterate through all bullets
            iterationFlag = false;                                   // reset the flag
            M_Bullet tempMBullet = bullets.get(i);
            Point tempLoc = new Point(tempMBullet.getLocation());    // get the bullet's current location
            Point tempDims = new Point(tempMBullet.getDimensions()); // get the bullet's dimensions
            if (tempMBullet.getTimeRemaining() == 0) {               // if the bullet has expired
                tempMBullet.setFlag(true);                           // flag it for removal
                iterationFlag = true;                                // keep the now nonexistent bullet from moving
                break;                                               // no need to check the other blocks
            } else {
                if (tempMBullet.isEnemyBullet()) {                   // if it's an enemy bullet
                    // if it's intersecting the character
                    if (boxIntersect(tempLoc, tempDims, player.getLocation(), player.getDimensions())) {
                        C_CharacterController.damage(tempMBullet.getPower());       // damage the character
                        tempMBullet.setFlag(true);                   // flag the bullet for removal
                        iterationFlag = true;                        // keep the now nonexistent bullet from moving
                        break;                                       // no need to check the blocks
                    }
                }
                for (int j = 0; j < blocks.size(); ++j) {            // iterate through the environment's blocks
                    M_Block curMBlock = blocks.get(j);               // get the current block
                    // if this bullet is hitting a block
                    if (boxIntersect(tempLoc, tempDims, curMBlock.getLocation(), curMBlock.getDimensions())) {
                        tempMBullet.setFlag(true);                   // flag it for removal
                        iterationFlag = true;                        // keep the now nonexistent bullet from moving
                        break;                                       // no need to check the other blocks
                    }
                }
            }
            if (!iterationFlag) {                                     // if the bullet wasn't removed
                tempLoc.offset(bullets.get(i).getVelocity().x, bullets.get(i).getVelocity().y);   // add the bullet's velocity to the temp
                bullets.get(i).setTimeRemaining(bullets.get(i).getTimeRemaining() - 1); // decrement time remaining
                bullets.get(i).setLocation(tempLoc); // set the bullet's location to the temp
            }
        }
        iterationFlag = false;                                     // Reset the flag for later use
    }

    // Currently, updateRecords() throws the iteration flag if the player character is
    // intersecting the goal record.
    private static void updateRecords() {
        // get the location and dimensions of the goal and the player.
        Point goalLoc = environment.getGoal().getLocation();
        Point goalDims = environment.getGoal().getDimensions();
        Point charLoc = player.getLocation();
        Point charDims = player.getDimensions();
        if (boxIntersect(goalLoc, goalDims, charLoc, charDims)) {   // see if the character is intersecting the goal
            iterationFlag = true;                                    // if so, set the flag so that we can end the level.
        }
    }


    // boxIntersect(L1, S1, L2, S2) returns true if the box anchored at point L1 with dimensions S1 and the box
    // anchored at point L2 with dimensions S2 overlap. (i.e. if one of the former box's corners is in the latter box,
    // or if all of the latter box's corners are in the former.)
    public static boolean boxIntersect(Point location1, Point dimensions1, Point location2, Point dimensions2) {
        Rect r1 = new Rect(location1.x, location1.y, location1.x + dimensions1.x - 1, location1.y + dimensions1.y - 1);
        Rect r2 = new Rect(location2.x, location2.y, location2.x + dimensions2.x - 1, location2.y + dimensions2.y - 1);
        return r1.intersect(r2);
    }

    // onBlock(mEntity) returns true if character mEntity is standing on a block, and false otherwise
    public static boolean onBlock(M_Entity mEntity) {
        Point tempLoc = new Point(mEntity.getLocation());
        List<M_Block> MBlocks = M_Environment.getInstance().getBlocks();
        tempLoc.offset(0, GRAVITY); // suppose the character falls
        // Iterate through all blocks, seeing if this movement would cause mEntity to intersect the block
        for (int i = 0; i < MBlocks.size(); ++i) {
            M_Block tempMBlock = MBlocks.get(i);
            // if one of the blocks WOULD intersect, return true
            if (boxIntersect(tempLoc, mEntity.getDimensions(), tempMBlock.getLocation(), tempMBlock.getDimensions())) {
                return true;
            }
        }
        // if mEntity wouldn't intersect a block, return false.
        return false;
    }

    public static void pauseGame() {
        environment.setPaused(true);
    }

    public static void unpause() {
        environment.setPaused(false);
    }

    public static void applyPower(int powerUp[]) {

    }
}