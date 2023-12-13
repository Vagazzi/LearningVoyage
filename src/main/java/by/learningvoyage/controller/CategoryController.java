package by.learningvoyage.controller;


import by.learningvoyage.model.Category;
import by.learningvoyage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String showCategories(Model model){

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "categoriesList";
    }
}