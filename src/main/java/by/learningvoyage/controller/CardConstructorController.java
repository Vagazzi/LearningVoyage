package by.learningvoyage.controller;

import by.learningvoyage.model.Category;
import by.learningvoyage.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
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
                               @RequestParam("categoryPicture") MultipartFile categoryPicture,
                               Model model) throws SQLException, IOException {

        Category category = new Category();

        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        category.setCategoryName(categoryName);
        category.setCategoryDescription(categoryDescription);
        category.setCategoryPicture(categoryService.castToBlobs(categoryPicture));

        if(categoryService.getAllCategories().stream()
                .filter(s->s.getCategoryName().equals(categoryName))
                .toList()
                .get(0)
                .equals(category)) {
            model.addAttribute("errorMessage", "This category exists, try again");
            return "redirect:/constructor/create";
        }

        categoryService.create(category);
        log.info("Category was created with this name: {} by user {}", categoryName, login);

        return "redirect:/constructor";

    }
}
