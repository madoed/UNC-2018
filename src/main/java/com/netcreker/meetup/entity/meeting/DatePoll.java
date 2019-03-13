package com.netcreker.meetup.entity.meeting;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(17)
public class DatePoll extends Entity {
  @Reference(1072)
  private Meeting oneDateOfMeeting;
  @Reference(1073)
  private User suggesterOfDate;
  @Reference(1074)
  private List<User> voicesForDate = new ArrayList<>();
  @Parameter(1075)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateOption;
  @Parameter(1083)
  private Double percentageForDate;
}
