package by.learningvoyage.service;

import by.learningvoyage.model.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import by.learningvoyage.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;


@Service
public class SubcategoryServiceImpl implements SubcategoryService{

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public Subcategory create(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    @Override
    public Subcategory getById(long id) {
        return subcategoryRepository.findById(id).get();
    }

}
