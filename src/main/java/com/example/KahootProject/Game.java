package com.example.KahootProject;

import com.example.databases.*;

import java.util.*;

public class Game {
    private ArrayList<Question> questionSet;
    public Game()  {
        questionSet = new ArrayList<>();
        String q;

        DataBaseSalaries allSalaries = new DataBaseSalaries();
        q = "Who has the highest salary?";
        ArrayList<Choice> hghstSalary = allSalaries.getSalaryList();
        Collections.shuffle(hghstSalary);
        questionSet.add(new Question(q, hghstSalary));


//        DataBaseNetWorth allNetWorths = new DataBaseNetWorth();
//        q = "Who has the highest net worth?";
//        ArrayList<Choice> hghstNetWorth = allNetWorths.getNetWorthList();
//        Collections.shuffle(hghstNetWorth);
//        questionSet.add(new Question(q, hghstNetWorth));

        DataBaseLakersWeight allLakersWeight = new DataBaseLakersWeight();
        q = "Who has the largest weight on the Lakers?";
        ArrayList<Choice> lakersWeightList = allLakersWeight.getLakersWeight();
        Collections.shuffle(lakersWeightList);
        questionSet.add(new Question(q, lakersWeightList));

        DataBaseAvgPnts allAvePnts = new DataBaseAvgPnts();
        q = "Who has the greatest average points in a game?";
        ArrayList<Choice> avePointList = allAvePnts.getAvePointsList();
        Collections.shuffle(avePointList);
        questionSet.add(new Question(q, avePointList));

        q = "Who has the highest points in a game?";
        ArrayList<Choice> hghstPntsList = DataBaseHghstPnts.getHghstPnts();
        Collections.shuffle(hghstPntsList);
        questionSet.add(new Question(q, hghstPntsList));


    }
    public ArrayList<Question> getQuestionSet() {
        return questionSet;
    }
}
//comment