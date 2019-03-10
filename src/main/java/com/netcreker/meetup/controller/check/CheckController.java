package com.netcreker.meetup.controller.check;

import com.netcreker.meetup.entity.chat.Message;
import com.netcreker.meetup.entity.check.Bill;
import com.netcreker.meetup.entity.check.BillItem;
import com.netcreker.meetup.entity.check.Check;
import com.netcreker.meetup.entity.check.ItemAmount;
import com.netcreker.meetup.service.check.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CheckController {

  @Autowired
  private CheckService checkService;

  @GetMapping(value = "/bill-items/{meetingId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<BillItem>> findBillItems(@PathVariable("meetingId") long meetingId) {
    List<BillItem> items = checkService.findAllBillItems(meetingId);
    if(items==null){
      return new ResponseEntity<List<BillItem>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<BillItem>>(items, HttpStatus.OK);
  }

  @GetMapping(value = "/bill/{meetingId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Bill> findCheckByMeting(@PathVariable("meetingId") long meetingId) {
    Bill bill = checkService.findCheckByMeting(meetingId);
    return new ResponseEntity<Bill>(bill, HttpStatus.OK);
  }

  @GetMapping(value = "/participant-items/{participantId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<ItemAmount>> findParticipantItems(@PathVariable("participantId") long participantId) {
    List<ItemAmount> items = checkService.findAllParticipantItems(participantId);
    if(items==null){
      return new ResponseEntity<List<ItemAmount>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<ItemAmount>>(items, HttpStatus.OK);
  }

  @PutMapping(value = "/item-update/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?> updateItem(@PathVariable long id, @RequestBody BillItem item) {
    BillItem billItem = checkService.loadItem(item.getId());
    if (billItem==null) {
      return new ResponseEntity<BillItem>(HttpStatus.NOT_FOUND);
    }
    //if(item.getItemAmount() < (billItem.getItemAmount() - billItem.getItemCurrentAmount()))
    //  return new ResponseEntity<BillItem>(HttpStatus.CONFLICT);
    if (item.getItemCurrentAmount()!=0&&(billItem.getItemAmount() + item.getItemCurrentAmount()<0)) {
      return new ResponseEntity<BillItem>(billItem, HttpStatus.CONFLICT);
    }
    item = checkService.updateItem(item, billItem, id);
    return new ResponseEntity<BillItem>(item, HttpStatus.OK);
  }

  @PostMapping(value = "/item-add/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>addItem(@RequestBody BillItem item, @PathVariable long id) {
    item = checkService.addItem(item, id);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<BillItem>(item, HttpStatus.OK);
  }

  @DeleteMapping(value = "/item-delete/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?> deleteItemFromBill(@PathVariable long id) {
    BillItem billItem = checkService.loadItem(id);
    if (billItem==null) {
      return new ResponseEntity<BillItem>(HttpStatus.NOT_FOUND);
    }
    if (billItem.getItemCurrentAmount() != billItem.getItemAmount())
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    checkService.deleteItem(billItem);
      return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(value = "/check-items-update/{participantId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>checkUpdate(@RequestBody List<BillItem> items, @PathVariable long participantId) {
    checkService.checkUpdate(items, participantId);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<>( HttpStatus.OK);
  }

  @DeleteMapping(value = "/check-delete-item/{participantId}/{itemId}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?> deleteItemFromCheck(@PathVariable long participantId, @PathVariable long itemId) {
    checkService.deleteItemFromCheck(participantId, itemId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/check-list/{id}/{status}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<Check>> getAllChecks(@PathVariable long id, @PathVariable String status) {
    List<Check> checks = checkService.getChecks(id, status);
    if(checks.isEmpty()){
      return new ResponseEntity<List<Check>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Check>>(checks, HttpStatus.OK);
  }

  @GetMapping("/check-owed-list/{id}/{status}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<Check>> getOwedChecks(@PathVariable long id, @PathVariable String status) {
    List<Check> checks = checkService.getOwedChecks(id, status);
    if(checks.isEmpty()){
      return new ResponseEntity<List<Check>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Check>>(checks, HttpStatus.OK);
  }

  @PostMapping("/payment-confirm")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>confirmParticipation(@RequestBody long checkId) {
    checkService.confirmPayment(checkId);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.OK);
  }
}
