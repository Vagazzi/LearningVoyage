package by.learningvoyage.repository;

import by.learningvoyage.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
