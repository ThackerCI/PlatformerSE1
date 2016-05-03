package com.test.platformerse1;

import android.content.Context;
import android.graphics.Point;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


// Author: Aaron Trusty
// Last Modified: 5/1/16 by Aaron Trusty
// Iteration 4
// Stores scores.

public class M_HighScores
{
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    ArrayList <Double> scoreList = new ArrayList<>(5);
    int level_ID;

    //constructor
    public M_HighScores(FileInputStream inputStream, FileOutputStream outputStream) //get input/output streams based on filename
    {
        fileInputStream = inputStream;
        fileOutputStream = outputStream;
    }


    public void setScoreList(ArrayList<Double> scoreList) {
        this.scoreList = scoreList;
    }

    //fills scoreList with 3 doubles all stored in memory
    public void readScores() { //strange
        //    scoreList.add(0,10000000.0);
        //    scoreList.add(1,10000000.0);
        //    scoreList.add(2,10000000.0);
        try {
            String score = ""; //string as needed for the readLine() for BufferedReader
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((score = bufferedReader.readLine()) != null) //if line exists
            {
                scoreList.add(Double.parseDouble(score));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeScore(double timeToWrite) {
        try {
            if(timeToWrite < scoreList.get(level_ID)) //the score is larger than the element at index level_ID
            {
                scoreList.add(level_ID, timeToWrite);
                fileOutputStream.write(String.valueOf(scoreList.get(level_ID)).getBytes()); //stores string into bytes array
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Double> getScoreList()
    {
        return scoreList;
    }

    public void setLevel_ID(int id)
    {
        level_ID = id;
    }
}
