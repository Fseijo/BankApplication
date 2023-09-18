package com.fseijo.ms_accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fseijo.ms_accounts.config.AccountsServiceConfig;
import com.fseijo.ms_accounts.model.*;
import com.fseijo.ms_accounts.service.AccountsService;
import com.fseijo.ms_accounts.service.CardsService;
import com.fseijo.ms_accounts.service.LoansService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;
    private final LoansService loansService;
    private final CardsService cardsService;

    private final AccountsServiceConfig accConfigServ;
    private static final String ORDER_SERVICE = "detailsForCustomerSupportApp";
    private static final String ORDER_FALLBACK = "myCustomerDetailsFallBack";


    @RequestMapping(value = "myAccount", method = RequestMethod.POST)
    public List<Accounts> getAccountsByCustomerId(@RequestBody Customer customer) {
        return accountsService.getAccountsList(customer);
    }

    @RequestMapping(value = "/accounts/properties", method = RequestMethod.GET)
    public String getPropertiesDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accConfigServ.getMsg(), accConfigServ.getBuildVersion(), accConfigServ.getMailDetails(), accConfigServ.getActiveBranches());
        String jsonStr = objectWriter.writeValueAsString(properties);
        return jsonStr;
    }

    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = ORDER_FALLBACK)
    @RequestMapping(value = "/myCoustomerDetails", method = RequestMethod.POST)
    public CustomerDetails myCustomerDetails(@RequestBody Customer customer){
        List<Accounts> accountsList = accountsService.getAccountsList(customer);
        List<Loan> loansList = loansService.getLoansList(customer);
        List<Cards> cardsList = cardsService.getCardsList(customer);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accountsList);
        customerDetails.setLoans(loansList);
        customerDetails.setCards(cardsList);

        return customerDetails;
    }

    private CustomerDetails myCustomerDetailsFallBack(Customer customer, Throwable throwable){
        List<Accounts> accountsList = accountsService.getAccountsList(customer);
        List<Loan> loansList = loansService.getLoansList(customer);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(accountsList);
        customerDetails.setLoans(loansList);
        return customerDetails;
    }
}
