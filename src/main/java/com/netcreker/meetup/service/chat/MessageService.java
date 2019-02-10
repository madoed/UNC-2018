package com.netcreker.meetup.service.chat;

import com.netcreker.meetup.entity.chat.DAOMessage;
import com.netcreker.meetup.entity.chat.DTOMessage;
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

  public ArrayList<DAOMessage>  chatsList=new ArrayList<DAOMessage> ();

  public void save(DTOMessage message){
    DAOMessage mes = new DAOMessage();
    mes.setContent(message.getContent());
    //mes.setSender(message.getSender());
    mes.setSender("ktoto");
    chatsList.add(mes);
  }

  public void sendReadReceipt(DTOMessage message){
    DAOMessage mes = new DAOMessage();
    mes.setContent(message.getContent());
    //mes.setSender(message.getSender());
    mes.setSender("ktoto");
    chatsList.add(mes);
  }

  public ArrayList<DAOMessage> findAllByChat(long channelId){

    if(chatsList.size()<2) {
      DAOMessage mes = new DAOMessage();
      mes.setContent("hi");
      mes.setSender("Nina");
      chatsList.add(mes);
      mes = new DAOMessage();
      mes.setContent("hey");
      mes.setSender("Vika");
      chatsList.add(mes);
    }
    return chatsList;
  }
}
