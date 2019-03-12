package com.netcreker.meetup.service.meeting;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.check.Bill;
import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.entity.meeting.Participant;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
      List<Participant> past = new ArrayList<Participant>();
      for (Participant part:participation) {
        if ((part.getParticipantOfMeeting().getDateOfMeeting()!=null)&&part.getParticipantOfMeeting().getDateOfMeeting().before(date)) {
          past.add(part);
        }
      }
      return past;
    } else if(type.equals(0)){
      Date date = new Date();
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

  public Meeting createMeeting(Meeting meeting) {
    if (meeting.getMeetingLocation()!=null){
      em.save(meeting.getMeetingLocation());
    }
    meeting.setName(meeting.getMeetingName());
    em.save(meeting);
    Bill bill = new Bill();
    bill.setName("bill for " + meeting.getMeetingName());
    bill.setBillStatus("empty");
    bill.setBillTotalSum(0.0);
    bill.setBillCommonAmount(0.0);
    bill.setBillOfMeeting(meeting);
    bill.setBillOwner(meeting.getBoss());
    bill.setDateOfBill(meeting.getDateOfMeeting());
    em.save(bill);
    Chat chat = new Chat();
    chat.setChatName(meeting.getMeetingName() + " chat");
    chat.setName(meeting.getMeetingName() + " chat");
    em.save(chat);
    meeting.setMeetingChat(chat);
    em.save(meeting);
    return meeting;
  }

  public void addParticipants(List<Participant> participants, long meetingId) {
    Meeting meeting = em.load(Meeting.class, meetingId);
    Chat chat = em.load(Chat.class, meeting.getMeetingChat().getId());
    List<User> pplInChat = new ArrayList<>();
    for (Participant participant:participants) {
      participant.setParticipantOfMeeting(meeting);
      participant.setName(participant.getMeetingParticipant().getName());
      participant.setAlreadyPayed(0.0);
      em.save(participant);
      pplInChat.add(participant.getMeetingParticipant());
    }
    meeting.setAmountOfParticipants(participants.size());
    em.save(meeting);
    chat.setSubscribers(pplInChat);
    em.save(chat);
  }

  public void confirmParticipation(long participantId) {
    Participant participant = em.load(Participant.class, participantId);
    participant.setStatusOfConfirmation("confirmed");
    em.save(participant);
  }
}
