package com.netcreker.meetup.entity.card;

import lombok.Data;

@Data
public class Card {
  private Long id;
  private String nameSurname;
  private String cardFullNumber;
  private String lastFourNumbers;
  private String dataOfExpire;
  private String codeWord;

  public Card(Long id, String nameSurname, String cardFullNumber,String dataOfExpire) {
    this.id = id;
    this.nameSurname=nameSurname;
    this.cardFullNumber=cardFullNumber;
    this.dataOfExpire=dataOfExpire;
  }

}
