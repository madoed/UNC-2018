package com.netcreker.meetup.entity.check;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.meeting.Participant;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(14)
public class Check extends Entity {
  @Reference(1062)
  private Participant checkOwner;
  @Parameter(1063)
  private Double checkAmount;
  @Reference(1065)
  private String checkStatus;
}
