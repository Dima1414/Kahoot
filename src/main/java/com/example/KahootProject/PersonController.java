package com.example.KahootProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {

    private int scoreCount = 0;

    @GetMapping
    String getPeople(Model model) {
        Game g = new Game();
        /*
        for(int i = 0; i < 4; i++) {
            model.addAttribute("question" + (i + 1), g.getQuestionSet().get(i));
        }

         */


        model.addAttribute("nextQuestion", g.getQuestionSet().get((int)(Math.random() * 3)));

        return "kahootQuestion";
    }
    @PostMapping("/answer")
    String answer(@RequestParam String answerString, @RequestParam String trueAnswer, Model model) {
        Game g = new Game();
        int rand = (int)(Math.random() * 3);
        if(trueAnswer.equals(answerString)) {
            scoreCount++;
        } else {
            scoreCount = 0;
        }
        model.addAttribute("answer", answerString);
        model.addAttribute("score", scoreCount);
        model.addAttribute("nextQuestion", g.getQuestionSet().get(rand)); // next question, del .getChoices().get(rand)

        return "kahootQuestion";
    }
}
