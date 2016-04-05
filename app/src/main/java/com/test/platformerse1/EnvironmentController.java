package com.test.platformerse1;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.List;

// Author: Isaiah Thacker
// Last Modified: 4/03/16 by Isaiah Thacker
// Iteration 3
// The EnvironmentController class manipulates the environment and calls the methods from the other
// controller classes. It can load data from levels into the environment's fields,
// and update the environment's current state based on the rules of the game.
public class EnvironmentController {

    // iterationFlag used for a variety of purposes
    private static boolean iterationFlag;
    // constant gravity strength
    public static final int GRAVITY = 3;
    // creating a character
    public static Character player = Character.getInstance();
    // the environment instance
    public static Environment environment = Environment.getInstance();
    // environment's blocks
    private static List<Block> blocks = environment.getBlocks();
    // environment's bullets
    private static List<Bullet> bullets = environment.getBullets();
    // environment's records
    private static List<Record> records = environment.getRecords();
    // environment's enemies
    private static List<Enemy> enemies = environment.getEnemies();

    // initialize will be used to restart the current level as well as to load a new level
    public static void initialize(Level l, Character c) {
        blocks.clear();                              // remove all current blocks from this list
        blocks.addAll(l.getBlocks());                // add all of the blocks from the level to this list
        records.clear();                             // remove all current records from this list
        records.addAll(l.getRecords());              // add all of the records from the level to this list
        bullets.clear();                             // remove all bullets from this list
        environment.setGoal(l.getGoal());                          // set the goal record
        enemies.addAll(l.getEnemies());              // add all of the enemies from the level to this list
        Point tempPoint = new Point(l.getStartingPoint().x, l.getStartingPoint().y);
        c.reset();                                   // initialize the character's stats
        c.setLocation(tempPoint);                    // initialize the player's starting point
    }


    // update(playerChar) calls updateBullets(), updateCharacter(playerChar),
    // updateRecords(playerChar), and updateEnemies(), then returns the iteration flag
    public static boolean update() {
        updateBullets();
        updateCharacter();
        updateRecords();
        EnemyController.updateEnemies();
        return iterationFlag;
    }

    private static void updateBullets() {
        for (int i = 0; i < bullets.size(); ++i) {                 // iterate through all bullets
            iterationFlag = false;                                  // reset the flag
            Bullet tempBullet = bullets.get(i);
            Point tempLoc = new Point(tempBullet.getLocation());    // get the bullet's current location
            Point tempDims = new Point(tempBullet.getDimensions()); // get the bullet's dimensions
            if (tempBullet.getTimeRemaining() == 0) {               // if the bullet has expired
                tempBullet.setFlag(true);                           // flag it for removal
                iterationFlag = true;                               // keep the now nonexistent bullet from moving
                break;                                              // no need to check the other blocks
            } else {
                if (tempBullet.isEnemyBullet()) {                   // if it's an enemy bullet
                    // if it's intersecting the character
                    if (boxIntersect(tempLoc, tempDims, player.getLocation(), player.getDimensions())) {
                        player.damage(tempBullet.getPower());       // damage the character
                        tempBullet.setFlag(true);                   // flag the bullet for removal
                        iterationFlag = true;                       // keep the now nonexistent bullet from moving
                        break;                                      // no need to check the blocks
                    }
                }
                for (int j = 0; j < blocks.size(); ++j) {              // iterate through the environment's blocks
                    Block curBlock = blocks.get(j);                // get the current block
                    if (boxIntersect(tempLoc, tempDims, curBlock.getLocation(), curBlock.getDimensions())) {// if this bullet is
                        // hitting a block
                        tempBullet.setFlag(true);                       // flag it for removal
                        iterationFlag = true;                           // keep the now nonexistent bullet from moving
                        break;                                          // no need to check the other blocks
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

    // Currently, updateRecords(playerChar) throws the iteration flag if character playerChar is
    // intersecting the goal record.
    private static void updateRecords() {
        Character playerChar = Character.getInstance();
//    for (i = 0; i < this.records.size(); ++i){                 // iterate through all records. WILL BE ADDED WHEN
//                                                               // WE ADD MORE RECORDS
//    }
        Point goalLoc = environment.getGoal().getLocation();
        Point goalDims = environment.getGoal().getDimensions();
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
    private static void updateCharacter() {
        Character playerChar = Character.getInstance();
        // load the character's current location into a temp variable
        Point tempLoc = new Point(playerChar.getLocation().x, playerChar.getLocation().y);
        // move the temp variable horizontally according to the character's velocity
        tempLoc.set(tempLoc.x + playerChar.getVelocity().x, tempLoc.y);
        // Iterate through all blocks, seeing if this movement would cause playerChar to intersect the block
        for (int i = 0; i < blocks.size(); ++i) {
            Block tempBlock = blocks.get(i);
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
        for (int i = 0; i < blocks.size(); ++i) {
            Block tempBlock = blocks.get(i);
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
        // If the player is immune, decrement its immunity time
        if (player.getImmunity() > 0) {
            player.decrementImmunity();
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

    // onBlock(entity) returns true if character entity is standing on a block, and false otherwise
    public static boolean onBlock(Entity entity) {
        Point tempLoc = new Point(entity.getLocation());
        List<Block> blocks = Environment.getInstance().getBlocks();
        tempLoc.offset(0, GRAVITY); // suppose the character falls
        // Iterate through all blocks, seeing if this movement would cause entity to intersect the block
        for (int i = 0; i < blocks.size(); ++i) {
            Block tempBlock = blocks.get(i);
            // if one of the blocks WOULD intersect, return true
            if (boxIntersect(tempLoc, entity.getDimensions(), tempBlock.getLocation(), tempBlock.getDimensions())) {
                return true;
            }
        }
        // if entity wouldn't intersect a block, return false.
        return false;
    }
}