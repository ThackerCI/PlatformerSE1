package com.test.platformerse1;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

// Author: Isaiah Thacker
// Last Modified: 4/11/16 by Isaiah Thacker
// Iteration 3
// Records the layouts for all of the levels in the game.

public class M_LevelVault {
    // constant dimensions of blocks. May be set to vary later
    private static final Point blockDimensions = new Point(20, 20);

    // defining level one
    public static M_Level levelOne() {
        List<M_Block> blocks1 = blocksOne();
        M_Record goal1 = new M_Record(new Point(26, 3), false);
        List<M_Enemy> enemies1 = enemiesOne();
        Point starting1 = new Point(20, 140);

        return new M_Level(blocks1, new ArrayList<M_Record>(), goal1, enemies1, starting1);
    }

    // defining the blocks of level one
    private static ArrayList<M_Block> blocksOne() {
        ArrayList<M_Block> blocks1 = new ArrayList<>();
        blocks1.add(new M_Block(new Point(0, 1), blockDimensions));
        blocks1.add(new M_Block(new Point(0, 2), blockDimensions));
        blocks1.add(new M_Block(new Point(0, 3), blockDimensions));
        blocks1.add(new M_Block(new Point(0, 4), blockDimensions));
        blocks1.add(new M_Block(new Point(0, 5), blockDimensions));
        blocks1.add(new M_Block(new Point(0, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(0, 7), blockDimensions));
        blocks1.add(new M_Block(new Point(0, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(1, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(1, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(2, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(2, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(3, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(4, 4), blockDimensions));
        blocks1.add(new M_Block(new Point(4, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(5, 4), blockDimensions));
        blocks1.add(new M_Block(new Point(5, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(6, 4), blockDimensions));
        blocks1.add(new M_Block(new Point(6, 5), blockDimensions));
        blocks1.add(new M_Block(new Point(6, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(6, 7), blockDimensions));
        blocks1.add(new M_Block(new Point(6, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(7, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(8, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(9, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(9, 7), blockDimensions));
        blocks1.add(new M_Block(new Point(9, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(10, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(11, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(12, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(12, 7), blockDimensions));
        blocks1.add(new M_Block(new Point(12, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(13, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(14, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(15, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(16, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(17, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(17, 7), blockDimensions));
        blocks1.add(new M_Block(new Point(17, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(18, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(19, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(20, 8), blockDimensions));
        blocks1.add(new M_Block(new Point(20, 7), blockDimensions));
        blocks1.add(new M_Block(new Point(20, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(20, 5), blockDimensions));
        blocks1.add(new M_Block(new Point(21, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(22, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(23, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(24, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(25, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(26, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(25, 5), blockDimensions));
        blocks1.add(new M_Block(new Point(26, 5), blockDimensions));
        blocks1.add(new M_Block(new Point(26, 4), blockDimensions));
        blocks1.add(new M_Block(new Point(26, 1), blockDimensions));
        blocks1.add(new M_Block(new Point(27, 1), blockDimensions));
        blocks1.add(new M_Block(new Point(27, 2), blockDimensions));
        blocks1.add(new M_Block(new Point(27, 3), blockDimensions));
        blocks1.add(new M_Block(new Point(27, 4), blockDimensions));
        return blocks1;
    }

    private static ArrayList<M_Enemy> enemiesOne() {
        ArrayList<M_Enemy> enemies1 = new ArrayList<>();
        enemies1.add(new M_Enemy(new Point(5, 3), 1, new Point(20, 20), 0, 1, 1, 5, R.drawable.crawler, 0));
        enemies1.add(new M_Enemy(new Point(24, 5), 1, new Point(20, 20), 0, 1, 1, 5, R.drawable.crawler, 0));
        M_Enemy turret = new M_Enemy(new Point(15, 5), -1, new Point(20, 20), 0, 0, 2, 5, R.drawable.turret, 1);
        turret.setShotInterval(45);
        enemies1.add(turret);
        return enemies1;
    }
}