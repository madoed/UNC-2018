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
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(6)
public class Chat extends Entity {
  @Parameter(1029)
  private String chatName;
  @Reference(1030)
  private List<User> subscribers;
  @Parameter(1088)
  private String chatType;//dialogue, chat, meeting
  @Parameter(1089)
  private String lastMessage;
  @Reference(1090)
  private User lastSender;
  @Parameter(1091)
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastUpdate;
  @Parameter(1036)
  private String avatarUrl;
}
