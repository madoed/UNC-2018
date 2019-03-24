package com.netcreker.meetup.service.chat;

import com.netcreker.meetup.databasemanager.query.ObjectQuery;
import com.netcreker.meetup.databasemanager.query.ReferenceQuery;
import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.meeting.Participant;
import com.netcreker.meetup.entity.notification.Reserve;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ChatService {
  @Autowired
  private EntityManager em;

  public List<Chat> getOldChats(Long id){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(6).reference(1030, id);
    List<Chat> allChats = em.filter(Chat.class, query, false);
    if (allChats.isEmpty())
      return allChats;

    allChats.sort((o1, o2) -> o2.getLastUpdate().compareTo(o1.getLastUpdate()));
    query = ObjectQuery.newInstance().objectTypeId(18).reference(1086, id);
    List<Reserve> newChats = em.filter(Reserve.class, query, false);
    if (newChats.isEmpty())
      return allChats;

    List<Chat> result = new ArrayList<>(allChats);

    for (Chat chat:allChats) {
      for (Reserve note:newChats) {
        if (chat.getId()==note.getReserveChat().getId()) {
          result.remove(chat);
        }
      }
    }

    return result;
  }

  public List<Chat> getNewChats(Long id){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(6).reference(1030, id);
    List<Chat> allChats = em.filter(Chat.class, query, false);
    if (allChats.isEmpty())
      return allChats;

    List<Chat> result = new ArrayList<>();

    allChats.sort((o1, o2) -> o2.getLastUpdate().compareTo(o1.getLastUpdate()));
    query = ObjectQuery.newInstance().objectTypeId(18).reference(1086, id);
    List<Reserve> newChats = em.filter(Reserve.class, query, false);
    if (newChats.isEmpty()) {
      return new ArrayList<Chat>();
    }

    for (Chat chat:allChats) {
      for (Reserve note:newChats) {
        if (note.getReserveChat().getId()==chat.getId()) {
          result.add(chat);
          break;
        }
      }
    }
    return result;
  }

  public List<User> getFriends(Long id){
    User user = em.load(User.class, id);
    return user.getFriends();
  }

  public Chat getChat(Long id){
    return em.load(Chat.class, id);
  }

  public Chat createDialogue (long user1, long user2) {
    User first = em.load(User.class, user1);
    User second = em.load(User.class, user2);
    Chat dialogue = new Chat();
    dialogue.setChatName("dialogue " + first.getName() + " and " + second.getName());
    dialogue.setName(dialogue.getChatName());
    dialogue.setChatType("dialogue");
    List<User> subs = new ArrayList<>();
    subs.add(first);
    subs.add(second);
    dialogue.setSubscribers(subs);
    dialogue.setLastUpdate(new Date());
    em.save(dialogue);
    return dialogue;
  }

  public Chat getDialogue(long user1, long user2) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(6).reference(1030, user1)
            .objectTypeId(6).value(1088, "dialogue");
    List<Chat> dialogues = em.filter(Chat.class,  query, false);
    Chat dialogue = null;
    if (dialogues.isEmpty()) {
      dialogue = createDialogue(user1, user2);
    } else {
      //User userTwo = em.load(User.class, user2);
      for (Chat c: dialogues) {
        for (User sub:c.getSubscribers()) {
          if (sub.getId() == user2) {
            dialogue = c;
            break;
          }
        }
        if(dialogue!=null)
          break;
      }
      if (dialogue==null) {
        dialogue = createDialogue(user1, user2);
      }
    }
    return dialogue;
  }

  public void addChat(Chat newChat){
    newChat.setName(newChat.getChatName());
    newChat.setLastUpdate(new Date());
    newChat.setChatType("chat");
    em.save(newChat);
  }

  public Integer getMeetingChatId(long partId) {
    Participant participant = em.load(Participant.class, partId);
    return (int)(participant.getParticipantOfMeeting().getMeetingChat().getId());
  }
}
