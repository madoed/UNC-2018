package com.netcreker.meetup.entity.chat;

import lombok.Data;

@Data
public class DTOMessage {

  private String content;

  private long chat;

  private long sender;

}
