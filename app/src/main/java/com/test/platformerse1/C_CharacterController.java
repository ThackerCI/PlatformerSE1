package com.test.platformerse1;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;

import java.util.List;

// Author: Isaiah Thacker
// Last Modified: 4/30/16 by John C. Hale
// Iteration 4
// The C_CharacterController class contains the methods for updating the state of the player
// character.
public class C_CharacterController {
    // get the blocks and the player character.
    private static final List<M_Block> blocks = M_Environment.getInstance().getBlocks();
    private static final M_Character player = M_Character.getInstance();

    // updateCharacter(playerChar) first moves character playerChar horizontally if doing so would
    // not cause the player to move into a block
    // If this condition is not satisfied, playerChar's horizontal velocity is set to zero.
    // updateCharacter(playerChar) then moves character playerChar vertically if doing so would not
    // cause the player to move into a block
    // If this condition is not satisfied, playerChar's vertical velocity is set to zero.
    public static void updateCharacter() {
        // load the character's current location into a temp variable
        Point tempLoc = new Point(player.getLocation().x, player.getLocation().y);
        // move the temp variable horizontally according to the character's velocity
        tempLoc.set(tempLoc.x + player.getVelocity().x, tempLoc.y);
        // Iterate through all blocks, seeing if this movement would cause playerChar to intersect the block
        for (int i = 0; i < blocks.size(); ++i) {
            M_Block tempMBlock = blocks.get(i);
            // if a block would intersect playerChar, move the temp location back. Keep velocity as
            // it is.
            // Also, break.
            if (C_EnvironmentController.boxIntersect(tempLoc, player.getDimensions(), tempMBlock.getLocation(), tempMBlock.getDimensions())) {
                tempLoc.set(tempLoc.x - player.getVelocity().x, tempLoc.y);
                break;
            }
        }
        // move the temp variable vertically according to the character's velocity
        tempLoc.offset(0, player.getVelocity().y);
        // Iterate through all blocks, seeing if this movement would cause playerChar to intersect the block
        for (int i = 0; i < blocks.size(); ++i) {
            M_Block tempMBlock = blocks.get(i);
            // if a block would intersect playerChar, move the temp location back and reduce playerChar's vertical velocity to zero.
            // Also, break.
            if (C_EnvironmentController.boxIntersect(tempLoc, player.getDimensions(), tempMBlock.getLocation(), tempMBlock.getDimensions())) {
                tempLoc.offset(0, -player.getVelocity().y);
                player.setVelocityY(0);
                break;
            }
        }
        // if the character isn't jumping and isn't standing on a block, start him falling.
        if (!C_EnvironmentController.onBlock(player) && player.getJumpTime() == 0) {
            player.setVelocityY(C_EnvironmentController.GRAVITY);
        }
        // decrement playerChar's jump time if applicable
        if (player.getJumpTime() > 0) {
            player.setJumpTime(player.getJumpTime() - 1);
        }

        player.setLocation(tempLoc);   // set the character's new location
        // If the player is immune, decrement its immunity time
        if (player.getImmunity() > 0) {
            player.decrementImmunity();
        }
    }

    /**
     * Resets the player's health, direction, jumpTime, and velocity
     */
    public static void reset() {
        player.setJumpTime(0);
        player.setHealth(player.getMaxHealth());
        player.setDirection(1);
        player.setVelocity(new Point(0, 0));
    }


    // c.horizontalMove(direction) sets character c's horizontal velocity to direction*c.speed,
    // and sets c.direction to direction.
    public static void horizontalMove(int direction) {
        //update the location of the entity based on move speeds
        player.setVelocityX(direction * player.getSpeed());
        player.setDirection(direction);
    }


    // if the character can jump, set his vertical velocity to the negative of the gravity and
    // reset the jump timer to maxJumpTime
    public static void jump(boolean canJump) {
        if (canJump) {
            player.setVelocityY(-C_EnvironmentController.GRAVITY);
            player.setJumpTime(player.getMaxJumpTime());
        }
    }

    // create a new bullet at the character's center
    public static M_Bullet shoot() {
        Point leftCorner = player.getLocation();
        // get the center of the character
        int x = leftCorner.x;
        int y = leftCorner.y;
        Point dim = player.getDimensions();
        int h = dim.y;
        int w = dim.x;
        h = h / 2;
        w = w / 2;
        Point center = new Point(x + w, y + h);
        // get the velocity for the bullet
        Point vel = new Point(player.getDirection() * 3, 0);
        // return the new bullet
        return new M_Bullet(center, player.getStrength(), vel, false);
    }

    // damage(dealt) grants the player a second of immunity. Later, it will be modified to work
    // with the scoring system.
    public static void damage(int dealt) {
        Log.d("Damage:", Integer.toString(dealt));

        // give the player 1 second of immunity
        player.setImmunity(30);
    }

    public static void applyPowerUp() {

        player.setStrength(M_CollectedRecords.getInstance().getPowerUp()[1]);
        player.setDefense(M_CollectedRecords.getInstance().getPowerUp()[2]);
        player.setSpeed(M_CollectedRecords.getInstance().getPowerUp()[3]);
        player.setImmunity(M_CollectedRecords.getInstance().getPowerUp()[4]);

    }


    public static void stopPowerUp() {
        player.setDefaultStats();
    }

}
