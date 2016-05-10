package com.test.platformerse1;

import android.graphics.Point;

import java.util.List;

/**
 * @author Isaiah Thacker
 *         Last Modified: 5/9/16 by Isaiah Thacker
 *         Iteration 4
 *         The C_EnemyController class contains the methods for updating the state of all enemies.
 */
class C_EnemyController {
    private static final List<M_Enemy> enemies = M_Environment.getInstance().getEnemies();
    private static final List<M_Block> MBlocks = M_Environment.getInstance().getBlocks();
    private static final List<M_Bullet> bullets = M_Environment.getInstance().getBullets();

    // updateEnemies() detects player bullet collisions with enemies, updates the location of all
    // enemies, and calls each enemy's AI function.
    public static void updateEnemies() {
        for (int j = 0; j < enemies.size(); ++j) {
            // get the mEnemy
            M_Enemy mEnemy = enemies.get(j);
            // handle all bullet collisions with the mEnemy
            detectEnemyCollision(mEnemy);
            // move the mEnemy
            moveEnemy(mEnemy);
            // handle any new bullet collisions with the mEnemy
            detectEnemyCollision(mEnemy);
            // run the mEnemy's AI
            runAI(mEnemy);
        }
    }


    // moveEnemy(mEnemy) moves mEnemy and updates its velocity
    private static void moveEnemy(M_Enemy mEnemy) {
        // load the mEnemy's current location into a temp variable
        Point tempLoc = new Point(mEnemy.getLocation());
        // move the temp variable horizontally according to the mEnemy's velocity
        tempLoc.offset(mEnemy.getVelocity().x, 0);
        // Iterate through all MBlocks, seeing if this movement would cause mEnemy to intersect the block
        for (int i = 0; i < MBlocks.size(); ++i) {
            M_Block tempMBlock = MBlocks.get(i);
            // if a block would intersect mEnemy, move the temp location back, toggle the direction
            // and invert the horizontal velocity.
            // Also, break.
            if (C_EnvironmentController.boxIntersect(tempLoc, mEnemy.getDimensions(), tempMBlock.getLocation(), tempMBlock.getDimensions())) {
                tempLoc.set(tempLoc.x - mEnemy.getVelocity().x, tempLoc.y);
                mEnemy.setVelocityX(mEnemy.getVelocity().x * -1);
                mEnemy.setDirection(mEnemy.getDirection() * -1);
                break;
            }
        }
        // move the temp variable vertically according to the mEnemy's velocity
        tempLoc.offset(0, mEnemy.getVelocity().y);
        // Iterate through all MBlocks, seeing if this movement would cause mEnemy to intersect the block
        for (int i = 0; i < MBlocks.size(); ++i) {
            M_Block tempMBlock = MBlocks.get(i);
            // if a block would intersect mEnemy, move the temp location back and reduce mEnemy's vertical velocity to zero.
            // Also, break.
            if (C_EnvironmentController.boxIntersect(tempLoc, mEnemy.getDimensions(), tempMBlock.getLocation(), tempMBlock.getDimensions())) {
                tempLoc.offset(0, -mEnemy.getVelocity().y);
                mEnemy.setVelocityY(0);
                break;
            }
        }
        // if the mEnemy isn't standing on a block, start him falling.
        if (!C_EnvironmentController.onBlock(mEnemy)) {
            mEnemy.setVelocityY(C_EnvironmentController.GRAVITY);
        } else { // otherwise, stop his falling
            mEnemy.setVelocityY(0);
        }

        mEnemy.setLocation(tempLoc);   // set the character's new location
    }

    // detectEnemyCollision(mEnemy) checks for collisions with player bullets and collisions with the
    // player
    private static void detectEnemyCollision(M_Enemy mEnemy) {
        // iterate through all bullets
        for (int i = 0; i < bullets.size(); ++i) {
            // get the current mBullet
            M_Bullet mBullet = bullets.get(i);
            // if the mBullet is an mEnemy mBullet or has been flagged
            if (mBullet.isEnemyBullet() || mBullet.getFlag()) {
                // skip this mBullet
                continue;
            }
            Point bulletLoc = mBullet.getLocation();    // get the mBullet's current location
            Point bulletDims = mBullet.getDimensions(); // get the mBullet's dimensions
            // if the mBullet hit the mEnemy
            if (C_EnvironmentController.boxIntersect(bulletLoc, bulletDims, mEnemy.getLocation(), mEnemy.getDimensions())) {
                // lower the mEnemy's health
                mEnemy.setHealth(mEnemy.getHealth() - Math.max(0, (mBullet.getPower() - mEnemy.getDefense())));
                // flag the mBullet for deletion
                mBullet.setFlag(true);
            }
        }
        M_Character player = M_Character.getInstance();
        // if the player character is not immune...
        if (player.getImmunity() == 0) {
            // check if we're intersecting the player character.
            if (C_EnvironmentController.boxIntersect(mEnemy.getLocation(), mEnemy.getDimensions(), player.getLocation(), player.getDimensions())) {
                // damage the player based on the mEnemy's strength
                C_CharacterController.damage(mEnemy.getStrength());
            }
        }
    }

    // runAI(mEnemy) causes mEnemy to behave according to its AI type.
    private static void runAI(M_Enemy mEnemy) {
        switch (mEnemy.getEnemyType()) {
            case 0:
                crawlerAI(mEnemy);
                break;
            case 1:
                turretAI(mEnemy);
                break;
        }
    }

    // crawlerAI(mEnemy) causes mEnemy to start moving to the left if its horizontal velocity is zero
    private static void crawlerAI(M_Enemy mEnemy) {
        if (mEnemy.getVelocity().x == 0) {
            mEnemy.setVelocityX(-1 * mEnemy.getSpeed());
            mEnemy.setDirection(-1);
        }
    }

    // turretAI(mEnemy) causes mEnemy to fire a bullet in the direction of the player character
    // if the character is within thirty units of  a horizontal line from mEnemy's center (doesn't
    // account for obstructions.)
    private static void turretAI(M_Enemy mEnemy) {
        // if the turret's cooldown isn't complete, it can't shoot, so decrement the cooldown
        // and return
        if (mEnemy.getShotCooldown() > 0) {
            mEnemy.decrementShotCooldown();
            return;
        }
        M_Character playerChar = M_Character.getInstance();     // get the locations of the player and mEnemy
        Point playerLoc = playerChar.getLocation();
        Point enemyLoc = mEnemy.getLocation();               // get the player's height
        int playerHeight = playerChar.getDimensions().y;
        // the mEnemy fires from its center, so its vertical firing level is equal to the y-coordinate
        // of its top-left corner plus half of its height.
        int enemyFiringLevel = enemyLoc.y + (mEnemy.getDimensions().y) / 2;
        // calculate the minimum level for the player's top-left corner when its lower edge is within 30
        // units of the mEnemy's firing level
        int topBound = enemyFiringLevel - playerHeight - 30;
        // calculate the maximum level for the player's top-left corner
        int lowBound = enemyFiringLevel + 30;
        // if the player character is within the specified boundaries...
        if (topBound <= playerLoc.y && playerLoc.y <= lowBound) {
            // have mEnemy fire a new bullet, then add it to the environment
            M_Bullet newShot = mEnemy.shoot();
            M_Environment.getInstance().getBullets().add(newShot);
        }

    }
}
