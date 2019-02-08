package com.netcreker.meetup.entity.card;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(4)
public class Card extends Entity {
  @Parameter(1020)
  private String nameSurname;
  @Parameter(1023)
  private String cardFullNumber;
  @Parameter(1022)
  private String lastFourNumbers;
  @Parameter(1021)
  private String dataOfExpire;
  @Parameter(1024)
  private String codeWord;
  @Reference(1025)
  private User user;

}
