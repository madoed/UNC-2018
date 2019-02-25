package com.netcreker.meetup.entity.card;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(15)
public class EncryptedCard extends Entity {
  @Parameter(1020)
  private String cardFullNumber;
  @Parameter(1023)
  private String codeWord;
  @Reference(1067)
  private Card encryptedCard;
}
