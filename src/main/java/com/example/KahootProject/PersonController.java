package com.example.KahootProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.Arrays;

@Controller
public class PersonController {

    @GetMapping
    String getPeople(Model model) {
        Game g = new Game();
        model.addAttribute("Question1", g.getQuestionSet().get(0));
        return "people";
    }
}
