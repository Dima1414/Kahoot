package com.example.KahootProject;

import com.example.databases.*;

import java.util.*;

public class Game {
    private ArrayList<Question> questionSet;
    public Game()  {
        questionSet = new ArrayList<>();
        String q;

//        DataBaseSalaries allSalaries = new DataBaseSalaries();
//        q = "Who has the highest salary?";
//        ArrayList<Choice> hghstSalary = allSalaries.getSalaryList();
//        Collections.shuffle(hghstSalary);
//        questionSet.add(new Question(q, hghstSalary));


//        DataBaseNetWorth allNetWorths = new DataBaseNetWorth();
//        q = "Who has the highest net worth?";
//        ArrayList<Choice> hghstNetWorth = allNetWorths.getNetWorthList();
//        Collections.shuffle(hghstNetWorth);
//        questionSet.add(new Question(q, hghstNetWorth));

//        DataBaseHghstPnts allHghstPnts = new DataBaseHghstPnts();
//        q = "Who has the highest number of points in a game?";
//        ArrayList<Choice> hghstPointList = allHghstPnts.getHghstPnts();
//        Collections.shuffle(hghstPointList);
//        questionSet.add(new Question(q, hghstPointList));

        DataBaseAvePnts allAvePnts = new DataBaseAvePnts();
        q = "Who has the greatest average points in a game?";
        ArrayList<Choice> avePointList = allAvePnts.getAvePointsList();
        Collections.shuffle(avePointList);
        questionSet.add(new Question(q, avePointList));
    }
    public ArrayList<Question> getQuestionSet() {
        return questionSet;
    }
}
//comment