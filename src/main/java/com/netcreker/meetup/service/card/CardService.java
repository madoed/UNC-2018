package com.netcreker.meetup.service.card;

import com.netcreker.meetup.databasemanager.DatabaseManagerImpl;
import com.netcreker.meetup.entity.card.Card;

import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.ArrayList;

@Service
public class CardService {
  DatabaseManagerImpl dbmanager;

  public ArrayList<Card> getMyCards(Long id){
    //по id найти в refs ссылку с attr_id которые имеют тип card_info
    //ArrayList<Card> cardsList = dbmanager.getValue(id, );
    //return mapper.mapAsList
    ArrayList<Card> cardsList=new ArrayList<Card>();
    cardsList.add(new Card(id, "ana gus", "24534542353","45/45"));
    return cardsList;
  }

  public Card addCard(Long id,Card newCard){
    newCard
    //Card repository, save method
    return newCard;
  }

  private String EncryptCard(String number){
    final String password = "meetup";
    final String salt = KeyGenerators.string().generateKey();
    TextEncryptor encryptor = Encryptors.text(password, salt);

    String textToEncrypt = number;
    String encryptedText = encryptor.encrypt(textToEncrypt);
    return encryptedText;
  }

  private String DecryptCard(String encryptedText,String password,String salt) {
    TextEncryptor decryptor = Encryptors.text("meetup", salt);
    String decryptedText = decryptor.decrypt(encryptedText);
    return decryptedText;
  }
}
