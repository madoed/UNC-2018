package com.netcreker.meetup.service.card;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.card.Card;
import com.netcreker.meetup.entity.card.EncryptedCard;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.entity.meeting.Participant;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

  @Autowired
  private EntityManager em;

  public List<Card> getAllCards(Long id){
    //change -1 on id
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(4).reference(1024, -1);
    List<Card> cards = em.filter(Card.class, query, false);

    return cards;
  }

  public Card getCard(Long id){
    return em.load(Card.class, id);
  }

  public void addCard(Card newCard){
    EncryptedCard encryptedCard = new EncryptedCard();
    encryptedCard.setCardFullNumber(newCard.getLastFourNumbers());
    encryptedCard.setEncryptedCard(newCard);
    EncryptCard(encryptedCard);
    newCard.setLastFourNumbers(newCard.getLastFourNumbers().substring(12));
    em.save(newCard);
    em.save(encryptedCard);
  }

  private void EncryptCard(EncryptedCard card){
    final String password = "meetup";
    final String salt = KeyGenerators.string().generateKey();
    card.setCodeWord(salt);
    TextEncryptor encryptor = Encryptors.text(password, salt);
    String encryptedText = encryptor.encrypt(card.getCardFullNumber());
  }

  private String DecryptCard(EncryptedCard card) {
    TextEncryptor decryptor = Encryptors.text("meetup", card.getCodeWord());
    String decryptedText = decryptor.decrypt(card.getCardFullNumber());
    return decryptedText;
  }
}

