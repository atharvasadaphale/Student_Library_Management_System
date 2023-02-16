package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public Boolean isValidCard(int cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        if(card == null){
            return false;
        }
        return true;
    }
}
