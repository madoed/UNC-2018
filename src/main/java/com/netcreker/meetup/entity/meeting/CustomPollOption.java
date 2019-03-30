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
@ObjectType(20)
public class CustomPollOption extends Entity {
//  @Reference(1100)
//  private CustomPoll optionOfPoll;
  @Reference(1101)
  private User suggesterOfOption;
  @Reference(1102)
  private List<User> voicesForOption = new ArrayList<>();
  @Parameter(1103)
  private String option;
  @Parameter(1104)
  private Double percentageForOption;
}
