package com.seijo.ms_cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.seijo.ms_cards.config.CardsServiceConfig;
import com.seijo.ms_cards.model.Cards;
import com.seijo.ms_cards.model.Customer;
import com.seijo.ms_cards.model.Properties;
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
    private final CardsServiceConfig cardsServiceConfig;

    @RequestMapping(value = "myCards", method = RequestMethod.POST)
    public List<Cards> getCardsByCustomerId(@RequestBody Customer customer){
        return cardsRepository.findAllByCustomerId(customer.getCustomerId());
//        return cardsRepository.getListCardsByCustomerId(customer.getCustomerId());
    }

    @RequestMapping(value ="/cards/properties", method = RequestMethod.GET)
    public String getPropertiesDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsServiceConfig.getMsg(), cardsServiceConfig.getBuildVersion(), cardsServiceConfig.getMailDetails(), cardsServiceConfig.getActiveBranches());
        String jsonStr = objectWriter.writeValueAsString(properties);
        return jsonStr;
    }
}
