package com.netcreker.meetup.service.check;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.check.Bill;
import com.netcreker.meetup.entity.check.BillItem;
import com.netcreker.meetup.entity.check.Check;
import com.netcreker.meetup.entity.check.ItemAmount;
import com.netcreker.meetup.entity.meeting.Participant;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckService {

  @Autowired
  private EntityManager em;

  public List<BillItem> findAllBillItems(long meetingId){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(11).reference(1050, meetingId);
    long billId = em.filter(Bill.class, query, false).get(0).getId();

    query = ObjectQuery.newInstance()
            .objectTypeId(12).reference(1058, billId);

    List<BillItem> items = em.filter(BillItem.class, query, false);

    return items;
  }

  public List<ItemAmount>   findAllParticipantItems(long participantId){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(14).reference(1062, participantId);
    long checkId = em.filter(Check.class, query, false).get(0).getId();

    query = ObjectQuery.newInstance()
            .objectTypeId(13).reference(1060, checkId);

    List<ItemAmount> items = em.filter(ItemAmount.class, query, false);

    return items;
  }
}
