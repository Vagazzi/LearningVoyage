package by.learningvoyage.controller;


import by.learningvoyage.model.Category;
import by.learningvoyage.model.Roles;
import by.learningvoyage.model.Subcategory;
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
    private CategoryService categoryService;

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("/edit/category")
    public String showEditCategoryPage(@RequestParam("id") long id,
                                       Model model) {
        model.addAttribute("category", categoryService.getById(id));
        model.addAttribute("categoryName", categoryService.getById(id).getCategoryName());
        model.addAttribute("subcategories", subcategoryService.getAllCategories());
        model.addAttribute("id", id);
        return "editCategory";
    }

    @PostMapping("/edit/category/")
    public String addSubcategories(@RequestParam(value = "id") long categoryId,
                                   @RequestParam("subcategoryCheckbox") List<String> subcategoriesId) {
        List<Long> subcategoryIds = subcategoriesId.stream().map(Long::parseLong).collect(Collectors.toList());
        List<Subcategory> subcategories = new ArrayList<>();

        for(int i = 0; i < subcategoryIds.size(); i++){
            subcategories.add(subcategoryService.getById(i));
        }

        Category category = categoryService.getById(categoryId);

        category.setSubcategories(subcategories);

        System.out.println(subcategoryService.getById(subcategoryIds.get(0)).getSubcategoryName());
        System.out.println(subcategoryService.getById(subcategoryIds.get(1)).getSubcategoryName());


        return "redirect:/constructor";
    }

    @GetMapping("/create/subcategory")
    public String showCreateSubcategoryPage() {
        return "createSubcategory";
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
        subcategory.setAccessLevel(String.valueOf(Roles.DEFAULT_USER));

        subcategoryService.create(subcategory);

        return "redirect:/constructor";

    }
}

