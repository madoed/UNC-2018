package com.netcreker.meetup.entity.check;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(13)
public class ItemAmount extends Entity {

  @Reference(1059)
  private BillItem billItemAmount;
  @Reference(1060)
  private Check itemAmountCheck;
  @Parameter(1061)
  private Integer amountInCheck;
}
