package com.netcreker.meetup.service.meeting;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.entity.meeting.Participant;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MeetingService {

  @Autowired
  private EntityManager em;

  public List<Participant> getMeetings(Long id){

    //change -1 on id
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(10).reference(1046, id);
    List<Participant> participantion = em.filter(Participant.class, query, false);

    /*for (Participant part:participantion) {

    }

    query = ObjectQuery.newInstance()
            .objectTypeId(9).reference(1044, id);
    List<Meeting> result = em.filter(Meeting.class, query, false);*/
    return participantion;
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


}
