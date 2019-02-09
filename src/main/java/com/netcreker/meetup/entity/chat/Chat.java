package com.netcreker.meetup.entity.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;

import java.util.List;

@Data
//@EqualsAndHashCode(callSuper = true)
@ObjectType(7)
public class Chat extends Entity {
  @Parameter(1034)
  private String chatName;

  @Reference(1035)
  @JsonIgnore
  private List<User> friends;
}
