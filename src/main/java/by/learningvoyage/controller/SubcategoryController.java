package by.learningvoyage.controller;


import by.learningvoyage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubcategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/edit/category")
    public String editCandidate(@RequestParam("id") long id,
                                Model model){
        model.addAttribute("categoryName", categoryService.getById(id).getName());
        return "editCategory";
    }
}
