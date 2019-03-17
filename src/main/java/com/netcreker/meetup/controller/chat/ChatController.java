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

  @GetMapping("/chats-old/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<Chat>> getOldChats(@PathVariable long id ) {
    List<Chat> chats = chatService.getOldChats(id);
    if(chats.isEmpty()){
      return new ResponseEntity<List<Chat>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Chat>>(chats, HttpStatus.OK);
  }

  @GetMapping("/chats-new/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<Chat>> getNewChats(@PathVariable long id ) {
    List<Chat> chats = chatService.getNewChats(id);
    if(chats.isEmpty()){
      return new ResponseEntity<List<Chat>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Chat>>(chats, HttpStatus.OK);
  }

  @GetMapping("/chat/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>  getChat(@PathVariable long id) {
    Chat chat = chatService.getChat(id);
    if(chat==null){
      return new ResponseEntity<Chat>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Chat>(chat, HttpStatus.OK);
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
  public ResponseEntity<?>addChat(@RequestBody Chat newChat) {
    chatService.addChat(newChat);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @GetMapping("/dialogue/{user1}/{user2}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Chat>getDialogue(@PathVariable long user1, @PathVariable long user2) {
    Chat chat = chatService.getDialogue(user1, user2);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<Chat>(chat, HttpStatus.CREATED);
  }

  /*
  @MessageMapping("/chat")
  public void handleMessage(Message message) {
    message.setTimestamp(new Date());
    messageService.save(message);
    template.convertAndSend("/channel/chat/" + message.getChannel(), message);
  }*/
}