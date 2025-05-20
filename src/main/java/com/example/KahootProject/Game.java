package com.example.KahootProject;

import com.example.databases.DataBaseAvePnts;

import java.util.*;

public class Game {
    private ArrayList<Question> questionSet;
    public Game()  {
        questionSet = new ArrayList<Question>();
        String q = "Who has the highest salary?";
        ArrayList<String> a = new ArrayList<>(Arrays.asList("Shaquille O'neal", "Lebron James", "Stephen Curry", "Larry Bird"));
        questionSet.add(new Question(q, a, "Lebron James"));
        q = "Who is the tallest?";
        a = new ArrayList<>(Arrays.asList("Michael Jordan", "Jayson Tatum", "Luka Doncic", "Draymond Green"));
        questionSet.add(new Question(q, a, "Jayson Tatum"));
        q = "Who scored the most points in a game?";
        a = new ArrayList<>(Arrays.asList("Stephen Curry", "Wilt Chamberlain", "Luka Doncic", "Lebron James"));
        questionSet.add(new Question(q, a, "Wilt Chamberlain"));

        DataBaseAvePnts allAvePnts = new DataBaseAvePnts();

        q = "Who has the greatest average points?";
        for(int i = 0; i < allAvePnts.getAvePointsList().size(); i++) {
            a.add(allAvePnts.getAvePointsList().get(i).getName());
        }
        questionSet.add(new Question(q, a, "Wilt Chamberlain"));

        // Collections.shuffle(questionSet, new Random());
    }
    public void start() {
        Scanner scan = new Scanner(System.in);
        int numCorrect = 0;
        for(int question = 0; question < questionSet.size(); question++) {
            System.out.println(questionSet.get(question).getQuestion());
            int numChoices = questionSet.get(question).getChoices().size();
            for(int choice = 0; choice < numChoices; choice++) {
                System.out.println((choice + 1) + ": " +
                        questionSet.get(question).getChoices().get(choice));

            }
            int playerAnswer = scan.nextInt();
            ArrayList<String> choiceSet = questionSet.get(question).getChoices();
            String correctAnswer = questionSet.get(question).getAnswer();
            int correctAnswerIndex = choiceSet.indexOf(correctAnswer);
            if(playerAnswer == correctAnswerIndex + 1) {
                numCorrect++;
            }
        }
        scan.close();
        System.out.println("You got " + numCorrect + " correct");
    }
    public ArrayList<Question> getQuestionSet() {
        return questionSet;
    }

}
//comment