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

    @GetMapping
    String getPeople(Model model) {
        getNextQuestion(model);

        return "kahootQuestion";
    }
    @PostMapping("/answer")
    String answer(@RequestParam String answerString, @RequestParam String trueAnswer, Model model) {
        if(trueAnswer.equals(answerString)) {
            scoreCount++;
        } else {
            scoreCount = 0;
        }
        model.addAttribute("answer", answerString);
        model.addAttribute("score", scoreCount);
        getNextQuestion(model);

        return "kahootQuestion";
    }

    public void getNextQuestion(Model model) {
        Game g = new Game();
        ArrayList<Question> qSet = g.getQuestionSet();
        Collections.shuffle(qSet);
        model.addAttribute("nextQuestion", qSet.get(0));
        // number of questions!!!
    }
}

// o
