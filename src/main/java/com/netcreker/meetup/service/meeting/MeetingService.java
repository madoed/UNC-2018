package com.netcreker.meetup.service.meeting;

import com.netcreker.meetup.databasemanager.query.ObjectQuery;
import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.check.Bill;
import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.entity.meeting.Participant;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MeetingService {

  @Autowired
  private EntityManager em;

  public List<Participant> getMeetings(Long id, Integer type){
    ObjectQuery query;
    if(type.equals(2)){
      query = ObjectQuery.newInstance()
              .objectTypeId(10).reference(1046, id)
              .objectTypeId(10).value(1078, "not confirmed");
    }
    else {
      query = ObjectQuery.newInstance()
              .objectTypeId(10).reference(1046, id)
              .objectTypeId(10).value(1078, "confirmed");
    }
    List<Participant> participation = em.filter(Participant.class, query, false);

    if(type.equals(1)){
      Date date = new Date();
      date.setTime(24);
      List<Participant> past = new ArrayList<Participant>();
      for (Participant part:participation) {
        if ((part.getParticipantOfMeeting().getDateOfMeeting()!=null)&&part.getParticipantOfMeeting().getDateOfMeeting().before(date)) {
          past.add(part);
        }
      }
      return past;
    } else if(type.equals(0)){
      Date date = new Date();
      date.setTime(24);
      List<Participant> future = new ArrayList<Participant>();
      for (Participant part:participation) {
        if ((part.getParticipantOfMeeting().getDateOfMeeting()==null)||part.getParticipantOfMeeting().getDateOfMeeting().after(date)) {
          future.add(part);
        }
      }
      return future;
    }

    return participation;
  }

  public Meeting getMeeting(Long id){

    //change -1 on id
    /*ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(9).reference(1043, id);*/
    return em.load(Meeting.class, id);

    //return meeting.isEmpty() ? null : meeting.get(0);
  }

  public Participant getParticipant(Long id){

    //change -1 on id
    /*ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(9).reference(1043, id);*/
    return em.load(Participant.class, id);

    //return meeting.isEmpty() ? null : meeting.get(0);
  }

  public void addMeeting(Meeting newMeeting){
    em.save(newMeeting);
  }

  public void setDate(Date newDate, long id){
    Meeting meeting = em.load(Meeting.class, id);
    meeting.setDateOfMeeting(newDate);
    em.save(meeting);
  }

  public void setTime(Date newTime, long id){
    Meeting meeting = em.load(Meeting.class, id);
    meeting.setTimeOfMeeting(newTime);
    em.save(meeting);
  }

  public void setLocation(Location location, long id) {
    location.setName(location.getPlaceName());
    em.save(location);
    Meeting meeting = em.load(Meeting.class, id);
    meeting.setMeetingLocation(location);
    em.save(meeting);
  }

  public void  setDescription(String newDescription, long id) {
    Meeting meeting = em.load(Meeting.class, id);
    meeting.setMeetingDescription(newDescription);
    em.save(meeting);
  }

  public List<Participant> getParticipants(Long id){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(10).reference(1048, id);
    List<Participant> result = em.filter(Participant.class, query, false);
    return result;
  }

  private Meeting setDateForEveryWeek (Meeting meeting) {
    DateTime dt = new DateTime();
    int currentDayOfTheWeek = dt.getDayOfWeek();
    System.out.println("currentDayOfTheWeek " + currentDayOfTheWeek);
    if (currentDayOfTheWeek>(meeting.getRecursiveInfo())) {
      dt = dt.plusDays(meeting.getRecursiveInfo()-currentDayOfTheWeek + 7);
      System.out.println("1 " + dt);
    } else if (currentDayOfTheWeek<(meeting.getRecursiveInfo())){
      dt = dt.plusDays(meeting.getRecursiveInfo()-currentDayOfTheWeek);
      System.out.println("2 " + dt);
    }
    meeting.setDateOfMeeting(dt.toDate());
    System.out.println(meeting.getDateOfMeeting());
    return meeting;
  }

  private Meeting setDateForEveryMonthFirst (Meeting meeting) {
    DateTime dt = new DateTime();
    DateTime dtFirst = dt.dayOfMonth().setCopy(1);
    System.out.println(dtFirst);
    int firstDay = dtFirst.getDayOfWeek();
    if (firstDay>(meeting.getRecursiveInfo())) {
      dtFirst = dtFirst.plusDays(-(firstDay - meeting.getRecursiveInfo()) + 7);
      System.out.println("3 " + dtFirst);
    } else if (firstDay<(meeting.getRecursiveInfo())){
      dtFirst = dtFirst.plusDays(meeting.getRecursiveInfo()-firstDay);
      System.out.println("4 " + dtFirst);
    }

    if (dtFirst.getDayOfMonth()<dt.getDayOfMonth()) {
      dtFirst = dt.plusMonths(1).dayOfMonth().setCopy(1);
      System.out.println(dtFirst);
      firstDay = dtFirst.getDayOfWeek();
      if (firstDay>(meeting.getRecursiveInfo())) {
        dtFirst = dtFirst.plusDays(-(firstDay - meeting.getRecursiveInfo()) + 7);
        System.out.println("5 " + dtFirst);
      } else if (firstDay<(meeting.getRecursiveInfo())){
        dtFirst = dtFirst.plusDays(meeting.getRecursiveInfo()-firstDay);
        System.out.println("6 " + dtFirst);
      }
      dt = dtFirst;
    } else {
      dt = dtFirst;
    }

    meeting.setDateOfMeeting(dt.toDate());
    System.out.println(meeting.getDateOfMeeting());
    return meeting;
  }

  private Meeting setDateForEveryMonthLast (Meeting meeting) {
    DateTime dt = new DateTime();
    DateTime dtLast = dt.plusMonths(1).dayOfMonth().setCopy(1).minusDays(1);
    System.out.println(dtLast);
    int lastDay = dtLast.getDayOfWeek();
    if (lastDay<(meeting.getRecursiveInfo())) {
      dtLast = dtLast.plusDays(meeting.getRecursiveInfo() - lastDay - 7);
      System.out.println("7 " + dtLast);
    } else if (lastDay>(meeting.getRecursiveInfo())){
      dtLast = dtLast.minusDays(lastDay-meeting.getRecursiveInfo());
      System.out.println("8 " + dtLast);
    }

    if (dtLast.getDayOfMonth()<dt.getDayOfMonth()) {
      dtLast = dtLast.plusMonths(2).dayOfMonth().setCopy(1).minusDays(1);
      System.out.println(dtLast);
      lastDay = dtLast.getDayOfWeek();
      if (lastDay<(meeting.getRecursiveInfo())) {
        dtLast = dtLast.plusDays(meeting.getRecursiveInfo() - lastDay - 7);
        System.out.println("9 " + dtLast);
      } else if (lastDay>(meeting.getRecursiveInfo())){
        System.out.println(dtLast);
        dtLast = dtLast.minusDays(lastDay-meeting.getRecursiveInfo());
        System.out.println("10 " + dtLast);
      }
      dt = dtLast;
    } else {
      dt = dtLast;
    }

    meeting.setDateOfMeeting(dt.toDate());
    System.out.println(meeting.getDateOfMeeting());
    return meeting;
  }

  private Meeting setDateForEvery (Meeting meeting) {
    DateTime dt = new DateTime();
    int currentDayOfTheWeek = dt.getDayOfWeek();
    if (currentDayOfTheWeek>(meeting.getRecursiveInfo())) {
      dt = dt.plusDays(meeting.getRecursiveInfo()-currentDayOfTheWeek + 7);
      System.out.println("1 " + dt);
    } else if (currentDayOfTheWeek<(meeting.getRecursiveInfo())){
      dt = dt.plusDays(meeting.getRecursiveInfo()-currentDayOfTheWeek);
      System.out.println("2 " + dt);
    }
    meeting.setDateOfMeeting(dt.toDate());
    System.out.println(meeting.getDateOfMeeting());
    return meeting;
  }

  private Meeting setDateForEveryYear (Meeting meeting) {
    DateTime dt = new DateTime();
    int currentDayOfTheWeek = dt.getDayOfWeek();
    if (currentDayOfTheWeek>(meeting.getRecursiveInfo())) {
      dt = dt.plusDays(meeting.getRecursiveInfo()-currentDayOfTheWeek + 7);
      System.out.println("1 " + dt);
    } else if (currentDayOfTheWeek<(meeting.getRecursiveInfo())){
      dt = dt.plusDays(meeting.getRecursiveInfo()-currentDayOfTheWeek);
      System.out.println("2 " + dt);
    }
    meeting.setDateOfMeeting(dt.toDate());
    System.out.println(meeting.getDateOfMeeting());
    return meeting;
  }

  private Meeting createRecursiveMeeting (Meeting meeting) {
    String type = meeting.getMeetingType();
    if (type.equals("Every week")) {
      meeting = setDateForEveryWeek(meeting);
      return meeting;
    }
    if (type.equals("Every first week of the month")) {
      meeting = setDateForEveryMonthFirst(meeting);
      return meeting;
    }
    if (type.equals("Every last week of the month")) {
      meeting = setDateForEveryMonthLast(meeting);
      return meeting;
    }
    if (type.equals("Every")) {
      meeting = setDateForEvery(meeting);
      return meeting;
    }
    if (type.equals("Every year")) {
      meeting = setDateForEveryYear(meeting);
      return meeting;
    }
    return meeting;
  }

  public Meeting createMeeting(Meeting meeting) {

    if (!meeting.getMeetingType().equals( "simple")) {
      meeting = createRecursiveMeeting(meeting);
    }

      if (meeting.getMeetingLocation() != null) {
        Location location = meeting.getMeetingLocation();
        location.setName(location.getPlaceName());
        em.save(location);
      }
      meeting.setName(meeting.getMeetingName());
      meeting.setAmountOfParticipants(1);
      meeting.setPollForPlaceOpen(0);
      meeting.setPollForDateOpen(0);
      meeting.setAvatarUrl(meeting.getAvatarUrl());
      em.save(meeting);
      Bill bill = new Bill();
      bill.setName("bill for " + meeting.getMeetingName());
      bill.setBillStatus("empty");
      bill.setBillTotalSum(0.0);
      bill.setBillCommonAmount(0.0);
      bill.setBillOfMeeting(meeting);
      //bill.setBillOwner(meeting.getBoss());
      bill.setDateOfBill(meeting.getDateOfMeeting());
      em.save(bill);
      Chat chat = new Chat();
      chat.setChatName(meeting.getMeetingName() + " chat");
      chat.setName(meeting.getMeetingName() + " chat");
      chat.setChatType("meeting");
      chat.setLastUpdate(new Date());
      chat.setAvatarUrl(meeting.getAvatarUrl());
      List<User> pplInChat = new ArrayList<>();
      pplInChat.add(meeting.getBoss());
      chat.setSubscribers(pplInChat);
      em.save(chat);
      meeting.setMeetingChat(chat);
      em.save(meeting);

    return meeting;
  }

  public void addParticipants(List<Participant> participants, long meetingId) {
    Meeting meeting = em.load(Meeting.class, meetingId);
    Chat chat = em.load(Chat.class, meeting.getMeetingChat().getId());
    User user;
    //List<User> pplInChat = new ArrayList<>();
    for (Participant participant:participants) {
      user = em.load(User.class, participant.getMeetingParticipant().getId());
      participant.setMeetingParticipant(user);
      participant.setParticipantOfMeeting(meeting);
      participant.setName(participant.getMeetingParticipant().getName());
      participant.setAlreadyPayed(0.0);
      em.save(participant);
      //pplInChat.add(participant.getMeetingParticipant());
    }
    //meeting.setAmountOfParticipants(participants.size());
    em.save(meeting);
    //chat.setSubscribers(pplInChat);
    em.save(chat);
  }

  public Participant addParticipant(Participant participant, long meetingId) {
    Meeting meeting = em.load(Meeting.class, meetingId);
    Chat chat = em.load(Chat.class, meeting.getMeetingChat().getId());
    List<User> pplInChat = chat.getSubscribers();
    participant.setParticipantOfMeeting(meeting);
    participant.setName(participant.getMeetingParticipant().getName());
    participant.setAlreadyPayed(0.0);
    em.save(participant);
    pplInChat.add(participant.getMeetingParticipant());
    //meeting.setAmountOfParticipants(meeting.getAmountOfParticipants()+1);
    em.save(meeting);
    chat.setSubscribers(pplInChat);
    em.save(chat);
    return participant;
  }

  public void confirmParticipation(long participantId) {
    Participant participant = em.load(Participant.class, participantId);
    participant.setStatusOfConfirmation("confirmed");
    Meeting meeting = em.load(Meeting.class, participant.getParticipantOfMeeting().getId());
    meeting.setAmountOfParticipants(meeting.getAmountOfParticipants()+1);
    em.save(meeting);
    em.save(participant);
    Chat chat = em.load(Chat.class, participant.getParticipantOfMeeting().getMeetingChat().getId());
    List<User> userList = chat.getSubscribers();
    userList.add(participant.getMeetingParticipant());
    chat.setSubscribers(userList);
    em.save(chat);
  }

  public void declineParticipation(long participantId) {
    Participant participant = em.load(Participant.class, participantId);
    em.delete(participant.getId());
  }

  public void changeMeetingStatus(String status, long id){
    Meeting meeting = em.load(Meeting.class, id);
    meeting.setStatus(status);
    em.save(meeting);
  }
}
