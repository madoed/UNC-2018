package com.netcreker.meetup.controller.chat;

import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.chat.DAOChat;
import com.netcreker.meetup.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

  @Autowired
  private ChatService chatService;

  @GetMapping("/chat/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ArrayList<DAOChat> getChats(@PathVariable long id) {
    return chatService.getChats(id);
  }

  /*
  @MessageMapping("/chat")
  public void handleMessage(Message message) {
    message.setTimestamp(new Date());
    messageService.save(message);
    template.convertAndSend("/channel/chat/" + message.getChannel(), message);
  }*/
}