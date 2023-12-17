package by.learningvoyage.controller;


import by.learningvoyage.model.Category;
import by.learningvoyage.model.Subcategory;
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
import java.util.stream.Collectors;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("/categories")
    public String showCategories(Model model) {

        List<Category> categories = categoryService.getAllCategories();
        List<List<Subcategory>> subcategories = new ArrayList<>();
        model.addAttribute("categories", categories);
        for (Category category : categories) {
            subcategories.add(category.getSubcategories());
        }
        model.addAttribute("subcategories", subcategories);

        return "ShowListOfCategories";
    }

    @GetMapping("/edit/category")
    public String showEditCategoryPage(@RequestParam("id") long id,
                                       Model model) {
        model.addAttribute("category", categoryService.getById(id));
        model.addAttribute("categoryName", categoryService.getById(id).getCategoryName());
        model.addAttribute("subcategories", subcategoryService.getAllSubcategories());
        model.addAttribute("id", id);
        return "ShowEditCategoryPage";
    }

    @PostMapping("/edit/category/{id}")
    public String addSubcategories(@PathVariable("id") long id,
                                   @RequestParam("subcategoryCheckbox") List<String> subcategoriesId) {
        List<Long> subcategoryIds = subcategoriesId.stream().map(Long::parseLong).toList();
        List<Subcategory> subcategories = new ArrayList<>();

        for (Long subcategoryId : subcategoryIds) {
            subcategories.add(subcategoryService.getById(subcategoryId));
        }

        Category category = categoryService.getById(id);

        category.setSubcategories(subcategories);

        categoryService.save(category);

        return "redirect:/constructor";
    }
}
