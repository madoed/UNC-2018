package com.netcreker.meetup.controller.card;

import com.netcreker.meetup.entity.card.Card;
import com.netcreker.meetup.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/{id}/card")
public class CardRestController {
  @Autowired
  private CardService cardService;

  @RequestMapping(method = GET)
  public ArrayList<Card> getСard(@PathVariable Long id) {
    return cardService.getMyCards(id);
  }

  @RequestMapping(method = POST)
  @ResponseStatus(CREATED)
  public void addCard(@PathVariable Long id,@RequestBody Card newCard) {
    cardService.addCard(id,newCard);
  }
}
