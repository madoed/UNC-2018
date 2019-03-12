package com.netcreker.meetup.service.card;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.card.Card;
import com.netcreker.meetup.entity.card.EncryptedCard;
import com.netcreker.meetup.entity.check.Bill;
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
            .objectTypeId(4).reference(1024, id);
    List<Card> cards = em.filter(Card.class, query, false);

    return cards;
  }

  public Card getCard(Long id){
    return em.load(Card.class, id);
  }

  public Card addCard(Card newCard){
    EncryptedCard encryptedCard = new EncryptedCard();
    encryptedCard.setCardFullNumber(newCard.getLastFourNumbers());
    EncryptCard(encryptedCard);
    newCard.setLastFourNumbers(newCard.getLastFourNumbers().substring(12));
    newCard.setName(newCard.getOwner().getName());
    em.save(newCard);
    encryptedCard.setEncryptedCard(newCard);
    encryptedCard.setName(newCard.getOwner().getName());
    em.save(encryptedCard);
    return newCard;
  }

  private void EncryptCard(EncryptedCard card){
    final String password = "meetup";
    final String salt = KeyGenerators.string().generateKey();
    card.setCodeWord(salt);
    TextEncryptor encryptor = Encryptors.text(password, salt);
    String encryptedText = encryptor.encrypt(card.getCardFullNumber());
    card.setCardFullNumber(encryptedText);
  }

  private String DecryptCard(EncryptedCard card) {
    TextEncryptor decryptor = Encryptors.text("meetup", card.getCodeWord());
    String decryptedText = decryptor.decrypt(card.getCardFullNumber());
    return decryptedText;
  }

  public void setBillCard(long cardId, long meetingId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(11).reference(1050, meetingId);
    Bill bill = em.filter(Bill.class, query, false).get(0);
    Card card = em.load(Card.class, cardId);
    bill.setMainCard(card);
    em.save(bill);
  }
}

