package com.netcreker.meetup.service.poll;


import com.netcreker.meetup.databasemanager.query.ObjectQuery;
import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.entity.location.MeetingLocation;
import com.netcreker.meetup.entity.meeting.*;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
    placePolls.sort(Comparator.comparing(MeetingLocation::getId));
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
    User user = em.load(User.class, participant.getMeetingParticipant().getId());
    MeetingLocation meetingLocation = new MeetingLocation();
    meetingLocation.setLocationOfMeeting(participant.getParticipantOfMeeting());
    meetingLocation.setOneLocation(location);
    meetingLocation.setSuggesterOfLocation(user);
    List<User> voices = new ArrayList<>();
    voices.add(user);
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
      date.setPercentageForDate(Math.round(date.getVoicesForDate().size()*100/ participantsAmount
              * 100.0) / 100.0);
    }
    datePolls.sort(Comparator.comparing(DatePoll::getId));
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
    User user = em.load(User.class, participant.getMeetingParticipant().getId());
    DatePoll datePoll = new DatePoll();
    datePoll.setDateOption(date);
    datePoll.setOneDateOfMeeting(participant.getParticipantOfMeeting());
    datePoll.setSuggesterOfDate(user);
    List<User> voices = new ArrayList<>();
    voices.add(user);
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

  public CustomPoll createPoll (CustomPoll customPoll) {
    customPoll.setIsOpened(1);
    customPoll.setName(customPoll.getCustomPollName());
    em.save(customPoll);
    return customPoll;
  }

  public List<CustomPoll> getCustomPolls (long meetingId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(19).reference(1097, meetingId);
    List<CustomPoll> customPolls = em.filter(CustomPoll.class, query, false);
    if(customPolls.isEmpty()) {
      return null;
    }
    Integer participantsAmount = ParticipantsAmount(customPolls.get(0).getPollOfMeeting().getId());
    List<CustomPollOption> customPollOptions;
    for (CustomPoll customPoll: customPolls) {
      customPollOptions = customPoll.getOptionsInPoll();
      for (CustomPollOption customPollOption:customPollOptions) {
        customPollOption.setPercentageForOption(Math.round(customPollOption.getVoicesForOption().size()*100/ participantsAmount
                * 100.0) / 100.0);
      }
      customPollOptions.sort(Comparator.comparing(CustomPollOption::getId));
    }
    return customPolls;
  }

  public CustomPoll openCustomPoll(long pollId) {
    CustomPoll customPoll = em.load(CustomPoll.class, pollId);
    customPoll.setIsOpened(1);
    em.save(customPoll);
    return customPoll;
  }

  public CustomPoll closeCustomPoll(long pollId) {
    CustomPoll customPoll = em.load(CustomPoll.class, pollId);
    customPoll.setIsOpened(1);
    em.save(customPoll);
    return customPoll;
  }

  public CustomPollOption voteForOption(long customPollOptionId, long userId) {
    CustomPollOption customPollOption = em.load(CustomPollOption.class, customPollOptionId);
    List<User> voices = customPollOption.getVoicesForOption();
    User user = em.load(User.class, userId);
    if (voices.contains(user))
      voices.remove(user);
    else
      voices.add(user);
    customPollOption.setVoicesForOption(voices);
    em.save(customPollOption);

    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(19).reference(1100, customPollOption.getId());
    CustomPoll customPoll = em.filter(CustomPoll.class, query, false).get(0);

    Integer participantsAmount = ParticipantsAmount(customPoll.getPollOfMeeting().getId());

    customPollOption.setPercentageForOption(Math.round(customPollOption.getVoicesForOption().size()*100/ participantsAmount
              * 100.0) / 100.0);

    return customPollOption;
  }

  public CustomPoll addOptionInPoll(long pollId, CustomPollOption option, long participantId) {

    Participant participant = em.load(Participant.class, participantId);
    User user = em.load(User.class, participant.getMeetingParticipant().getId());
    CustomPollOption customPollOption = new CustomPollOption();
    customPollOption.setOption(option.getOption());
    customPollOption.setSuggesterOfOption(user);
    List<User> voices = new ArrayList<>();
    voices.add(user);
    customPollOption.setVoicesForOption(voices);
    customPollOption.setName(customPollOption.getOption());
    em.save(customPollOption);
    CustomPoll poll = em.load(CustomPoll.class, pollId);
    List<CustomPollOption> options = new ArrayList<>();
    options.add(customPollOption);
    poll.setOptionsInPoll(options);
    em.save(poll);

    Integer participantsAmount = ParticipantsAmount(poll.getPollOfMeeting().getId());
    for (CustomPollOption opt:poll.getOptionsInPoll()) {
      opt.setPercentageForOption(Math.round(customPollOption.getVoicesForOption().size()*100/ participantsAmount
              * 100.0) / 100.0);
    }
    poll.getOptionsInPoll().sort(Comparator.comparing(CustomPollOption::getId));

    return poll;
  }
}
