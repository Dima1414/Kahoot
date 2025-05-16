package com.example.KahootProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class PersonController {

    @GetMapping
    String getPeople(Model model) {
        Game g = new Game();
        /*
        for(int i = 0; i < 4; i++) {
            model.addAttribute("question" + (i + 1), g.getQuestionSet().get(i));
        }

         */


        model.addAttribute("question1", g.getQuestionSet().get((int)(Math.random() * 3)));

        return "people";
    }
}
