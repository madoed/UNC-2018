package com.netcreker.meetup.entity.notification;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.chat.Message;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(18)
public class Reserve extends Entity {
  @Reference(1085)
  private Chat reserveChat;
  @Reference(1086)
  private User reserveUser;
  @Reference(1087)
  private Message reserveMessage;
}
