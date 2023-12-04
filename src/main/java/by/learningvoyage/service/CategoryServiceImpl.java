package by.learningvoyage.service;

import by.learningvoyage.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import by.learningvoyage.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(long id) {
        return  categoryRepository.findById(id).get();
    }
}
