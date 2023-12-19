package by.learningvoyage.controller;

import by.learningvoyage.model.Card;
import by.learningvoyage.model.Subcategory;
import by.learningvoyage.service.CardService;
import by.learningvoyage.service.CategoryService;
import by.learningvoyage.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


//FIXME Ты понимаешь, что ты в тарелку насрал, или нет?!
//FIXME Мойся, под струю!
@Controller
public class CardViewController {


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private CardService cardService;

    public static int counter = 0;

    @GetMapping("/category/{categoryId}")
    public String showSubcategories(@PathVariable("categoryId") Long id,
                                    Model model) {

        model.addAttribute("subcategory", categoryService.getById(id).getSubcategories());
        model.addAttribute("categoryId", id);
        return "ShowSubcategoriesPage";
    }

    @GetMapping("/category/{categoryId}/subcategory/{subcategoryId}")
    public String showCards(@PathVariable("subcategoryId") Long subcategoryId,
                            @PathVariable("categoryId") Long categoryId,
                            Model model) {
        List<Card> cards = cardService.getCardsBySubcategoryId(subcategoryId);

        if (counter >= cards.size()-1) {
            model.addAttribute("message", "It's over! Well done.");
            counter = 0;
            return "/";
        }

        model.addAttribute("message");

        model.addAttribute("cardQuestion", cards.get(counter).getQuestion());
        model.addAttribute("firstAnswer", cards.get(counter).getFirstAnswer());
        model.addAttribute("secondAnswer", cards.get(counter).getSecondAnswer());
        model.addAttribute("thirdAnswer", cards.get(counter).getThirdAnswer());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("cards", cards);
        model.addAttribute("subcategoryId", subcategoryId);

        counter++;

        return "ShowCards";
    }

    @PostMapping("/category/{categoryId}/subcategory/{subcategoryId}")
    public String getAnswer(Model model,
                            @PathVariable("categoryId") Long categoryId,
                            @PathVariable("subcategoryId") Long subcategoryId,
                            @RequestParam(value = "1st", required = false) String first,
                            @RequestParam(value = "2nd", required = false) String second,
                            @RequestParam(value = "3rd", required = false) String third,
                            @RequestParam(value = "4th", required = false) String fourth
    ) {

        List<Card> cards = cardService.getCardsBySubcategoryId(subcategoryId);

        List<String> values = new ArrayList<>();
        values.add(first);
        values.add(second);
        values.add(third);
        values.add(fourth);

        String answer = cardService.getValueFromCheckboxes(values);

        String correctAnswer = cards.get(counter).getCorrectAnswer();

        if(answer.equals(correctAnswer)){
            model.addAttribute("message", "It's a correct answer!");
        }
        else {
            model.addAttribute("message", "It's uncorrected answer! Right one :" + correctAnswer);
        }


        //return "redirect:/category/" + categoryId + "/subcategory/" + subcategoryId;
        return null;
    }
}
