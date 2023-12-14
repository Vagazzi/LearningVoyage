package by.learningvoyage.controller;

import by.learningvoyage.service.CategoryService;
import by.learningvoyage.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CardViewController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{id}")
    public String showSubcategories(@PathVariable("id") Long id,
                                    Model model){

        model.addAttribute("subcategory", categoryService.getById(id).getSubcategories());
        return "ShowSubcategoriesPage";
    }
}
