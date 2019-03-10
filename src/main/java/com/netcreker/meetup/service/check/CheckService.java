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

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckService {

  @Autowired
  private EntityManager em;

  public Bill findCheckByMeting(long meetingId){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(11).reference(1050, meetingId);
    return em.filter(Bill.class, query, false).get(0);
  }

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

  public void deleteChecks (long meetingId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(10).reference(1048, meetingId);
    List<Participant> participants = em.filter(Participant.class, query, false);
    Check check;
    for (Participant participant:
            participants) {
      query = ObjectQuery.newInstance()
              .objectTypeId(14).reference(1062, participant.getId());
      check = em.filter(Check.class, query, false).get(0);
      em.delete(check.getId());
    }
  }

  public void deleteItem (BillItem billItem) {
    Bill bill = em.load(Bill.class, billItem.getItemBill().getId());
    bill.setBillTotalSum(billItem.getPrice()*billItem.getItemAmount());
    bill.setBillCommonAmount((bill.getBillCommonAmount()*bill.getBillOfMeeting().getAmountOfParticipants() -
            billItem.getPrice())/bill.getBillOfMeeting().getAmountOfParticipants());
    if(bill.getBillTotalSum()==0.0) {
      String status = "closed";
      bill.setBillStatus(status);
      deleteChecks(bill.getBillOfMeeting().getId());
    }
    em.save(bill);
    em.delete(billItem.getId());
  }

  public BillItem updateItem(BillItem newItem, BillItem item, long id) {
    Bill bill = em.load(Bill.class, item.getItemBill().getId());
    Meeting meeting = em.load(Meeting.class, id);
    String status = "open";
    bill.setBillStatus(status);
    Double amount_without_item = bill.getBillCommonAmount()*meeting.getAmountOfParticipants() -
            item.getPrice()*item.getItemAmount();
    Double common = (amount_without_item + newItem.getPrice()*newItem.getItemAmount())
            /meeting.getAmountOfParticipants();
    bill.setBillCommonAmount(common);
    Double total = bill.getBillTotalSum() - item.getPrice()*item.getItemAmount() +
            newItem.getPrice()*newItem.getItemAmount();
    bill.setBillTotalSum(total);
    item.setPrice(newItem.getPrice());
    item.setItemTitle(newItem.getItemTitle());
    Integer current;
    if (newItem.getItemCurrentAmount() == 0) {
      current = newItem.getItemAmount() - (item.getItemAmount()-item.getItemCurrentAmount()) ;
      item.setItemAmount(newItem.getItemAmount());
    }
    else {
      current = item.getItemCurrentAmount() + newItem.getItemCurrentAmount();
    }
    item.setItemCurrentAmount(current);
    em.save(bill);
    item.setName(item.getItemTitle());
    em.save(item);
    return item;
  }

  public void  createChecks (long meetingId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(10).reference(1048, meetingId);
    List<Participant> participants = em.filter(Participant.class, query, false);
    Check check;
    Meeting meeting = em.load(Meeting.class, meetingId);
    for (Participant participant: participants) {
      check = new Check();
      check.setCheckAmount(0.0);
      check.setCheckStatus("notpayed");
      check.setCheckOwner(participant);
      check.setName(meeting.getBoss().getName());
      em.save(check);
    }
  }

  public BillItem addItem(BillItem item, long id) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(11).reference(1050, id);
    Bill bill = em.filter(Bill.class, query, false).get(0);
    Meeting meeting = em.load(Meeting.class, id);
    if(bill.getBillStatus().equals("empty"))
      createChecks(id);
    String status = "open";
    bill.setBillStatus(status);
    bill.setBillTotalSum(bill.getBillTotalSum() + item.getPrice()*item.getItemAmount());
    bill.setBillCommonAmount(( meeting.getAmountOfParticipants()*bill.getBillCommonAmount()+
            item.getPrice()*item.getItemAmount())
            /meeting.getAmountOfParticipants());
    item.setItemBill(bill);
    item.setItemCurrentAmount(item.getItemAmount());
    em.save(bill);
    item.setName(item.getItemTitle());
    em.save(item);
    return item;
  }

  public void checkUpdate(List<BillItem> items, long participantId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(14).reference(1062, participantId)
            .objectTypeId(14).value(1065, "notpayed");
    Check check = em.filter(Check.class, query, false).get(0);
    check.setCheckStatus("notpayed");
    List<ItemAmount> billItem;
    ItemAmount itemAmount;
    for (BillItem item: items) {
      check.setCheckStatus("notpayed");
      query = ObjectQuery.newInstance()
              .objectTypeId(13).reference(1060, check.getId()).
                      objectTypeId(13).reference(1059, item.getId());
      billItem = em.filter(ItemAmount.class, query, false);
      if (billItem.isEmpty()) {

        itemAmount = new ItemAmount();
        itemAmount.setName(item.getItemTitle());
        itemAmount.setBillItemAmount(item);
        itemAmount.setAmountInCheck(item.getItemCurrentAmount());
        itemAmount.setItemAmountCheck(check);
        itemAmount.setName(item.getItemTitle() +" " + check.getCheckOwner().getName());
        check.setCheckAmount(check.getCheckAmount()+item.getPrice()*item.getItemCurrentAmount());
        em.save(check);
        em.save(itemAmount);
      }
      else {
        itemAmount = billItem.get(0);
        check.setCheckAmount(check.getCheckAmount()-
                item.getPrice()*itemAmount.getAmountInCheck()
                +item.getPrice()*item.getItemCurrentAmount());
        em.save(check);
        itemAmount.setAmountInCheck(item.getItemCurrentAmount());
        em.save(itemAmount);
      }
    }
  }

  public void deleteItemFromCheck(long participantId, long itemId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(14).reference(1062, participantId);
    Check check = em.filter(Check.class, query, false).get(0);
    query = ObjectQuery.newInstance()
            .objectTypeId(13).reference(1060, check.getId()).
                    objectTypeId(13).reference(1059, itemId);
    ItemAmount itemAmount = em.filter(ItemAmount.class, query, false).get(0);
    check.setCheckAmount(check.getCheckAmount()-itemAmount.getAmountInCheck()*
            itemAmount.getBillItemAmount().getPrice());
    em.save(check);
    em.delete(itemAmount.getId());
  }

  public List<Check> getChecks(Long id, String status){
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(10).reference(1046, id);
    List<Participant> participation = em.filter(Participant.class, query, false);

    List<Check> checks = new ArrayList<>();
    List<Check> check;
    Bill bill = new Bill();

    for (Participant part:participation) {
      if(part.getMeetingParticipant().getId()!=id) {
        query = ObjectQuery.newInstance()
                .objectTypeId(14).reference(1062, part.getId())
                .value(1065, status);
        check = em.filter(Check.class, query, false);
        if (!check.isEmpty()) {
          for (Check singleCheck:check) {
            query = ObjectQuery.newInstance()
                    .objectTypeId(11).reference(1050, part.getParticipantOfMeeting().getId());
            bill = em.filter(Bill.class, query, false).get(0);
            singleCheck.setCheckAmount(singleCheck.getCheckAmount() + bill.getBillCommonAmount());
            checks.add(singleCheck);
          }
        }
      }
    }
    return checks;
  }

  public List<Check> getOwedChecks(Long id, String status){
    ObjectQuery query;
    query = ObjectQuery.newInstance()
            .objectTypeId(11).reference(1049, id)
            .value(1052, "open");
    List<Bill> bills = em.filter(Bill.class, query, false);
//    if(status.equals("payed")) {
//      query = ObjectQuery.newInstance()
//              .objectTypeId(11).reference(1049, id)
//              .value(1052, "open");
//    }
//    else {
//      query = ObjectQuery.newInstance()
//              .objectTypeId(11).reference(1049, id)
//              .value(1052, "closed");
//    }


    List<Check> checks = new ArrayList<>();
    List<Check> check;
    List<Participant> participation;


    //get all bills where i'm owed
    for (Bill bill: bills) {

        query = ObjectQuery.newInstance()
                .objectTypeId(10).reference(1048, bill.getBillOfMeeting().getId());
        participation = em.filter(Participant.class, query, false);

        for (Participant participant : participation) {
          if (participant.getMeetingParticipant().getId() != id) {
            query = ObjectQuery.newInstance()
                    .objectTypeId(14).reference(1062, participant.getId())
                    .value(1065, status);
          check = em.filter(Check.class, query, false);
          if (!check.isEmpty()) {
            for (Check singleCheck : check) {
              singleCheck.setCheckAmount(singleCheck.getCheckAmount() + bill.getBillCommonAmount());
              checks.add(singleCheck);
            }
          }
          }
        }
    }
    return checks;
  }

  public void confirmPayment(long checkId) {
    Check check = em.load(Check.class, checkId);
    check.setCheckStatus("payed");
    em.save(check);
  }
}
