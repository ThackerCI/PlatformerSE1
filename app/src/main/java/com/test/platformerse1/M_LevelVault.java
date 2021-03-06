package com.test.platformerse1;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Isaiah Thacker
 *         Last Modified: 5/9/16 by Isaiah Thacker
 *         Edit by Tayo Elelu 04.28  - adding in additional tutorial level
 *         Iteration 4
 *         Records the layouts for all of the levels in the game.
 */

class M_LevelVault {
    // constant dimensions of blocks. May be set to vary later
    private static final Point blockDimensions = new Point(20, 20);

    // getLevel(i) returns the level with ID i
    public static M_Level getLevel(int ID) {
        switch (ID) {
            case 0:
                return tutorial();
            default:
                return levelOne();
        }
    }

    // defining level one
    private static M_Level levelOne() {
        int id = 1;
        List<M_Block> blocks1 = blocksOne();
        M_Record goal1 = new M_Record(new Point(26, 3), false);
        List<M_Enemy> enemies1 = enemiesOne();
        List<M_PopupTrigger> popups1 = popupsOne();
        Point starting1 = new Point(20, 140);

        return new M_Level(id, blocks1, new ArrayList<M_Record>(), goal1, enemies1, popups1, starting1);
    }

    //Tutorial Level
    private static M_Level tutorial() {
        List<M_Block> blocksTut = blocksTutorial();
        M_Record goal1 = new M_Record(new Point(22, 5), false);

        int id = 0;

        List<M_Enemy> enemies1 = enemiesTutorial();
        List<M_PopupTrigger> popups1 = popupsTutorial();

        Point startTut = new Point(40, 100);

        return new M_Level(0, blocksTut, new ArrayList<M_Record>(), goal1, enemies1, popups1, startTut);
    }

    //blocks for tutorial level
    private static ArrayList<M_Block> blocksTutorial() {

        // list all of the points where blocks will exist
        ArrayList<Point> blockPoints = new ArrayList<>();

        //vertical column
        blockPoints.add(new Point(1, 1));
        blockPoints.add(new Point(1, 2));
        blockPoints.add(new Point(1, 3));
        blockPoints.add(new Point(1, 4));
        blockPoints.add(new Point(1, 5));
        blockPoints.add(new Point(1, 6));

        //Horizontal
        blockPoints.add(new Point(2, 6));
        blockPoints.add(new Point(3, 6));
        blockPoints.add(new Point(4, 6));
        blockPoints.add(new Point(5, 6));
        blockPoints.add(new Point(6, 6));

        //Pit 1
        blockPoints.add(new Point(6, 7));
        blockPoints.add(new Point(6, 8));
        blockPoints.add(new Point(7, 8));
        blockPoints.add(new Point(8, 8));
        blockPoints.add(new Point(9, 8));
        blockPoints.add(new Point(9, 7));

        blockPoints.add(new Point(9, 6));
        blockPoints.add(new Point(10, 6));
        blockPoints.add(new Point(11, 6));
        blockPoints.add(new Point(12, 6));
        blockPoints.add(new Point(13, 6));


        //Pit 2
        blockPoints.add(new Point(13, 7));
        blockPoints.add(new Point(14, 7));
        blockPoints.add(new Point(15, 7));
        blockPoints.add(new Point(16, 7));
        blockPoints.add(new Point(17, 7));
        blockPoints.add(new Point(18, 7));
        blockPoints.add(new Point(19, 7));


        blockPoints.add(new Point(19, 6));
        blockPoints.add(new Point(20, 6));
        blockPoints.add(new Point(21, 6));
        blockPoints.add(new Point(22, 6));
        blockPoints.add(new Point(23, 6));

        //vertical column
        blockPoints.add(new Point(24, 6));
        blockPoints.add(new Point(24, 5));
        blockPoints.add(new Point(24, 4));
        blockPoints.add(new Point(24, 3));
        blockPoints.add(new Point(24, 2));
        blockPoints.add(new Point(24, 1));


        // "convert" the points into blocks
        ArrayList<M_Block> blocksTut = new ArrayList<>();
        for (Point point : blockPoints) {
            blocksTut.add(new M_Block(point, blockDimensions));
        }

        return blocksTut;
    }

    // defining the blocks of level one
    private static ArrayList<M_Block> blocksOne() {

        // list the points where blocks will be located
        ArrayList<Point> points1 = new ArrayList<>();
        points1.add(new Point(0, 1));
        points1.add(new Point(0, 2));
        points1.add(new Point(0, 3));
        points1.add(new Point(0, 4));
        points1.add(new Point(0, 5));
        points1.add(new Point(0, 6));
        points1.add(new Point(0, 7));
        points1.add(new Point(0, 8));
        points1.add(new Point(1, 6));
        points1.add(new Point(1, 8));
        points1.add(new Point(2, 6));
        points1.add(new Point(2, 8));
        points1.add(new Point(3, 8));
        points1.add(new Point(4, 4));
        points1.add(new Point(4, 8));
        points1.add(new Point(5, 4));
        points1.add(new Point(5, 8));
        points1.add(new Point(6, 4));
        points1.add(new Point(6, 5));
        points1.add(new Point(6, 6));
        points1.add(new Point(6, 7));
        points1.add(new Point(6, 8));
        points1.add(new Point(7, 6));
        points1.add(new Point(8, 6));
        points1.add(new Point(9, 6));
        points1.add(new Point(9, 7));
        points1.add(new Point(9, 8));
        points1.add(new Point(10, 8));
        points1.add(new Point(11, 8));
        points1.add(new Point(12, 8));
        points1.add(new Point(12, 7));
        points1.add(new Point(12, 6));
        points1.add(new Point(13, 6));
        points1.add(new Point(14, 6));
        points1.add(new Point(15, 6));
        points1.add(new Point(16, 6));
        points1.add(new Point(17, 8));
        points1.add(new Point(17, 7));
        points1.add(new Point(17, 6));
        points1.add(new Point(18, 8));
        points1.add(new Point(19, 8));
        points1.add(new Point(20, 8));
        points1.add(new Point(20, 7));
        points1.add(new Point(20, 6));
        points1.add(new Point(20, 5));
        points1.add(new Point(21, 6));
        points1.add(new Point(22, 6));
        points1.add(new Point(23, 6));
        points1.add(new Point(24, 6));
        points1.add(new Point(25, 6));
        points1.add(new Point(26, 6));
        points1.add(new Point(25, 5));
        points1.add(new Point(26, 5));
        points1.add(new Point(26, 4));
        points1.add(new Point(26, 1));
        points1.add(new Point(27, 1));
        points1.add(new Point(27, 2));
        points1.add(new Point(27, 3));
        points1.add(new Point(27, 4));

        // "convert" those points into blocks
        ArrayList<M_Block> blocks1 = new ArrayList<>();
        for (Point point : points1) {
            blocks1.add(new M_Block(point, blockDimensions));
        }

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

    //Enemy for tutorial level
    private static ArrayList<M_Enemy> enemiesTutorial() {
        ArrayList<M_Enemy> enemies1 = new ArrayList<>();
        enemies1.add(new M_Enemy(new Point(16, 6), 1, new Point(20, 20), 0, 1, 1, 5, R.drawable.crawler, 0));

        return enemies1;
    }

    private static ArrayList<M_PopupTrigger> popupsOne() {
        return new ArrayList<>();
//        ArrayList<M_PopupTrigger> popups1 = new ArrayList<>();
//        popups1.add(new M_PopupTrigger(new Point(60, 140), new Point(20, 20), "Congrats!", "The System is Working!"));
//        return popups1;
    }


    private static ArrayList<M_PopupTrigger> popupsTutorial() {
        ArrayList<M_PopupTrigger> popupsTut = new ArrayList<>();
        popupsTut.add(new M_PopupTrigger(new Point(40, 60), new Point(20, 60), "Tutorial", "Press the left arrow to move left, and the right arrow to move right."));
        popupsTut.add(new M_PopupTrigger(new Point(80, 60), new Point(20, 60), "Tutorial", "Great! Keep on going."));
        popupsTut.add(new M_PopupTrigger(new Point(120, 60), new Point(20, 60), "Tutorial", "Press the up arrow to jump over the pit."));
        popupsTut.add(new M_PopupTrigger(new Point(240, 60), new Point(20, 60), "Tutorial", "Press the crosshairs to shoot bullets into the enemy. Kill it!"));
        popupsTut.add(new M_PopupTrigger(new Point(400, 60), new Point(20, 60), "Tutorial", "Touch the record to complete the level."));

        return popupsTut;
    }

}
