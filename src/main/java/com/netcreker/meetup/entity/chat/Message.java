package com.netcreker.meetup.entity.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(7)
public class Message extends Entity {

  @Parameter(1031)
  private String content;

  @Parameter(1032)
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  @Reference(1033)
  private Chat from_chat;

  @Reference(1034)
  private User sender;

}
