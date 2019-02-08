package com.netcreker.meetup.controller.chat;

import com.netcreker.meetup.entity.chat.Message;
import com.netcreker.meetup.service.chat.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @GetMapping(value = "/messages/{chatId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ArrayList<Message> findMessages(Pageable pageable, @PathVariable("chatId") long chatId) {
    return messageService.findAllByChat(chatId);
  }

  /*@PostMapping(value = "/messages")
  @CrossOrigin(origins = "http://localhost:4200")
  public void sendReadReceipt(@PathVariable("chatId") long chatId,@PathVariable("userId") long userId) {
    messageService.sendReadReceipt(chatId,userId);
  }*/

  @PostMapping(value = "/messages")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?> sendReadReceipt(@RequestBody Message newMes) {
    messageService.sendReadReceipt(newMes);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

}
