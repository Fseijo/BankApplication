package com.fseijo.ms_accounts.service.client;

import com.fseijo.ms_accounts.model.Cards;
import com.fseijo.ms_accounts.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("cards")
public interface CardsFeignClient {
    @RequestMapping(value = "myCards", method = RequestMethod.POST, consumes = "application/json")
    List<Cards> getCardsDetails(@RequestBody Customer customer);
}
