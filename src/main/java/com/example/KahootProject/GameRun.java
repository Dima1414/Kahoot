package com.example.KahootProject;

import com.example.databases.Choice;
import com.example.databases.DataBaseAvgPnts1;
import com.example.databases.DataBaseHghstPnts;
import com.example.databases.DataBaseSalaries1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;


public class GameRun {
    private ArrayList<Choice> answerList;
    private ArrayList<CreateQuestion> questionSet;
    private Random random;
    private int currentQuestionIndex;
    private int score;

    public GameRun() {
        random = new Random();
        answerList = new ArrayList<>();
        questionSet = new ArrayList<>();

        ArrayList<Choice> tempPointsList = new ArrayList<>();
        Collections.shuffle(DataBaseHghstPnts.getHghstPnts());
        answerList.clear();
        for (int i = 0; i < Math.min(4, DataBaseHghstPnts.getHghstPnts().size()); i++) {
            Choice tempPlayer = DataBaseHghstPnts.getHghstPnts().get(i);
            tempPointsList.add(tempPlayer);
            answerList.add(tempPlayer);
        }

        String q = "Who has the highest career-high scoring points?";
        String[] a = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            a[i] = answerList.get(i).getName();
        }

        questionSet.add(new CreateQuestion(q, a, findHighestPoints(tempPointsList)));

        ArrayList<Choice> tempAvgList = new ArrayList<>();
        Collections.shuffle(DataBaseAvgPnts1.getAvgPointsList());
        answerList.clear();
        for (int i = 0; i < Math.min(4, DataBaseAvgPnts1.getAvgPointsList().size()); i++) {
            Choice tempPlayer = DataBaseAvgPnts1.getAvgPointsList().get(i);
            tempAvgList.add(tempPlayer);
            answerList.add(tempPlayer);
        }

        q = "Who had the highest average points per game in 2025?";
        a = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            a[i] = answerList.get(i).getName();
        }

        questionSet.add(new CreateQuestion(q, a, findHighestAverage(tempAvgList)));

        ArrayList<Choice> tempSalList = new ArrayList<>();
        Collections.shuffle(DataBaseSalaries1.getSalaryList());
        answerList.clear();
        for (int i = 0; i < Math.min(4, DataBaseSalaries1.getSalaryList().size()); i++) {
            Choice tempPlayer = DataBaseSalaries1.getSalaryList().get(i);
            tempSalList.add(tempPlayer);
            answerList.add(tempPlayer);
        }

        q = "Who had the highest salary?";
        a = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            a[i] = answerList.get(i).getName();
        }

        questionSet.add(new CreateQuestion(q, a, findHighestSalary(tempSalList)));
        Collections.shuffle(questionSet, new Random());
    }

    private String findHighestPoints(ArrayList<Choice> tempPointsList) {
        if (tempPointsList == null || tempPointsList.isEmpty()) return "No data";

        Choice maxPlayer = tempPointsList.get(0);
        for (Choice p : tempPointsList) {
            if (p.getStat() > maxPlayer.getStat()) {
                maxPlayer = p;
            }
        }

        return maxPlayer.getName();
    }

    private String findHighestAverage(ArrayList<Choice> tempAvgList) {
        if (tempAvgList == null || tempAvgList.isEmpty()) return "No data";

        Choice maxPlayer = tempAvgList.get(0);
        for (Choice p : tempAvgList) {
            if (p.getStat() > maxPlayer.getStat()) {
                maxPlayer = p;
            }
        }

        return maxPlayer.getName();
    }

    public static String findHighestSalary(ArrayList<Choice> tempSalList) {
        if (tempSalList == null || tempSalList.isEmpty()) return "No data";

        Choice maxPlayer = tempSalList.get(0);
        for (Choice p : tempSalList) {
            if (p.getStat() > maxPlayer.getStat()) {
                maxPlayer = p;
            }
        }

        return maxPlayer.getName();
    }

    public void startGame() {
        currentQuestionIndex = 0;
        score = 0;
    }

    public CreateQuestion getCurrentQuestion() {
        if (currentQuestionIndex < questionSet.size()) {
            return questionSet.get(currentQuestionIndex);
        }
        return null;
    }

    public boolean checkAnswer(String selectedAnswer) {
        CreateQuestion currentQuestion = getCurrentQuestion();
        if (currentQuestion != null) {
            if (selectedAnswer.equals(currentQuestion.getAnswer())) {
                score++;
                return true;
            }
        }
        return false;
    }

    public boolean nextQuestion() {
        currentQuestionIndex++;
        return currentQuestionIndex < questionSet.size();
    }

    public int getScore() {
        return score;
    }

    public void resetGame() {
        currentQuestionIndex = 0;
        score = 0;
        Collections.shuffle(questionSet, new Random());
    }
}