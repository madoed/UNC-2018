package com.netcreker.meetup.service.check;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.check.Bill;
import com.netcreker.meetup.entity.check.BillItem;
import com.netcreker.meetup.entity.check.Check;
import com.netcreker.meetup.entity.check.ItemAmount;
import com.netcreker.meetup.entity.meeting.Meeting;
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

  public BillItem loadItem(long item) {
    //Bill bill = em.load(Bill.class, item.getItemBill().getId());
    //ObjectQuery query = ObjectQuery.newInstance()
      //      .objectTypeId(12).reference(1058, bill.getId());
    return em.load(BillItem.class, item);
  }

  public void deleteItem (long id) {
    em.delete(id);
  }

  public void updateItem(BillItem newItem, BillItem item, long id) {
    Bill bill = em.load(Bill.class, item.getItemBill().getId());
    Meeting meeting = em.load(Meeting.class, id);
    bill.setBillStatus("open");
    Double amount_without_item = bill.getCheckCommonAmount()*meeting.getAmountOfParticipants() -
            item.getPrice()*item.getItemAmount();
    bill.setCheckCommonAmount((amount_without_item + newItem.getPrice()*newItem.getItemAmount())
            /meeting.getAmountOfParticipants());
    item.setPrice(newItem.getPrice());
    item.setItemAmount(newItem.getItemAmount());
    item.setItemCurrentAmount(item.getItemCurrentAmount() + newItem.getItemAmount() - item.getItemAmount());
    em.save(bill);
    item.setName(item.getItemTitle());
    em.save(item);
  }

  public void addItem(BillItem item, long id) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(11).reference(1050, id);
    Bill bill = em.filter(Bill.class, query, false).get(0);
    Meeting meeting = em.load(Meeting.class, id);
    bill.setBillStatus("open");
    bill.setCheckCommonAmount(( meeting.getAmountOfParticipants()*bill.getCheckCommonAmount()+
            item.getPrice()*item.getItemAmount())
            /meeting.getAmountOfParticipants());
    item.setItemBill(bill);
    item.setItemCurrentAmount(item.getItemAmount());
    em.save(bill);
    item.setName(item.getItemTitle());
    em.save(item);
  }
}
