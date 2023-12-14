package by.learningvoyage.service;

import by.learningvoyage.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import by.learningvoyage.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return  (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(long id) {
        return  categoryRepository.findById(id).get();
    }

    @Override
    public Blob castToBlobs(MultipartFile object) throws SQLException, IOException {

        byte[] photoBytes = object.getBytes();
        return new javax.sql.rowset.serial.SerialBlob(photoBytes);
    }
}
