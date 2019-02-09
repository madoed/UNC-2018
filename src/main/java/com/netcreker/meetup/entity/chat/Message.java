package com.netcreker.meetup.entity.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
//@EqualsAndHashCode(callSuper = true)
@ObjectType(6)
public class Message extends Entity {

  @Parameter(1030)
  private String content;

  @Parameter(1031)
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  @Reference(1032)
  @JsonIgnore
  private Chat chat;

  @Parameter(1033)
  private String sender;

}
