package by.learningvoyage.service;

import by.learningvoyage.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import by.learningvoyage.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public List<String> getAnswers(String[] arrays) {
        List<String> resultStrings = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            arrays[i] = arrays[i].replace(" id", "");
            Pattern p = Pattern.compile("[a-zA-Z]+\\.?");
            Matcher m = p.matcher(arrays[i]);
            while (m.find()) {
                resultStrings.add(m.group());
            }
        }

        return resultStrings;
    }

    @Override
    public Long getIdFromPage(String[] arrays) {

        //What a hell you are doing here?!

        long value = 0L;

        for (int i = 0; i < 4; i++) {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(arrays[i]);
            while (m.find()) {
                value = Long.parseLong(m.group());
            }
        }

        return value;
    }

    @Override
    public Card findById(long id) {
        return cardRepository.findById(id).get();
    }
}

