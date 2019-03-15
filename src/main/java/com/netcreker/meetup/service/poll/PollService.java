package com.netcreker.meetup.service.poll;


import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.entity.location.MeetingLocation;
import com.netcreker.meetup.entity.meeting.DatePoll;
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
public class PollService {

  @Autowired
  private EntityManager em;

  public List<MeetingLocation> getPlacePoll(long meetingId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(16).reference(1069, meetingId);
    List<MeetingLocation> placePolls = em.filter(MeetingLocation.class, query, false);
    Integer participantsAmount;
    for (MeetingLocation place:placePolls) {
      participantsAmount = ParticipantsAmount(place.getLocationOfMeeting().getId());
      place.setPercentageForPlace(Math.round(place.getVoicesForLocation().size()*100/ participantsAmount
              * 100.0) / 100.0);
    }
    placePolls.sort((o1, o2) -> o2.getPercentageForPlace().compareTo(o1.getPercentageForPlace()));
    return placePolls;
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

  public MeetingLocation addPlaceInPoll(Location location, long participantId) {
    location.setName(location.getPlaceName());
    em.save(location);
    Participant participant = em.load(Participant.class, participantId);
    MeetingLocation meetingLocation = new MeetingLocation();
    meetingLocation.setLocationOfMeeting(participant.getParticipantOfMeeting());
    meetingLocation.setOneLocation(location);
    meetingLocation.setSuggesterOfLocation(participant.getMeetingParticipant());
    List<User> voices = new ArrayList<>();
    voices.add(participant.getMeetingParticipant());
    meetingLocation.setVoicesForLocation(voices);
    meetingLocation.setName(location.getName());
    em.save(meetingLocation);
    return meetingLocation;
  }

  public MeetingLocation voteForPlace(long meetingLocationId, long userId) {
    MeetingLocation meetingLocation = em.load(MeetingLocation.class, meetingLocationId);
    List<User> voices = meetingLocation.getVoicesForLocation();
    User user = em.load(User.class, userId);
    if (voices.contains(user))
      voices.remove(user);
    else
      voices.add(user);
    meetingLocation.setVoicesForLocation(voices);
    em.save(meetingLocation);
    return meetingLocation;
  }

  private Integer ParticipantsAmount (long meetingId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(10).reference(1048, meetingId)
            .objectTypeId(10).value(1078, "confirmed");
    List<DatePoll> datePolls = em.filter(DatePoll.class, query, false);
    return datePolls.size();
  }

  public List<DatePoll> getDatePoll(long meetingId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(17).reference(1072, meetingId);
    List<DatePoll> datePolls = em.filter(DatePoll.class, query, false);
    Integer participantsAmount;
    for (DatePoll date:datePolls) {
      participantsAmount = ParticipantsAmount(date.getOneDateOfMeeting().getId());
      date.setPercentageForDate(Math.round(date.getVoicesForDate().size()* participantsAmount
              * 10000.0) / 100.0);
    }
    datePolls.sort((o1, o2) -> o2.getPercentageForDate().compareTo(o1.getPercentageForDate()));
    return datePolls;
  }

  public Meeting openDatePoll(long meetingId) {
    Meeting meeting = em.load(Meeting.class, meetingId);
    meeting.setPollForDateOpen(1);
    em.save(meeting);
    return meeting;
  }

  public Meeting closeDatePoll(long meetingId) {
    Meeting meeting = em.load(Meeting.class, meetingId);
    meeting.setPollForDateOpen(0);
    em.save(meeting);
    return meeting;
  }

  public DatePoll addDateInPoll(Date date, long participantId) {

    Participant participant = em.load(Participant.class, participantId);
    DatePoll datePoll = new DatePoll();
    datePoll.setDateOption(date);
    datePoll.setOneDateOfMeeting(participant.getParticipantOfMeeting());
    datePoll.setSuggesterOfDate(participant.getMeetingParticipant());
    List<User> voices = new ArrayList<>();
    voices.add(participant.getMeetingParticipant());
    datePoll.setVoicesForDate(voices);
    datePoll.setName(date.toString());
    em.save(datePoll);
    return datePoll;
  }

  public DatePoll voteForDate(long datePollId, long userId) {
    DatePoll datePoll = em.load(DatePoll.class, datePollId);
    List<User> voices = datePoll.getVoicesForDate();
    User user = em.load(User.class, userId);
    if (voices.contains(user))
      voices.remove(user);
    else
      voices.add(user);
    datePoll.setVoicesForDate(voices);
    em.save(datePoll);
    return datePoll;
  }
}
