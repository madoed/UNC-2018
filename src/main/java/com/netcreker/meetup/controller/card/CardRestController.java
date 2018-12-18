package com.netcreker.meetup.controller.card;

import com.netcreker.meetup.entity.card.Card;
import com.netcreker.meetup.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/{id}/card")
public class CardRestController {
  @Autowired
  private CardService cardService;

  @GetMapping()
  public ArrayList<Card> getСard(@PathVariable Long id) {
    return cardService.getMyCards(id);
  }

  @PostMapping()
  @ResponseStatus(CREATED)
  public Card addCard(@PathVariable Long id,@RequestBody Card newCard) {
    return cardService.addCard(id,newCard);
  }
}
