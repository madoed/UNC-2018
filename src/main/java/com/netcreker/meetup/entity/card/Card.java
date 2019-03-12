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
  @Parameter(1019)
  private String nameSurname;
  @Parameter(1021)
  private String lastFourNumbers;
  @Parameter(1022)
  private String dataOfExpire;
  @Reference(1024)
  private User owner;

}
