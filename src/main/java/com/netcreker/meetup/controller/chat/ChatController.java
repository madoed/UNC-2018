package com.netcreker.meetup.controller.chat;

import com.netcreker.meetup.entity.chat.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {
  private final SimpMessagingTemplate template;

    @Autowired
    public ChatController(final SimpMessagingTemplate template) {
      this.template = template;
    }

    @MessageMapping("/send/message")
    public void onReceiveMessage(@Nullable final String message) {
      this.template.convertAndSend("/chat",
              LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ": " + message);
    }
  }