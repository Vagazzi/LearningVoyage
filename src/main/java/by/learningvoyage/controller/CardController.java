package by.learningvoyage.controller;

import by.learningvoyage.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/create/card")
    public String showCreateCardPage() {
        return "createCard";
    }


    @PostMapping("/create/card")
    public String saveCard(@RequestParam(value = "firstAnswer") String first,
                           @RequestParam(value = "secondAnswer") String second,
                           @RequestParam(value = "thirdAnswer") String third,
                           @RequestParam(value = "fourthAnswer") String fourth) {

        String[] checkboxParams = new String[]{first, second, third, fourth};

        Long id = cardService.getIdFromPage(checkboxParams);
        List<String> answers = cardService.getAnswers(checkboxParams);

        System.out.println(id);
        answers.forEach(System.out::println);

        return "redirect:/create/card";
    }
}
