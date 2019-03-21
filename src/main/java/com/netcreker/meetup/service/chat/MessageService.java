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

  public List<User> save(Message message){
    //delete
    message.setName("mes");
    message.setTimestamp(new Date());
    message.setSender(em.load(User.class,message.getSender().getId()));
    em.save(message);
    Chat chat = em.load(Chat.class, message.getFrom_chat().getId());
    chat.setLastUpdate(message.getTimestamp());
    chat.setLastMessage(message.getContent());
    chat.setLastSender(message.getSender());
    em.save(chat);
    message.setFrom_chat(chat);
    message.getSender().setFriends(null);
    return saveToReserve(message);
  }

  private List<User> saveToReserve(Message message) {
    Chat chat = em.load(Chat.class, message.getFrom_chat().getId());
    Reserve reserve;
    for (User sub:chat.getSubscribers()) {
      if(sub.getId()!=message.getSender().getId()) {
        reserve = new Reserve();
        reserve.setReserveChat(chat);
        reserve.setReserveUser(sub);
        reserve.setReserveMessage(message);
        reserve.setName("new message from " + message.getSender().getName());
        em.save(reserve);
      }
    }
    return chat.getSubscribers();
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
    List<Reserve> newMes = em.filter(Reserve.class, query, false);
    if (newMes.isEmpty())
      return allMes;

    List<Message> result = new ArrayList<>(allMes);

    for (Message message:allMes) {
      for (Reserve note:newMes) {
        if (note.getReserveMessage().getId()==message.getId()) {
          result.remove(message);
        }
      }
    }

    return result;
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
    List<Reserve> newMes = em.filter(Reserve.class, query, false);
    if (newMes.isEmpty())
      return new ArrayList<Message>();

    for (Message message:allMes) {
      for (Reserve note:newMes) {
        if (note.getReserveMessage().getId()==message.getId()) {
          result.add(message);
          break;
        }
      }
    }
    cleanReserve(channelId, userId);
    return result;
  }

  public void cleanReserve(long channelId, long userId) {
    ObjectQuery query =  ObjectQuery.newInstance()
            .objectTypeId(18).reference(1085, channelId)
            .objectTypeId(18).reference(1086, userId);
    List<Reserve> chats = em.filter(Reserve.class, query, false);
    for (Reserve chat:chats) {
      em.delete(chat.getId());
    }
  }
}
