package com.netcreker.meetup.entity.check;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.card.Card;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(11)
public class Bill extends Entity {

    //private long bill_fn;
    //private long bill_fd;

  @Reference(1049)
  private User billOwner;
  @Reference(1050)
  private Meeting billOfMeeting;
  @Parameter(1051)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateOfBill;
  @Parameter(1052)
  private String billStatus;//empty, open, closed
  @Parameter(1064)
  private Double billCommonAmount;
  @Parameter(1077)
  private Double billTotalSum;
  @Reference(1066)
  private Card mainCard;
}
