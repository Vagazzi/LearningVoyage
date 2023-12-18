package by.learningvoyage.service;

import by.learningvoyage.model.Subcategory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
public interface SubcategoryService {

    Subcategory create(Subcategory subcategory);

    Blob castToBlobs(MultipartFile object) throws SQLException, IOException;

    List<Subcategory> getAllSubcategories();
    Subcategory getById(long id);

    List<Subcategory> prepareSubcategories(String role, List<Subcategory> subcategories);

    Subcategory save(Subcategory subcategory);
}
