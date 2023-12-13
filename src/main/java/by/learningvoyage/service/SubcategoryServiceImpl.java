package by.learningvoyage.service;

import by.learningvoyage.model.Category;
import by.learningvoyage.model.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import by.learningvoyage.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;


@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public Subcategory create(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    @Override
    public Blob castToBlobs(MultipartFile object) throws SQLException, IOException {

        byte[] photoBytes = object.getBytes();
        return new javax.sql.rowset.serial.SerialBlob(photoBytes);

    }

    @Override
    public Subcategory getById(long id) {
        return subcategoryRepository.findById(id).get();
    }

    @Override
    public List<Subcategory> getAllCategories() {
        return  (List<Subcategory>) subcategoryRepository.findAll();
    }
}
