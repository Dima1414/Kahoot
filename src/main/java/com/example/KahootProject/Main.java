package com.example.KahootProject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameRun game = new GameRun();
        do {
            System.out.println(game.getCurrentQuestion());
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your answer: ");
            System.out.println(game.getCurrentQuestion().getAnswer());
            String answer = scanner.nextLine();
            if (game.checkAnswer(answer)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
            System.out.println();
        } while (game.nextQuestion());
    }
}
