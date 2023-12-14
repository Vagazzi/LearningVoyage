package by.learningvoyage.service;

import by.learningvoyage.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import by.learningvoyage.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card create(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getAllCards() {
        return  (List<Card>) cardRepository.findAll();
    }

    @Override
    public String getCorrectAnswer(Integer correctAnswerId, Card card) {
        switch (correctAnswerId){
            case 1 : return card.getFirstAnswer();
            case 2 : return card.getSecondAnswer();
            case 3 : return card.getThirdAnswer();
            case 4 : return card.getFourthAnswer();
        }
        return null;
    }


    //FIXME дайте мне костыль и бутылку рома, йо-хо-хо!
    @Override
    public List<String> getAnswers(String[] arrays) {
        List<String> resultStrings = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            arrays[i] = arrays[i].replace(",1", "");
            arrays[i] = arrays[i].replace(",2", "");
            arrays[i] = arrays[i].replace(",3", "");
            arrays[i] = arrays[i].replace(",4", "");
            resultStrings.add(arrays[i]);
        }

        return resultStrings;
    }

    @Override
    public Integer getIdFromPage(String[] arrays) {

        //What a hell you are doing here?!

        int value = 0;

        for (int i = 0; i < 4; i++) {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(arrays[i]);
            while (m.find()) {
                value = Integer.parseInt(m.group());
            }
        }

        return value;
    }

    @Override
    public Card findById(long id) {
        return cardRepository.findById(id).get();
    }
}

