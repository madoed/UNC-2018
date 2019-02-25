package com.netcreker.meetup.controller.card;

import com.netcreker.meetup.entity.card.Card;
import com.netcreker.meetup.entity.meeting.Meeting;
import com.netcreker.meetup.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CardRestController {
  @Autowired
  private CardService cardService;

  @GetMapping("/card-list/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<Card>> getCards(@PathVariable long id) {
    List<Card> cards = cardService.getAllCards(id);
    if(cards.isEmpty()){
      return new ResponseEntity<List<Card>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Card>>(cards, HttpStatus.OK);
  }

  @GetMapping("/card/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>  getCard(@PathVariable long id) {
    Card card = cardService.getCard(id);
    if(card == null){
      return new ResponseEntity<Card>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Card>(card, HttpStatus.OK);
  }

  @PostMapping("/card")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<?>  addCard(@RequestBody Card newCard) {
    cardService.addCard(newCard);
    //HttpHeaders headers = new HttpHeaders();
    //headers.setLocation(ucBuilder.path("/card/{id}").buildAndExpand(newCard.getId()).toUri());
    return new ResponseEntity<String>(HttpStatus.CREATED);
  }

}
