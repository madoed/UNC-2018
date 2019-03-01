package com.netcreker.meetup.service.chat;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChatService {
  @Autowired
  private EntityManager em;

  public List<Chat> getChats(Long id){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(6).reference(1030, id);
    List<Chat> result = em.filter(Chat.class, query, false);
    return result;
  }

  public List<User> getFriends(Long id){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(1).reference(1006, id);
    List<User> result = em.filter(User.class, query, false);
    return result;
  }

  public Chat getChat(Long id){
    return em.load(Chat.class, id);
  }

  public void addChat(Chat newChat){
    em.save(newChat);
  }
}
