package by.learningvoyage.service;


import by.learningvoyage.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
public interface CategoryService {

    Category create(Category category);

    List<Category> getAllCategories();

    Category save(Category category);



    Blob castToBlobs(MultipartFile object) throws SQLException, IOException;

    Category getById(long id);
}
