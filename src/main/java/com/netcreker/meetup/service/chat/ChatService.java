package com.netcreker.meetup.service.chat;

import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChatService {

  public ArrayList<Chat> getChats(Long id){
    //по id найти в refs ссылку с attr_id которые имеют тип card_info
    //ArrayList<Card> cardsList = dbmanager.getValue(id, );
    //return mapper.mapAsList
    ArrayList<Chat> chatsList=new ArrayList<Chat>();
    Chat chat=new Chat();
    chat.setId(1);
    chat.setChatName("first chat");
    User user = new User();
    user.setId(id);
    user.setFirstName("Nastya");
    ArrayList<User> usersList=new ArrayList<User>();
    usersList.add(user);
    chatsList.add(chat);
    return chatsList;
  }
}
