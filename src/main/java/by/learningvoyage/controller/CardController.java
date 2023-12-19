package by.learningvoyage.controller;

import by.learningvoyage.model.Card;
import by.learningvoyage.service.CardService;
import by.learningvoyage.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/create/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("")
    public String showCreateCardPage() {
        return "ShowCreateCardPage";
    }


    @PostMapping("")
    public String saveCard(@RequestParam("question") String question,
                           @RequestParam("firstAnswer") String first,
                           @RequestParam("secondAnswer") String second,
                           @RequestParam("thirdAnswer") String third,
                           @RequestParam("fourthAnswer") String fourth) {


        Card card = new Card();

        String[] checkboxParams = new String[]{first, second, third, fourth};

        Integer correctAnswerId = cardService.getIdFromPage(checkboxParams);
        List<String> answers = cardService.getAnswers(checkboxParams);

        card.setQuestion(question);
        card.setFirstAnswer(answers.get(0));
        card.setSecondAnswer(answers.get(1));
        card.setThirdAnswer(answers.get(2));
        card.setFourthAnswer(answers.get(3));
        card.setCorrectAnswerId(correctAnswerId);

        card.setCorrectAnswer(cardService.getCorrectAnswer(correctAnswerId,card));

        cardService.create(card);

        return "redirect:/create/card";
    }
}
