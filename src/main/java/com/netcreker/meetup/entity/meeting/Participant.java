package com.netcreker.meetup.entity.meeting;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(10)
public class Participant extends Entity {

  @Reference(1046)
  private User meetingParticipant;
  @Reference(1047)
  private List<Location> suggestedLocations;
  @Reference(1048)
  private Meeting participantOfMeeting;
  @Parameter(1078)
  private String statusOfConfirmation;// not confirmed, confirmed
  @Parameter(1080)
  private Double alreadyPayed;
}
