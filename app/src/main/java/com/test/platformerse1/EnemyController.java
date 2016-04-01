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
    private static List<Bullet> bullets = Environment.getInstance().getBullets();

    // updateEnemies() detects player bullet collisions with enemies, updates the location of all
    // enemies, and calls each enemy's AI function.
    public static void updateEnemies() {
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
    private static void moveEnemy(Enemy enemy) {
        // load the enemy's current location into a temp variable
        Point tempLoc = new Point(enemy.getLocation());
        // move the temp variable horizontally according to the enemy's velocity
        tempLoc.offset(enemy.getVelocity().x, 0);
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
        // if the enemy isn't standing on a block, start him falling.
        if (!Environment.onBlock(enemy)) {
            enemy.setVelocityY(Environment.GRAVITY);
        }
        else { // otherwise, stop his falling
            enemy.setVelocityY(0);
        }

        enemy.setLocation(tempLoc);   // set the character's new location
    }

    // detectEnemyCollision(enemy) checks fo
    private static void detectEnemyCollision(Enemy enemy) {
        // iterate through all bullets
        for (int i = 0; i < bullets.size(); ++i){
            // get the current bullet
            Bullet bullet = bullets.get(i);
            // if the bullet is an enemy bullet
            if(bullet.getSprite() == R.drawable.enemy_bullet) {
                // break
                break;
            }
            Point bulletLoc = bullet.getLocation();    // get the bullet's current location
            Point bulletDims = bullet.getDimensions(); // get the bullet's dimensions
            // if the bullet hit the enemy
            if (Environment.boxIntersect(bulletLoc, bulletDims, enemy.getLocation(), enemy.getDimensions())) {
                // lower the enemy's health
                enemy.setHealth(enemy.getHealth() - Math.max(0, (bullet.getPower() - enemy.getDefense())));
                // flag the bullet for deletion
                bullet.setFlag(true);
            }
        }
    }

    // runAI(enemy) causes enemy to behave according to its AI type.
    private static void runAI(Enemy enemy) {
        switch (enemy.getEnemyType()){
            case 0:
                crawlerAI(enemy);
                break;
        }
    }

    // crawlerAI(enemy) causes enemy to start moving to the left if its horizontal velocity is zero
    private static void crawlerAI(Enemy enemy){
        if (enemy.getVelocity().x == 0){
            enemy.setVelocityX(-1 * enemy.getSpeed());
            enemy.setDirection(-1);
        }
    }
}
