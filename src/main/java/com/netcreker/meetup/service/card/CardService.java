package com.netcreker.meetup.service.card;

import com.netcreker.meetup.entity.card.Card;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CardService {
  //DatabaseManagerImpl dbmanager;

  public ArrayList<Card> getCard(Long id){
    //по id найти в refs ссылку с attr_id которые имеют тип card_info
    //ArrayList<Card> cardsList = dbmanager.getValue(id, );
    //return mapper.mapAsList
    ArrayList<Card> cardsList=new ArrayList<Card>();
    Card card=new Card();
    card.setId(id);
    card.setDataOfExpire("06/45");
    card.setLastFourNumbers("456"+id);
    card.setNameSurname("adf rt");
    cardsList.add(card);
    return cardsList;
  }

  public ArrayList<Card> getAllCards(){
    //по id найти в refs ссылку с attr_id которые имеют тип card_info
    //ArrayList<Card> cardsList = dbmanager.getValue(id, );
    //return mapper.mapAsList
    ArrayList<Card> cardsList=new ArrayList<Card>();
    Card card=new Card();
    card.setId((long)1);
    card.setDataOfExpire("45/45");
    card.setLastFourNumbers("6554");
    card.setNameSurname("ana gus");
    cardsList.add(card);
    card=new Card();
    card.setId((long)2);
    card.setDataOfExpire("06/45");
    card.setLastFourNumbers("4565");
    card.setNameSurname("adf rt");
    cardsList.add(card);
    return cardsList;
  }

  public void addCard(Card newCard){
    //Card repository, save method
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

