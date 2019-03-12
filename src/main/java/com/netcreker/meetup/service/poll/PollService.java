package com.netcreker.meetup.service.poll;


import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.location.MeetingLocation;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

  @Autowired
  private EntityManager em;

  public List<MeetingLocation> getPlacePoll(long meetingId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(16).reference(1069, meetingId);
    return em.filter(MeetingLocation.class, query, false);
  }

  public Meeting openPlacePoll(long meetingId) {
    Meeting meeting = em.load(Meeting.class, meetingId);
    meeting.setPollForPlaceOpen(1);
    em.save(meeting);
    return meeting;
  }

  public Meeting closePlacePoll(long meetingId) {
    Meeting meeting = em.load(Meeting.class, meetingId);
    meeting.setPollForPlaceOpen(0);
    em.save(meeting);
    return meeting;
  }

}
