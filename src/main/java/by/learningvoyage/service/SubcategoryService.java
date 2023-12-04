package by.learningvoyage.service;

import by.learningvoyage.model.Subcategory;
import org.springframework.stereotype.Service;

@Service
public interface SubcategoryService {

    Subcategory create(Subcategory subcategory);

    Subcategory getById(long id);
}
