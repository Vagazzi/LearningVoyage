package by.learningvoyage.service;


import by.learningvoyage.model.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    Category create(Category category);

    Category getById(long id);
}
