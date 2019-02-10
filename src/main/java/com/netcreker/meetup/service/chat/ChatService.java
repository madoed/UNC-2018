package com.netcreker.meetup.service.chat;

import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.chat.DAOChat;
import com.netcreker.meetup.entity.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChatService {

  public ArrayList<DAOChat> getChats(Long id){
    //по id найти в refs ссылку с attr_id которые имеют тип card_info
    //ArrayList<Card> cardsList = dbmanager.getValue(id, );
    //return mapper.mapAsList
    ArrayList<DAOChat> chatsList=new ArrayList<DAOChat>();
    DAOChat chat=new DAOChat();
    chat.setId(1);
    chat.setChatName("first chat");
    chatsList.add(chat);

    chat=new DAOChat();
    chat.setId(2);
    chat.setChatName("second chat");
    chatsList.add(chat);

    return chatsList;
  }
}
