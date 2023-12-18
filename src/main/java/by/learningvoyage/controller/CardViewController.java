package by.learningvoyage.controller;

import by.learningvoyage.model.Card;
import by.learningvoyage.model.Roles;
import by.learningvoyage.model.Subcategory;
import by.learningvoyage.model.User;
import by.learningvoyage.repository.UserRepository;
import by.learningvoyage.service.CardService;
import by.learningvoyage.service.CategoryService;
import by.learningvoyage.service.SubcategoryService;
import by.learningvoyage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;


//FIXME Ты понимаешь, что ты в тарелку насрал, или нет?!
//FIXME Мойся, под струю!
@Controller
public class CardViewController {

    int counterCards = 0;


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardService cardService;

    @GetMapping("/category/{categoryId}")
    public String showSubcategories(@PathVariable("categoryId") Long id,
                                    Model model) {


        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLogin(login);
        String role = Objects.requireNonNull(user.getRoles().stream().findFirst().orElse(null)).getName();

        List<Subcategory> subcategories = subcategoryService.prepareSubcategories(role, categoryService.getById(id).getSubcategories());

        model.addAttribute("subcategory", subcategories);
        model.addAttribute("categoryId", id);
        return "ShowSubcategoriesPage";
    }

    @GetMapping("/category/{categoryId}/subcategory/{subcategoryId}")
    public String showCards(@PathVariable("subcategoryId") Long subcategoryId,
                            @PathVariable("categoryId") Long categoryId,
                            Model model) {
        model.addAttribute("message", null);

        List<Card> cards = new ArrayList<>(subcategoryService.getById(subcategoryId).getCards());
        model.addAttribute("cards", cards);

        model.addAttribute("cardQuestion", cards.get(counterCards).getQuestion());
        model.addAttribute("firstAnswer", cards.get(counterCards).getFirstAnswer());
        model.addAttribute("secondAnswer", cards.get(counterCards).getSecondAnswer());
        model.addAttribute("thirdAnswer", cards.get(counterCards).getThirdAnswer());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("subcategoryId", subcategoryId);
        if (counterCards < cards.size()) {
            counterCards++;
        } else {
            model.addAttribute("endingMessage", "It's over! Well done.");
            return "/";
        }
        return "ShowCards";
    }

    @PostMapping("/category/{categoryId}/subcategory/{subcategoryId}")
    public String getAnswer(Model model,
                            @PathVariable("categoryId") Long categoryId,
                            @PathVariable("subcategoryId") Long subcategoryId,
                            @RequestParam("firstAnswer") String first
                          /*
                          @RequestParam("secondAnswer") String second,
                          @RequestParam("thirdAnswer") String third,
                          @RequestParam("fourthAnswer") String fourth
    */) {


        //, second, third, fourth
        Subcategory subcategory = subcategoryService.getById(subcategoryId);
        String[] checkboxParams = new String[]{first};
        //List<Card> cardFromDb = subcategory.getCards().stream().filter(s -> s.getQuestion().equals("Вилкой в глаз")).collect(Collectors.toList());
        //model.getAttribute("cardQuestion"
        List<Card> cardFromDb = List.of(subcategory.getCards().get(0));
        //Integer correctAnswerId = cardService.getIdFromPage(checkboxParams);
        Integer correctAnswerId = 1;
        if (Objects.equals(cardFromDb.get(0).getCorrectAnswerId(), correctAnswerId)) {
            //ХЗ, как написать)))))
            // идея в том, что если id с формы равен id правильного ответа, то правильный ответ подсветится зеленым, или около того, иначе - красным
            // иначе - нет
            model.addAttribute("message", "It's a correct answer!");
        } else {
            model.addAttribute("message", "It's uncorrected answer! Right one :" + cardFromDb.get(0).getCorrectAnswer());
        }

        return "redirect:/category/" + categoryId + "/subcategory/" + subcategoryId;
    }
}
