package com.netcreker.meetup.controller.chat;

import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

  @Autowired
  private ChatService chatService;

  @GetMapping("/chat/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<Chat>>  getChats(@PathVariable long id) {
    List<Chat> chats = chatService.getChats(id);
    if(chats.isEmpty()){
      return new ResponseEntity<List<Chat>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Chat>>(chats, HttpStatus.OK);
  }

  @GetMapping("/friends/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<User>>  getFriends(@PathVariable long id) {
    List<User> friends = chatService.getFriends(id);
    if(friends.isEmpty()){
      return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<User>>(friends, HttpStatus.OK);
  }

  @PostMapping("/chat")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>addCard(@RequestBody Chat newChat) {
    chatService.addChat(newChat);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }
  /*
  @MessageMapping("/chat")
  public void handleMessage(Message message) {
    message.setTimestamp(new Date());
    messageService.save(message);
    template.convertAndSend("/channel/chat/" + message.getChannel(), message);
  }*/
}