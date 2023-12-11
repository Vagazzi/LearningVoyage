package by.learningvoyage.service;

import by.learningvoyage.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import by.learningvoyage.repository.CardRepository;
import org.springframework.stereotype.Service;


@Service
public class CardServiceImpl implements CardService{

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card create(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findById(long id) {
        return cardRepository.findById(id).get();
    }
}
