package by.learningvoyage.controller;


import by.learningvoyage.model.Card;
import by.learningvoyage.model.Category;
import by.learningvoyage.model.Roles;
import by.learningvoyage.model.Subcategory;
import by.learningvoyage.service.CardService;
import by.learningvoyage.service.CategoryService;
import by.learningvoyage.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private CardService cardService;

    @GetMapping("/create/subcategory")
    public String showCreateSubcategoryPage() {
        return "ShowCreateSubcategoryPage";
    }


    @GetMapping("/display/subcategoryPicture")
    public ResponseEntity<byte[]> displaySubcategoryPicture(@RequestParam("id") long id) throws SQLException {
        Subcategory subcategory = subcategoryService.getById(id);
        byte[] categoryPictureBytes = subcategory.getSubcategoryPicture().getBytes(1, (int) subcategory.getSubcategoryPicture().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(categoryPictureBytes);
    }

    @PostMapping("/create/subcategory")
    public String createSubcategory(@RequestParam("subcategoryName") String subcategoryName,
                                    @RequestParam("subcategoryDescription") String subcategoryDescription,
                                    @RequestParam("subcategoryPicture") MultipartFile subcategoryPicture) throws SQLException, IOException {

        Subcategory subcategory = new Subcategory();

        subcategory.setSubcategoryName(subcategoryName);
        subcategory.setSubcategoryDescription(subcategoryDescription);
        subcategory.setSubcategoryPicture(subcategoryService.castToBlobs(subcategoryPicture));
        subcategory.setAccessLevel(Roles.DEFAULT_USER);

        subcategoryService.create(subcategory);

        return "redirect:/constructor";

    }

    @GetMapping("subcategory/edit")
    public String showSubcategoryMenu(@RequestParam("id") long id,
                                      Model model){
        List<Subcategory> subcategories = subcategoryService.getAllSubcategories();

        model.addAttribute("subcategories", subcategories);
        model.addAttribute("subcategoryName", subcategoryService.getById(id).getSubcategoryName());
        model.addAttribute("id", id);

        List<Card> cards = cardService.getAllCards();
        model.addAttribute("cards", cards);
        return "ShowEditSubcategoryPage";
    }

    @PostMapping("/subcategory/edit/{id}")
    public String addCards(@PathVariable("id") long id,
                                   @RequestParam("cardCheckbox") List<String> subcategoriesId) {
        List<Long> cardIds = subcategoriesId.stream().map(Long::parseLong).toList();
        List<Card> cards = new ArrayList<>();

        for (Long cardId : cardIds) {
            cards.add(cardService.findById(cardId));
        }

        Subcategory subcategory = subcategoryService.getById(id);

        subcategory.setCards(cards);

        subcategoryService.save(subcategory);

        return "redirect:/constructor";
    }

}

