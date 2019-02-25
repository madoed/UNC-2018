package com.netcreker.meetup.service.location;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.entity.meeting.Participant;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

  @Autowired
  private EntityManager em;

  public void setMeetingLocation(long meetingid, Location location){
    Meeting meeting = em.load(Meeting.class, meetingid);
    meeting.setMeetingLocation(location);
    em.save(meeting);
  }

  public void addMeetingLocation(long participantId, Location location){
    Participant participant = em.load(Participant.class, participantId);
    participant.getSuggestedLocations().add(location);
    em.save(participant);
  }

  public List<Location> getMeetingLocations(Long meetingId){

    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(5).reference(1046, meetingId).reference(1048, meetingId);
    List<Location> locations = em.filter(Location.class, query, false);

    return locations;
  }

}
