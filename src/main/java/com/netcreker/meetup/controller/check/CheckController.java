package com.netcreker.meetup.controller.check;

import com.netcreker.meetup.entity.chat.Message;
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
    if(item.getItemAmount() < (billItem.getItemAmount() - billItem.getItemCurrentAmount()))
      return new ResponseEntity<BillItem>(HttpStatus.CONFLICT);
    checkService.updateItem(item, billItem, id);
    return new ResponseEntity<BillItem>(item, HttpStatus.OK);
  }

  @PostMapping(value = "/item-add/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>addItem(@RequestBody BillItem item, @PathVariable long id) {
    checkService.addItem(item, id);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<BillItem>(item, HttpStatus.OK);
  }

  //если current!=amount то не удаляем - ошибка
  @DeleteMapping(value = "/item-delete/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?> deleteItem(@PathVariable long id) {
    BillItem billItem = checkService.loadItem(id);
    if (billItem==null) {
      return new ResponseEntity<BillItem>(HttpStatus.NOT_FOUND);
    }
    checkService.deleteItem(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
