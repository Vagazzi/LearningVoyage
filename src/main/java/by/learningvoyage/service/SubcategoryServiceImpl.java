package by.learningvoyage.service;

import by.learningvoyage.model.Category;
import by.learningvoyage.model.Roles;
import by.learningvoyage.model.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import by.learningvoyage.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.management.relation.Role;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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
    public List<Subcategory> prepareSubcategories(String role, List<Subcategory> allSubcategories) {
        if(role.equals(Roles.ADMIN) || role.equals(Roles.PRIVILEGED_USER)){
            return allSubcategories;
        }

        else return allSubcategories.stream()
                    .filter(s-> Objects.equals(s.getAccessLevel(), Roles.DEFAULT_USER))
                    .collect(Collectors.toList());
    }

    @Override
    public Subcategory save(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    @Override
    public List<Subcategory> getAllSubcategories() {
        return  (List<Subcategory>) subcategoryRepository.findAll();
    }
}
