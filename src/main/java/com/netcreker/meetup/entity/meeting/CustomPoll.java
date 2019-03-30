package com.netcreker.meetup.entity.meeting;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(19)
public class CustomPoll extends Entity {
  @Reference(1097)
  private Meeting pollOfMeeting;
  @Parameter(1098)
  private String customPollName;
  @Parameter(1099)
  private Integer isOpened;
  @Reference(1100)
  private List<CustomPollOption> optionsInPoll = new ArrayList<>();
  @Reference(1105)
  private  User creatorOfPoll;
}
