package com.test.platformerse1;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

// Author: Isaiah Thacker
// Last Modified: 4/28/16 by Isaiah Thacker
// Edit by Tayo Elelu 04.28  - adding in additional tutorial level
// Iteration 4
// Records the layouts for all of the levels in the game.

public class M_LevelVault {
    // constant dimensions of blocks. May be set to vary later
    private static final Point blockDimensions = new Point(20, 20);

    // getLevel(i) returns the level with ID i
    public static M_Level getLevel(int ID) {
        switch (ID) {
            case 0:
                return levelZero();
            default:
                return levelOne();
        }
    }

    public static M_Level levelZero(){
        return levelOne();
    }

    // defining level one
    public static M_Level levelOne() {
        int id = 1;
        List<M_Block> blocks1 = blocksOne();
        M_Record goal1 = new M_Record(new Point(26, 3), false);
        List<M_Enemy> enemies1 = enemiesOne();
        List<M_PopupTrigger> popups1 = popupsOne();
        Point starting1 = new Point(20, 140);

        return new M_Level(id, blocks1, new ArrayList<M_Record>(), goal1, enemies1, popups1, starting1);
    }

    //Tutorial Level
    public static M_Level tutorial()
    {
        List<M_Block> blocksTut = blocksTutorial();
        M_Record goal1 = new M_Record(new Point(24, 2), false);

        int id = 0;

        List<M_Enemy> enemies1 = enemiesTutorial();
        List<M_PopupTrigger> popups1 = popupsOne();

        Point startTut = new Point(40, 100);

        return new M_Level(0, blocksTut, new ArrayList<M_Record>(), goal1, enemies1, popups1, startTut);
    }

    //blocks for tutorial level
    private static ArrayList<M_Block> blocksTutorial() {
        ArrayList<M_Block> blocksTut = new ArrayList<>();

        //vertical column
        blocksTut.add(new M_Block(new Point(1, 1), blockDimensions));
        blocksTut.add(new M_Block(new Point(1, 2), blockDimensions));
        blocksTut.add(new M_Block(new Point(1, 3), blockDimensions));
        blocksTut.add(new M_Block(new Point(1, 4), blockDimensions));
        blocksTut.add(new M_Block(new Point(1, 5), blockDimensions));
        blocksTut.add(new M_Block(new Point(1, 6), blockDimensions));

        //Horizontal
        blocksTut.add(new M_Block(new Point(2, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(3, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(4, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(5, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(6, 6), blockDimensions));

        //Pit 1
        blocksTut.add(new M_Block(new Point(6, 7), blockDimensions));
        blocksTut.add(new M_Block(new Point(6, 8), blockDimensions));
        blocksTut.add(new M_Block(new Point(7, 8), blockDimensions));
        blocksTut.add(new M_Block(new Point(8, 8), blockDimensions));
        blocksTut.add(new M_Block(new Point(8, 7), blockDimensions));

        blocksTut.add(new M_Block(new Point(8, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(9, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(10, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(11, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(12, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(13, 6), blockDimensions));


        //Pit 2
        blocksTut.add(new M_Block(new Point(13, 7), blockDimensions));
        blocksTut.add(new M_Block(new Point(14, 7), blockDimensions));
        blocksTut.add(new M_Block(new Point(15, 7), blockDimensions));
        blocksTut.add(new M_Block(new Point(16, 7), blockDimensions));
        blocksTut.add(new M_Block(new Point(17, 7), blockDimensions));
        blocksTut.add(new M_Block(new Point(18, 7), blockDimensions));
        blocksTut.add(new M_Block(new Point(19, 7), blockDimensions));


        blocksTut.add(new M_Block(new Point(19, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(20, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(21, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(22, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(23, 6), blockDimensions));

        //vertical column
        blocksTut.add(new M_Block(new Point(24, 6), blockDimensions));
        blocksTut.add(new M_Block(new Point(24, 5), blockDimensions));
        blocksTut.add(new M_Block(new Point(24, 4), blockDimensions));
        blocksTut.add(new M_Block(new Point(24, 3), blockDimensions));
        blocksTut.add(new M_Block(new Point(24, 2), blockDimensions));
        blocksTut.add(new M_Block(new Point(24, 1), blockDimensions));

        return blocksTut;
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

    //Enemy for tutorial level
    private static ArrayList<M_Enemy> enemiesTutorial()
    {
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


    private static ArrayList<M_PopupTrigger> popupsTutorial()
    {
        ArrayList<M_PopupTrigger> popupsTut = new ArrayList<>();
        popupsTut.add(new M_PopupTrigger(new Point(40, 100), new Point(20, 20), "Tutorial", "Press L/R to move left or right."));
        popupsTut.add(new M_PopupTrigger(new Point(80, 100), new Point(20, 20), "Tutorial", "Great! Keep on going."));
        popupsTut.add(new M_PopupTrigger(new Point(120, 100), new Point(20, 20), "Tutorial", "Press ^ to jump over the pit."));
        popupsTut.add(new M_PopupTrigger(new Point(240, 100), new Point(20, 20), "Tutorial", "Press X to shoot bullets into the enemy. Kill it!"));
        popupsTut.add(new M_PopupTrigger(new Point(400, 100), new Point(20, 20), "Tutorial", "Jump into the record to complete the level."));

        return popupsTut;
    }

}
