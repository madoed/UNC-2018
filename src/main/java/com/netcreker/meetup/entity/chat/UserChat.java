package com.netcreker.meetup.entity.chat;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(9)
public class UserChat extends Entity {
  @Reference(1034)
  private Chat chat;

  @Reference(1035)
  private User subscriber;
}
