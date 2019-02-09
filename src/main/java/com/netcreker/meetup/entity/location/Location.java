package com.netcreker.meetup.entity.location;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.meeting.Meeting;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(5)
public class Location extends Entity {
  @Parameter(1026)
  private String lng;
  @Parameter(1027)
  private String lat;
  @Parameter(1028)
  private String placeName;
  @Reference(1029)
  private Meeting meeting;
}

