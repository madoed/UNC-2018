package com.netcreker.meetup.controller.card;

import com.netcreker.meetup.entity.card.Card;
import com.netcreker.meetup.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CardRestController {
  @Autowired
  private CardService cardService;


  @GetMapping("/card")
  @CrossOrigin(origins = "http://localhost:4200")
  public ArrayList<Card> getAllСards() {
    return cardService.getAllCards();
  }

  @GetMapping("/card/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ArrayList<Card> getСards(@PathVariable long id) {
    return cardService.getCard(id);
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
