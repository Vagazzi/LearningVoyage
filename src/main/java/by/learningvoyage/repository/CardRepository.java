package by.learningvoyage.repository;

import by.learningvoyage.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
}
