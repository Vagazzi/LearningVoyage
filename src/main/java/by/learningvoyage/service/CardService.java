package by.learningvoyage.service;


import by.learningvoyage.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {

    Card create(Card card);

    Long getIdFromPage(String[] arrays);

    List<String> getAnswers(String[] arrays);

    Card findById(long id);
}
