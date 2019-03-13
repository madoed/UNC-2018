package com.netcreker.meetup.controller.meeting;


import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.entity.meeting.Participant;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.service.meeting.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MeetingController {

  @Autowired
  private MeetingService meetingService;

  @GetMapping("/meeting-list/{id}/{type}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<Participant>> getAllMeetings(@PathVariable long id, @PathVariable Integer type) {
    List<Participant> meetings = meetingService.getMeetings(id, type);
    if(meetings.isEmpty()){
      return new ResponseEntity<List<Participant>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Participant>>(meetings, HttpStatus.OK);
  }

  @GetMapping("/meeting/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?> getMeeting(@PathVariable long id) {
    Meeting meeting = meetingService.getMeeting(id);
    if(meeting==null){
      return new ResponseEntity<Meeting>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
  }

  /*@GetMapping("/meeting/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<User>>  getFriends(@PathVariable long id) {
    List<User> friends = chatService.getFriends(id);
    if(friends.isEmpty()){
      return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<User>>(friends, HttpStatus.OK);
  }*/

  @PostMapping("/meeting")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>addMeeting(@RequestBody Meeting newMeeting) {
    meetingService.addMeeting(newMeeting);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @PostMapping("/meeting-date/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>setDate(@RequestBody Date newDate, @PathVariable long id) {
    meetingService.setDate(newDate, id);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @PostMapping("/meeting-status/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>changeMeetingStatus(@RequestBody String status, @PathVariable long id) {
    meetingService.changeMeetingStatus(status, id);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.OK);
  }

  @PostMapping("/meeting-time/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>setTime(@RequestBody Date newTime, @PathVariable long id) {
    meetingService.setTime(newTime, id);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @PostMapping("/meeting-description/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>setDescription(@RequestBody String newDescription, @PathVariable long id) {
    meetingService.setDescription(newDescription, id);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @PostMapping("/meeting-location/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>setLocation(@RequestBody Location location, @PathVariable long id) {
    meetingService.setLocation(location, id);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @GetMapping("/participants/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<Participant>>  getFriends(@PathVariable long id) {
    List<Participant> participants = meetingService.getParticipants(id);
    if(participants.isEmpty()){
      return new ResponseEntity<List<Participant>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Participant>>(participants, HttpStatus.OK);
  }

  @GetMapping("/participant/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>  getParticipant(@PathVariable long id) {
    Participant participant = meetingService.getParticipant(id);
    if(participant==null){
      return new ResponseEntity<Participant>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Participant>(participant, HttpStatus.OK);
  }

  @PostMapping("/meeting-create")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>createMeeting(@RequestBody Meeting meeting) {
    meetingService.createMeeting(meeting);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);
  }

  @PostMapping("/meeting-add-participants/{meetingId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>addParticipants(@RequestBody List<Participant> participants, @PathVariable long meetingId) {
    meetingService.addParticipants(participants, meetingId);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

  @PostMapping("/meeting-add-participant/{meetingId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>addParticipants(@RequestBody Participant participant, @PathVariable long meetingId) {
    Participant part = meetingService.addParticipant(participant, meetingId);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<Participant>(part, HttpStatus.CREATED);
  }

  @PostMapping("/participant-confirm")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>confirmParticipation(@RequestBody long participantId) {
    meetingService.confirmParticipation(participantId);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.OK);
  }

}
