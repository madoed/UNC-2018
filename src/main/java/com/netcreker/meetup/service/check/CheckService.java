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
    List<Check> checks = em.filter(Check.class, query, false);

    List<ItemAmount> items = new ArrayList<>();
    List<ItemAmount> itemsFromOneCheck;
    for (Check check:checks) {
      query = ObjectQuery.newInstance()
              .objectTypeId(13).reference(1060, check.getId());
      itemsFromOneCheck = em.filter(ItemAmount.class, query, false);
      if (itemsFromOneCheck!=null){
        for (ItemAmount a: itemsFromOneCheck) {
          items.add(a);
        }
      }
    }

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
    Integer current;
    Double billCommonAmountBefore = bill.getBillCommonAmount();
    if (newItem.getItemCurrentAmount() == 0) {//if changing price, amount
      current = item.getItemCurrentAmount() + (newItem.getItemAmount() - item.getItemAmount());
      newItem.setItemCurrentAmount(current);

      Double amount_without_item = bill.getBillCommonAmount()*meeting.getAmountOfParticipants() -
              item.getPrice()*item.getItemCurrentAmount();
      Double common = (amount_without_item + newItem.getPrice()*newItem.getItemCurrentAmount())
              /meeting.getAmountOfParticipants();
      bill.setBillCommonAmount(common);
      Double total = bill.getBillTotalSum() - item.getPrice()*item.getItemAmount() +
              newItem.getPrice()*newItem.getItemAmount();
      bill.setBillTotalSum(total);

      item.setItemCurrentAmount(current);
      item.setItemAmount(newItem.getItemAmount());
    }
    else {//if move to list
      current = item.getItemCurrentAmount() + newItem.getItemCurrentAmount();
      //item.setItemCurrentAmount(current);
      newItem.setItemCurrentAmount(current);

      Double amount_without_item = bill.getBillCommonAmount()*meeting.getAmountOfParticipants() -
              item.getPrice()*item.getItemCurrentAmount();
      Double common = (amount_without_item + newItem.getPrice()*newItem.getItemCurrentAmount())
              /meeting.getAmountOfParticipants();
      bill.setBillCommonAmount(common);
      item.setItemCurrentAmount(current);
    }

    item.setPrice(newItem.getPrice());
    item.setItemTitle(newItem.getItemTitle());

    createCheckIfNoNotPayedCheck(id, (billCommonAmountBefore-bill.getBillCommonAmount()));
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

  private void createCheckIfNoNotPayedCheck (long meetingId, Double commonAmountChange) {

    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(11).reference(1050, meetingId);
    Bill bill = em.filter(Bill.class, query, false).get(0);

    query = ObjectQuery.newInstance()
            .objectTypeId(10).reference(1048, meetingId);
    List<Participant> participants = em.filter(Participant.class, query, false);

    Check check;
    for (Participant participant:participants) {
      query = ObjectQuery.newInstance()
              .objectTypeId(14).reference(1062, participant.getId())
              .objectTypeId(14).value(1065, "notpayed");
      if (em.filter(Check.class, query, false)==null){
        check = new Check();
        check.setCheckAmount(0.0);
        check.setCheckStatus("notpayed");
        check.setCheckOwner(participant);
        check.setName(bill.getBillOwner().getName());
        em.save(check);
      }
    }
  }

  public BillItem addItem(BillItem item, long id) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(11).reference(1050, id);
    Bill bill = em.filter(Bill.class, query, false).get(0);
    Meeting meeting = em.load(Meeting.class, id);
    if(bill.getBillStatus().equals("empty"))
      createChecks(id);

    Double billCommonAmountBefore = bill.getBillCommonAmount();
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

    createCheckIfNoNotPayedCheck(id, (billCommonAmountBefore-bill.getBillCommonAmount()));
    return item;
  }

  public List<ItemAmount> checkUpdate(List<BillItem> items, long participantId) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(14).reference(1062, participantId)
            .objectTypeId(14).value(1065, "notpayed");
    List<Check> checks = em.filter(Check.class, query, false);
    if (checks==null) {
      createCheckIfNoNotPayedCheck(participantId, 0.0);
    }
    Check check = em.filter(Check.class, query, false).get(0);
    check.setCheckStatus("notpayed");

    List<ItemAmount> newList = new ArrayList<>();
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
        //check.setCheckAmount(check.getCheckAmount()+item.getPrice()*item.getItemCurrentAmount());
        em.save(check);
        em.save(itemAmount);
        newList.add(itemAmount);
      }
      else {
        itemAmount = billItem.get(0);
//        check.setCheckAmount(check.getCheckAmount()-
//                item.getPrice()*itemAmount.getAmountInCheck()
//                +item.getPrice()*item.getItemCurrentAmount());
        em.save(check);
        itemAmount.setAmountInCheck(item.getItemCurrentAmount());
        em.save(itemAmount);
        newList.add(itemAmount);
      }
    }
    return newList;
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
      query = ObjectQuery.newInstance()
              .objectTypeId(11).reference(1050, part.getParticipantOfMeeting()
                      .getId());
      bill = em.filter(Bill.class, query, false).get(0);
      if (bill.getBillOwner().getId() != id) {
        query = ObjectQuery.newInstance()
                .objectTypeId(14).reference(1062, part.getId())
                .value(1065, status);
        check = em.filter(Check.class, query, false);
        if (!check.isEmpty()) {
          for (Check singleCheck : check) {
            if (singleCheck.getCheckAmount() != 0.0)
              checks.add(singleCheck);
          }
        }
      }
    }
    return checks;
  }


  private List<Check> getParticipantChecks (List<Participant> participation, String status,long id) {
   ObjectQuery query;
    List<Check> check;
    List<Check> checks = new ArrayList<Check>();
    for (Participant part:participation) {
      if(part.getMeetingParticipant().getId()!=id) {
        query = ObjectQuery.newInstance()
                .objectTypeId(14).reference(1062, part.getId())
                .value(1065, status);
        check = em.filter(Check.class, query, false);
        if (!check.isEmpty()) {
          for (Check singleCheck:check) {
//            query = ObjectQuery.newInstance()
//                    .objectTypeId(11).reference(1050, part.getParticipantOfMeeting().getId());
//            bill = em.filter(Bill.class, query, false).get(0);
//            singleCheck.setCheckAmount(singleCheck.getCheckAmount() + bill.getBillCommonAmount());
            singleCheck = updateCheckAmount(singleCheck.getId());
            if (singleCheck.getCheckAmount()!=0.0)
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

//        for (Participant participant : participation) {
//          if (participant.getMeetingParticipant().getId() != id) {
//            query = ObjectQuery.newInstance()
//                    .objectTypeId(14).reference(1062, participant.getId())
//                    .value(1065, status);
//          check = em.filter(Check.class, query, false);
//          if (!check.isEmpty()) {
//            for (Check singleCheck : check) {
//              singleCheck = updateCheckAmount(singleCheck.getId());
//              if (singleCheck.getCheckAmount()!=0.0)
//                checks.add(singleCheck);
////              singleCheck.setCheckAmount(singleCheck.getCheckAmount() + bill.getBillCommonAmount());
////              checks.add(singleCheck);
//            }
//          }
//          }
//        }
      check = getParticipantChecks(participation, status, id);
      for (Check a:check) {
        checks.add(a);
      }
    }
    return checks;
  }

  private Check updateCheckAmount(long checkId) {
    Check check = em.load(Check.class, checkId);
    Participant participant = check.getCheckOwner();
    Meeting meeting = em.load(Meeting.class, participant.getParticipantOfMeeting().getId());
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(11).reference(1050, meeting.getId());
    Bill bill = em.filter(Bill.class, query, false).get(0);
    Double amount = bill.getBillCommonAmount();
    List<ItemAmount> itemsInCheck = getItems(checkId);
    for (ItemAmount itemAmount: itemsInCheck) {
      amount += itemAmount.getBillItemAmount().getPrice()*itemAmount.getAmountInCheck();
    }
    check.setCheckAmount(amount);
    em.save(check);
    return check;
  }

  public void confirmPayment(long checkId) {
    Check check = updateCheckAmount(checkId);
    Participant participant = check.getCheckOwner();

    participant.setAlreadyPayed(participant.getAlreadyPayed() + check.getCheckAmount());
    check.setCheckStatus("payed");
    em.save(check);
  }

  public List<ItemAmount> getItems (long id) {
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(13).reference(1060, id);
    List<ItemAmount> items = em.filter(ItemAmount.class, query, false);
    return items;
  }

  public Check getCheck (long id) {
    Check check = em.load(Check.class, id);
    Participant participant = em.load(Participant.class, check.getCheckOwner().getId());
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(11).reference(1050, participant.getParticipantOfMeeting().getId());
    Bill bill = em.filter(Bill.class, query, false).get(0);
    //check.setCheckAmount(check.getCheckAmount() + bill.getBillCommonAmount());
    check = updateCheckAmount(check.getId());
    return check;
  }

  public boolean checkForAlreadyPayedParticipants (long billId) {
    Bill bill = em.load(Bill.class, billId);
    ObjectQuery query = ObjectQuery.newInstance()
            .objectTypeId(10).reference(1048, bill.getBillOfMeeting().getId());
    List<Participant> participants = em.filter(Participant.class, query, false);
    for (Participant participant: participants) {
      if(participant.getAlreadyPayed()!=0.0)
        return true;
    }
    return false;
  }
}
