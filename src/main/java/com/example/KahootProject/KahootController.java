package com.example.KahootProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;

@Controller
public class KahootController {

    private int scoreCount = 0;
    private int maxScore = 0;

    // 💡 NEW: Add these fields to persist game state
    private int currentQuestionIndex = 0;
    private Game game;
    private int totalQuestionsAsked = 0;
    private ArrayList<Question> questions;

    // ✅ NEW: Display the start page at the root URL
    @GetMapping("/")
    public String showStartPage() {
        return "startPage"; // You need to create startPage.html in templates
    }

    // ✅ NEW: Starts the quiz when user clicks "Start"
    @PostMapping("/start")
    public String startQuiz(Model model) {
        scoreCount = 0;
        currentQuestionIndex = 0;
        totalQuestionsAsked = 0;
        game = new Game();
        questions = game.getQuestionSet();
        Collections.shuffle(questions); // Shuffle the full question set
        getNextQuestion(model);
        return "kahootQuestion";
    }

    @PostMapping("/answer")
    public String answer(@RequestParam String answerString, @RequestParam String trueAnswer, Model model) {
        if (trueAnswer.equals(answerString)) {
            scoreCount++;
            if (scoreCount > maxScore) {
                maxScore = scoreCount;
            }
        } else {
            if (scoreCount >= maxScore) {
                maxScore = scoreCount;
            }
            scoreCount = 0;
        }

        currentQuestionIndex++;
        totalQuestionsAsked++;

        if (totalQuestionsAsked >= 10) {
            model.addAttribute("finalScore", scoreCount);
            model.addAttribute("maxScore", maxScore);
            return "gameOverPage";
        }

        if (questions != null && currentQuestionIndex >= questions.size()) {
            currentQuestionIndex = 0; // Prevent going out of bounds
            Collections.shuffle(questions); // Optional: reshuffle for variety
        }

        model.addAttribute("score", scoreCount);
        model.addAttribute("maxScore", maxScore);
        getNextQuestion(model);
        return "kahootQuestion";
    }

    // ✅ Updated: Load next question from current index
    public void getNextQuestion(Model model) {
        if (questions != null && currentQuestionIndex < questions.size()) {
            model.addAttribute("nextQuestion", questions.get(currentQuestionIndex));
        }
    }
}
