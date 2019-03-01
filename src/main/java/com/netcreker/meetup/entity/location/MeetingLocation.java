package com.netcreker.meetup.entity.location;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(16)
public class MeetingLocation extends Entity {

  @Reference(1068)
  private Location oneLocation;
  @Reference(1069)
  private Meeting locationOfMeeting;
  @Reference(1070)
  private User suggesterOfLocation;
  @Reference(1071)
  private List<User> voicesForLocation = new ArrayList<>();
}
