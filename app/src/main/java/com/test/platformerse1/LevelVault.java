package com.test.platformerse1;
import android.graphics.Point;
import java.util.ArrayList;
import java.util.List;

// Author: Isaiah Thacker
// Last Modified: 4/01/16 by Isaiah Thacker
// Iteration 3
// Records the layouts for all of the levels in the game.

public class LevelVault {
    // constant dimensions of blocks. May be set to vary later
    private static final Point blockDimensions = new Point(30, 30);

    // defining level one
    public static M_Level levelOne() {
        List<M_Block> blocks1 = blocksOne();
        M_Record goal1 = new M_Record(new Point(8, 1), R.raw.baewolf, false, 2, 2, 2,2);
        List<M_Enemy> enemies1 = enemiesOne();
        Point starting1 = new Point(150, 90);

        return new M_Level(blocks1, new ArrayList<M_Record>(), goal1, enemies1, starting1);
    }

    // defining the blocks of level one
    private static ArrayList<M_Block> blocksOne() {
        ArrayList<M_Block> blocks1 = new ArrayList<>();
        blocks1.add(new M_Block(new Point(3, 2), blockDimensions));
        blocks1.add(new M_Block(new Point(4, 2), blockDimensions));
        blocks1.add(new M_Block(new Point(4, 3), blockDimensions));
        blocks1.add(new M_Block(new Point(4, 4), blockDimensions));
        blocks1.add(new M_Block(new Point(4, 5), blockDimensions));
        blocks1.add(new M_Block(new Point(4, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(5, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(2, 2), blockDimensions));
        blocks1.add(new M_Block(new Point(6, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(7, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(8, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(9, 6), blockDimensions));
        blocks1.add(new M_Block(new Point(8, 5), blockDimensions));
        blocks1.add(new M_Block(new Point(9, 5), blockDimensions));
        blocks1.add(new M_Block(new Point(9, 4), blockDimensions));
        blocks1.add(new M_Block(new Point(10, 4), blockDimensions));
        blocks1.add(new M_Block(new Point(11, 4), blockDimensions));
        blocks1.add(new M_Block(new Point(11, 3), blockDimensions));
        blocks1.add(new M_Block(new Point(11, 2), blockDimensions));
        blocks1.add(new M_Block(new Point(11, 1), blockDimensions));
        return blocks1;
    }

    private static ArrayList<M_Enemy> enemiesOne() {
        ArrayList<M_Enemy> enemies1 = new ArrayList<>();
        enemies1.add(new M_Enemy(new Point(7, 2), 1, new Point(30, 30), 0, 1, 1, 5, R.drawable.crawler, 0));
        M_Enemy turret = new M_Enemy(new Point(10, 3), -1, new Point(30, 30), 0, 0, 2, 5, R.drawable.turret, 1);
        turret.setShotInterval(45);
        enemies1.add(turret);
        return enemies1;
    }
}
