package com.netcreker.meetup.entity.meeting;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.chat.Chat;
import com.netcreker.meetup.entity.location.Location;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(9)
public class Meeting extends Entity {
  @Parameter(1039)
  private String meetingName;
  @Parameter(1040)
  private String meetingDescription;
  @Parameter(1041)
  @Temporal(TemporalType.TIMESTAMP)
  private  Date dateOfMeeting;
  @Parameter(1079)
  @Temporal(TemporalType.TIMESTAMP)
  private  Date timeOfMeeting;
  @Parameter(1042)
  private String status;//new, confirmed, past, closed?
  @Reference(1043)
  private User boss;
  @Reference(1044)
  private Chat meetingChat;
  @Reference(1045)
  private Location meetingLocation;
  @Parameter(1076)
  private Integer amountOfParticipants;
}
