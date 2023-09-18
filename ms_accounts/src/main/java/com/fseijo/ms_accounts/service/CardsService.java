package com.fseijo.ms_accounts.service;

import com.fseijo.ms_accounts.model.Cards;
import com.fseijo.ms_accounts.model.Customer;
import com.fseijo.ms_accounts.service.client.CardsFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardsService {

    private final CardsFeignClient cardsFeignClient;
    public List<Cards> getCardsList(Customer customer) {
        return cardsFeignClient.getCardsDetails(customer);
    }
}
