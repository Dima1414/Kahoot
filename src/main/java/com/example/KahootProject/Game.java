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

        DataBaseHghstPnts allHghstPnts = new DataBaseHghstPnts();
        q = "Who has the highest career-high points?";
        ArrayList<Choice> hghstPntsList = allHghstPnts.getHghstPnts();
        Collections.shuffle(hghstPntsList);
        questionSet.add(new Question(q, hghstPntsList));

        DataBaseAvgRebounds allAveRebounds = new DataBaseAvgRebounds();
        q = "Who has the greatest average rebounds in a game?";
        ArrayList<Choice> aveReboundsList = allAveRebounds.getRebounds();
        Collections.shuffle(aveReboundsList);
        questionSet.add(new Question(q, aveReboundsList));

        DataBaseAvgThreePoints allAvgThreePoints = new DataBaseAvgThreePoints();
        q = "Who has the greatest average three points in a game?";
        ArrayList<Choice> aveThreesList = allAvgThreePoints.getThrees();
        Collections.shuffle(aveThreesList);
        questionSet.add(new Question(q, aveThreesList));

        DataBaseAvgSTPG allAvgSTPG = new DataBaseAvgSTPG();
        q = "Who has the greatest average steals per game?";
        ArrayList<Choice> avgSTPGList = allAvgSTPG.getSTPGList();
        Collections.shuffle(avgSTPGList);
        questionSet.add(new Question(q, avgSTPGList));

        DataBaseAvgBLKPG allAvgBLKPG = new DataBaseAvgBLKPG();
        q = "Who has the greatest average blocks per game?";
        ArrayList<Choice> avgBlocksList = allAvgBLKPG.getAvgBLKPG();
        Collections.shuffle(avgBlocksList);
        questionSet.add(new Question(q, avgBlocksList));

        DataBaseAvgAPG allAvgAPG = new DataBaseAvgAPG();
        q = "Who has the greatest average assists per game?";
        ArrayList<Choice> avgAssistsList = allAvgAPG.getAvgAPG();
        Collections.shuffle(avgAssistsList);
        questionSet.add(new Question(q, avgAssistsList));

        Collections.shuffle(questionSet);
    }
    public ArrayList<Question> getQuestionSet() {
        return questionSet;
    }
}
//comment