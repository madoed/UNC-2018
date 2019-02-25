package com.netcreker.meetup.entity.check;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(12)
public class BillItem extends Entity {

  @Parameter(1053)
  private String itemTitle;
  @Parameter(1054)
  private Integer itemAmount;
  @Parameter(1055)
  private Integer itemCurrentAmount;
  /*@Parameter(1056)
  private String itemStatus;*/
  @Parameter(1057)
  private Double price;
  @Reference(1058)
  private Bill itemBill;
}
