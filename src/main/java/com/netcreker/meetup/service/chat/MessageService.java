package com.netcreker.meetup.service.chat;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.chat.Message;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private EntityManager em;

  public void save(Message message){
    //delete
    message.setSender(em.load(User.class,-1));
    em.save(message);
  }

  /*public void sendReadReceipt(Message message){

  }*/

  public List<Message> findAllByChat(long channelId){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(7).reference(1033, channelId);
    List<Message> result = em.filter(Message.class, query, false);

    return result.isEmpty() ? null : result;
    /*if(chatsList.size()<2) {
      Message mes = new Message();
      mes.setContent("hi");
      mes.setTimestamp(new Date());
      mes.setSender(new User());
      chatsList.add(mes);
      mes = new Message();
      mes.setContent("hey");
      mes.setTimestamp(new Date());
      mes.setSender(new User());
      chatsList.add(mes);
    }*/
  }
}
