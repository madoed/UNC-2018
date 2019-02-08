package com.netcreker.meetup.service.chat;

import com.netcreker.meetup.entity.chat.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Service
public class MessageService {

  public ArrayList<Message>  chatsList=new ArrayList<Message> ();

  public void save(Message message){

  }

  public void sendReadReceipt(Message message){
    chatsList.add(message);
  }

  public ArrayList<Message> findAllByChat(long channelId){

    if(chatsList.size()<2) {
      Message mes = new Message();
      mes.setContent("hi");
      mes.setTimestamp(new Date());
      mes.setSender("Nina");
      chatsList.add(mes);
      mes = new Message();
      mes.setContent("hey");
      mes.setTimestamp(new Date());
      mes.setSender("Vika");
      chatsList.add(mes);
    }
    return chatsList;
  }
}
