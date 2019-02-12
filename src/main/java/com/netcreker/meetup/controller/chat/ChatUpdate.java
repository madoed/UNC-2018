package com.netcreker.meetup.controller.chat;

import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.chat.Message;
import com.netcreker.meetup.service.chat.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ChatUpdate {

  /*
  @Autowired
  private SimpMessagingTemplate template;

  @MessageMapping("/messages")
  @SendTo("/channel1")
  @CrossOrigin(origins = "http://localhost:4200")
  public Message handleMessage(@RequestBody Message newMes) {
    //message.setTimestamp(new Date());
    //messageService.save(message);
    //template.convertAndSend("/channel" , message);
    //this.template.convertAndSend("/channel1", message);
    return newMes;
  }*/

  @Autowired
  private MessageService messageService;

  @Autowired
  private SimpMessagingTemplate template;

    @MessageMapping("/messages")
    public void handleMessage(Message message) {
      //message.setTimestamp(new Date());
      messageService.save(message);
      //String id = chat.getChatName();
      template.convertAndSend("/channel/"+ message.getFrom_chat().getId(), message);
    }
}
