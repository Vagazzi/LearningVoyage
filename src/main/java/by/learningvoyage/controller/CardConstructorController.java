package by.learningvoyage.controller;

import by.learningvoyage.model.Category;
import by.learningvoyage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class CardConstructorController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/constructor")
    public String showCardConstructor(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "ShowAvailableForEditCategories";
    }

    @GetMapping("/constructor/create")
    public String showCreateMenu(){
        return "ShowCreateCategoryPage";
    }

    @GetMapping("/display/categoryPicture")
    public ResponseEntity<byte[]> displayCategoryPicture(@RequestParam("id") long id) throws SQLException {
        Category category = categoryService.getById(id);
        byte[] categoryPictureBytes = category.getCategoryPicture().getBytes(1, (int) category.getCategoryPicture().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(categoryPictureBytes);
    }


    @PostMapping("/constructor/create")
    public String saveCategory(@RequestParam("categoryName") String categoryName,
                               @RequestParam("categoryDescription") String categoryDescription,
                               @RequestParam("categoryPicture") MultipartFile categoryPicture) throws SQLException, IOException {

        Category category = new Category();

        category.setCategoryName(categoryName);
        category.setCategoryDescription(categoryDescription);
        category.setCategoryPicture(categoryService.castToBlobs(categoryPicture));

        categoryService.create(category);

        return "redirect:/constructor";

    }
}
