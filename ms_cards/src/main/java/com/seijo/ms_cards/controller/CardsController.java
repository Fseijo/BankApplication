package com.seijo.ms_cards.controller;

import com.seijo.ms_cards.model.Cards;
import com.seijo.ms_cards.model.Customer;
import com.seijo.ms_cards.repositories.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;

    @RequestMapping(value = "myCards", method = RequestMethod.POST)
    public List<Cards> getCardsByCustomerId(@RequestBody Customer customer){
        return cardsRepository.findAllByCustomerId(customer.getCustomerId());
//        return cardsRepository.getListCardsByCustomerId(customer.getCustomerId());
    }
}
