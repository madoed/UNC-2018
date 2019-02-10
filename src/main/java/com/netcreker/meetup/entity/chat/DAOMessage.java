package com.netcreker.meetup.entity.chat;

import lombok.Data;

@Data
public class DAOMessage {

  private String content;

  private long chat;

  private String sender;
}
