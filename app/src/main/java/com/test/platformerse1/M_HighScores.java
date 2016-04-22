package com.test.platformerse1;

import android.content.Context;
import android.graphics.Point;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron Trusty on 4/20/2016.
 */
public class M_HighScores
{
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    ArrayList <Double> scoreList = new ArrayList<Double>(5);

    //constructor
    public M_HighScores(FileInputStream inputStream, FileOutputStream outputStream) //get input/output streams based on filename
    {
        fileInputStream = inputStream;
        fileOutputStream = outputStream;
    }

    //fills scoreList with 5 doubles all stored in memory
    public void readScores() {
        //allocate room for top 5 scores
        try {
            String score = ""; //string as needed for the readLine() for BufferedReader
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int arrayIndex = 0;
            while ((score = bufferedReader.readLine()) != null && (arrayIndex<5)) //if line exists
            {
                scoreList.add(Double.parseDouble(score));
                arrayIndex++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeScore(double score) { //Probably some errors in this method. Will fix later FIXME
        double scoreToWrite = score;
        String scoreAsString = String.valueOf(scoreToWrite);
        try {
            int i; //iterator
            for(i=0;i<5;i++)
            {
                if(score > scoreList.get(i)) //the score is larger than the element at index i
                {
                    scoreList.add(i, scoreToWrite); //chop off at all indexes above 4
                }
                fileOutputStream.write(String.valueOf(scoreList.get(i)).getBytes());
            }
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int determineScore(double time)
    {
        if(time < 30.0)
        {
            return 500;
        }
        else if(time < 45.0)
        {
            return 100;
        }
        else
        {
            return 50;
        }
    }

    public ArrayList<Double> getScoreList()
    {
        return scoreList;
    }
}
