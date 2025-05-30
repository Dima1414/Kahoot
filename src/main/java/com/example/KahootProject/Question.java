package com.example.KahootProject;

import com.example.databases.Choice;

import java.util.ArrayList;
import java.util.Collections;

public class Question {
    private String question;
    private ArrayList<Choice> choices;
    private String correctAnswer;
    public Question(String question, ArrayList<Choice> choices) {
        this.question = question;
        this.choices = new ArrayList<>();
        for (int i = 0; i < choices.size(); i++) {
            this.choices.add(choices.get(i));
        }

        shuffleAndUpdateCorrectAnswer();
    }

    private int getCorrectAnswerIndex() {
        double temp;
        double max = 0;
        int index = -1;
        for(int i = 0; i < 4; i++) {
            temp = choices.get(i).getStat();
            if(temp > max) {
                max = temp;
                index = i;
            }
        }
        return index;
    }

    public void shuffleAndUpdateCorrectAnswer() {
        Collections.shuffle(this.choices);
        correctAnswer = choices.get(getCorrectAnswerIndex()).getName();
    }
    public String getQuestion() {
        return question;
    }
    public ArrayList<Choice> getChoices() {
        return choices;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
