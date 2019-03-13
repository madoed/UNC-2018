package com.netcreker.meetup.controller.poll;

import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.entity.location.MeetingLocation;
import com.netcreker.meetup.entity.meeting.DatePoll;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.service.poll.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PollController {

  @Autowired
  private PollService pollService;

  @GetMapping("/meeting-place-poll/{meetingId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<MeetingLocation>> getPlacePoll(@PathVariable long meetingId) {
    List<MeetingLocation> poll = pollService.getPlacePoll(meetingId);
    if(poll.isEmpty()){
      return new ResponseEntity<List<MeetingLocation>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<MeetingLocation>>(poll, HttpStatus.OK);
  }

  @GetMapping("/meeting-place-poll-open/{meetingId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Meeting> openPlacePoll(@PathVariable long meetingId) {
    Meeting meeting = pollService.openPlacePoll(meetingId);
    return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);
  }

  @GetMapping("/meeting-place-poll-close/{meetingId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Meeting> closePlacePoll(@PathVariable long meetingId) {
    Meeting meeting = pollService.closePlacePoll(meetingId);
    return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);
  }

  @PostMapping("/meeting-place-poll-add/{participantId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<MeetingLocation>addPlaceInPoll(@RequestBody Location location, @PathVariable long participantId) {
    MeetingLocation meetingLocation = pollService.addPlaceInPoll(location, participantId);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<MeetingLocation>(meetingLocation, HttpStatus.CREATED);
  }

  @PostMapping("/meeting-place-poll-vote/{userId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<MeetingLocation> voteForPlace(@RequestBody long meetingLocationId, @PathVariable long userId) {
    MeetingLocation meetingLocation = pollService.voteForPlace(meetingLocationId, userId);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<MeetingLocation>(meetingLocation, HttpStatus.OK);
  }

  @GetMapping("/meeting-date-poll/{meetingId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<DatePoll>> getDatePoll(@PathVariable long meetingId) {
    List<DatePoll> poll = pollService.getDatePoll(meetingId);
    if(poll.isEmpty()){
      return new ResponseEntity<List<DatePoll>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<DatePoll>>(poll, HttpStatus.OK);
  }

  @GetMapping("/meeting-date-poll-open/{meetingId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Meeting> openDatePoll(@PathVariable long meetingId) {
    Meeting meeting = pollService.openDatePoll(meetingId);
    return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);
  }

  @GetMapping("/meeting-date-poll-close/{meetingId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Meeting> closeDatePoll(@PathVariable long meetingId) {
    Meeting meeting = pollService.closeDatePoll(meetingId);
    return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);
  }

  @PostMapping("/meeting-date-poll-add/{participantId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<DatePoll> addDateInPoll(@RequestBody Date date, @PathVariable long participantId) {
    DatePoll datePoll = pollService.addDateInPoll(date, participantId);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<DatePoll>(datePoll, HttpStatus.CREATED);
  }

  @PostMapping("/meeting-date-poll-vote/{userId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<DatePoll> voteForDate(@RequestBody long datePollId, @PathVariable long userId) {
    DatePoll datePoll = pollService.voteForDate(datePollId, userId);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<DatePoll>(datePoll, HttpStatus.OK);
  }
}
