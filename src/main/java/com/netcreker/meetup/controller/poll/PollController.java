package com.netcreker.meetup.controller.poll;

import com.netcreker.meetup.entity.location.MeetingLocation;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.service.poll.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
