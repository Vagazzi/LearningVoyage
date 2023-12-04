package by.learningvoyage.service;


import by.learningvoyage.model.Card;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

    Card create(Card card);

    Card findById(long id);
}
