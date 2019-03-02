package com.netcreker.meetup.controller.check;

import com.netcreker.meetup.entity.chat.Message;
import com.netcreker.meetup.entity.check.BillItem;
import com.netcreker.meetup.entity.check.Check;
import com.netcreker.meetup.entity.check.ItemAmount;
import com.netcreker.meetup.service.check.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
