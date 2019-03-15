package com.netcreker.meetup.service.chat;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.chat.Message;
import com.netcreker.meetup.entity.notification.Reserve;
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
    message.setTimestamp(new Date());
    message.setSender(em.load(User.class,message.getSender().getId()));
    em.save(message);
    saveToReserve(message);
  }

  private void saveToReserve(Message message) {
    Chat chat = em.load(Chat.class, message.getFrom_chat().getId());
    Reserve reserve;
    for (User sub:chat.getSubscribers()) {
      if(sub.getId()!=message.getSender().getId()) {
        reserve = new Reserve();
        reserve.setReserveChat(chat);
        reserve.setReserveUser(sub);
        reserve.setReserveMessage(message);
        em.save(reserve);
      }
    }
  }

  public List<Message> findOldByChat(long channelId, long userId){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(7).reference(1033, channelId);
    List<Message> allMes = em.filter(Message.class, query, false);
    if (allMes.isEmpty())
      return allMes;

    allMes.sort((o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()));
    query = ObjectQuery.newInstance().objectTypeId(18).reference(1085, channelId)
            .objectTypeId(18).reference(1086, userId);
    List<Chat> newMes = em.filter(Chat.class, query, false);
    if (newMes.isEmpty())
      return allMes;

    for (Message message:allMes) {
      if(newMes.contains(message)) {
        allMes.remove(message);
      }
    }
    return allMes;
  }

  public List<Message> findNewByChat(long channelId, long userId){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(7).reference(1033, channelId);
    List<Message> allMes = em.filter(Message.class, query, false);
    if (allMes.isEmpty())
      return allMes;

    List<Message> result = new ArrayList<>();

    allMes.sort((o1, o2) -> o1.getTimestamp().compareTo(o2.getTimestamp()));
    query = ObjectQuery.newInstance().objectTypeId(18).reference(1085, channelId)
            .objectTypeId(18).reference(1086, userId);
    List<Message> newMes = em.filter(Message.class, query, false);
    if (newMes.isEmpty())
      return newMes;

    for (Message message:allMes) {
      if(newMes.contains(message)) {
        result.add(message);
      }
    }
    return result;
  }

  private void cleanReserve(long channelId, long userId) {
    ObjectQuery query =  ObjectQuery.newInstance()
            .objectTypeId(18).reference(1085, channelId)
            .objectTypeId(18).reference(1086, userId);
    List<Reserve> chats = em.filter(Reserve.class, query, false);
    for (Reserve chat:chats) {
      em.delete(chat.getId());
    }
  }
}
