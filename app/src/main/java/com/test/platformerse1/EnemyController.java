package com.test.platformerse1;

import android.graphics.Point;

import java.util.List;

// Author: Isaiah Thacker
// Last Modified: 3/31/16 by Isaiah Thacker
// Iteration 3
// The EnemyController class contains the methods for updating the state of all enemies.
public class EnemyController {
    private static List<Enemy> enemies = Environment.getInstance().getEnemies();
    private static List<Block> blocks = Environment.getInstance().getBlocks();

    // updateEnemies() detects player bullet collisions with enemies, updates the location of all
    // enemies, flags each enemy that is out of health for deletion, and calls each enemy's AI
    // function.
    private void updateEnemies() {
        for (int j = 0; j < enemies.size(); ++j) {
            // get the enemy
            Enemy enemy = enemies.get(j);
            // handle all bullet collisions with the enemy
            detectEnemyCollision(enemy);
            // move the enemy
            moveEnemy(enemy);
            // run the enemy's AI
            runAI(enemy);
        }
    }


    // moveEnemy(enemy) moves enemy and updates its velocity
    private void moveEnemy(Enemy enemy) {
        // load the enemy's current location into a temp variable
        Point tempLoc = new Point(enemy.getLocation().x, enemy.getLocation().y);
        // move the temp variable horizontally according to the enemy's velocity
        tempLoc.set(tempLoc.x + enemy.getVelocity().x, tempLoc.y);
        // Iterate through all blocks, seeing if this movement would cause enemy to intersect the block
        for (int i = 0; i < blocks.size(); ++i) {
            Block tempBlock = blocks.get(i);
            // if a block would intersect enemy, move the temp location back, toggle the direction
            // and invert the horizontal velocity.
            // Also, break.
            if (Environment.boxIntersect(tempLoc, enemy.getDimensions(), tempBlock.getLocation(), tempBlock.getDimensions())) {
                tempLoc.set(tempLoc.x - enemy.getVelocity().x, tempLoc.y);
                enemy.setVelocityX(enemy.getVelocity().x * -1);
                enemy.setDirection(enemy.getDirection() * -1);
                break;
            }
        }
        // move the temp variable vertically according to the enemy's velocity
        tempLoc.offset(0, enemy.getVelocity().y);
        // Iterate through all blocks, seeing if this movement would cause enemy to intersect the block
        for (int i = 0; i < blocks.size(); ++i) {
            Block tempBlock = blocks.get(i);
            // if a block would intersect enemy, move the temp location back and reduce enemy's vertical velocity to zero.
            // Also, break.
            if (Environment.boxIntersect(tempLoc, enemy.getDimensions(), tempBlock.getLocation(), tempBlock.getDimensions())) {
                tempLoc.offset(0, -enemy.getVelocity().y);
                enemy.setVelocityY(0);
                break;
            }
        }

        enemy.setLocation(tempLoc);   // set the character's new location
    }

    private void detectEnemyCollision(Enemy enemy){

    }



    // runAI(enemy) causes enemy to behave according to its AI type.
    private void runAI(Enemy enemy){

    }
}
