package by.learningvoyage.service;


import by.learningvoyage.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {

    Card create(Card card);

    Integer getIdFromPage(String[] arrays);

    String getCorrectAnswer(Integer id, Card card);

    List<String> getAnswers(String[] arrays);

    List<Card> getAllCards();

    Card findById(long id);
}
