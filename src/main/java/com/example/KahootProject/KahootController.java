package com.example.KahootProject;

import com.example.databases.Choice;
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
    private int finalScore;
    private String checkIndicator;

    private int currentQuestionIndex = 0;
    private Game game;
    private ArrayList<Question> questions;

    @GetMapping("/")
    public String showStartPage() {
        return "startPage";
    }

    @PostMapping("/start")
    public String startQuiz(Model model) {
        finalScore = 0;
        scoreCount = 0;
        maxScore = 0;
        currentQuestionIndex = 0;
        checkIndicator = "";
        game = new Game();
        questions = game.getQuestionSet();
        Collections.shuffle(questions);
        getNextQuestion(model);
        model.addAttribute("score", scoreCount);
        model.addAttribute("finalScore", finalScore);
        model.addAttribute("maxScore", maxScore);
        model.addAttribute("currentQuestionIndex", currentQuestionIndex);
        model.addAttribute("checkIndicator", checkIndicator);
        return "kahootQuestion";
    }

    @PostMapping("/answer")
    public String answer(@RequestParam String answerString, @RequestParam String trueAnswer, Model model) {
        if (trueAnswer.equals(answerString)) {
            scoreCount++;
            finalScore++;
            checkIndicator = "Correct";
            if (scoreCount > maxScore) {
                maxScore = scoreCount;
            }
        } else {
            if (scoreCount >= maxScore) {
                maxScore = scoreCount;
            }
            checkIndicator = "Incorrect";
            scoreCount = 0;

        }

        currentQuestionIndex++;

        if (currentQuestionIndex >= 10) {
            model.addAttribute("finalScore", finalScore);
            model.addAttribute("maxScore", maxScore);
            model.addAttribute("currentQuestionIndex", currentQuestionIndex);
            return "gameOverPage";
        }

        model.addAttribute("score", scoreCount);
        model.addAttribute("maxScore", maxScore);
        model.addAttribute("finalScore", finalScore);
        model.addAttribute("currentQuestionIndex", currentQuestionIndex);
        model.addAttribute("checkIndicator", checkIndicator);
        getNextQuestion(model);
        return "kahootQuestion";
    }

    public void getNextQuestion(Model model) {
        if (questions != null && currentQuestionIndex < 10  ) {
            int randomIndex = (int)(Math.random() * questions.size());
            questions.get(randomIndex).shuffleAndUpdateCorrectAnswer();
            model.addAttribute("nextQuestion", questions.get(randomIndex));
        }
    }
}