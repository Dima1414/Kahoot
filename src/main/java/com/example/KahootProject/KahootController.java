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

    private int currentQuestionIndex = 0;
    private Game game;
    private ArrayList<Question> questions;

    @GetMapping("/")
    public String showStartPage(Model model) {
        return "startPage";
    }

    @PostMapping("/start")
    public String startQuiz(Model model) {
        scoreCount = 0;
        currentQuestionIndex = 0;
        game = new Game();
        questions = game.getQuestionSet();
        Collections.shuffle(questions);
        getNextQuestion(model);
        model.addAttribute("score", scoreCount);
        model.addAttribute("maxScore", maxScore);
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

        model.addAttribute("score", scoreCount);
        model.addAttribute("maxScore", maxScore);
        getNextQuestion(model);
        return "kahootQuestion";
    }

    public void getNextQuestion(Model model) {
        if (questions != null && currentQuestionIndex < questions.size()) {
            model.addAttribute("nextQuestion", questions.get(currentQuestionIndex));
        }
    }
}
