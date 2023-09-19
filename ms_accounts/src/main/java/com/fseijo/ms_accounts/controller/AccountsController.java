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
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;
    private final LoansService loansService;
    private final CardsService cardsService;

    private final AccountsServiceConfig accConfigServ;
    private final Logger LOGGER = LoggerFactory.getLogger(AccountsController.class);
    private static final String ORDER_SERVICE = "detailsForCustomerSupportApp";
    private static final String RETRY_ORDER_SERVICE = "retryForCustomerDetails";
    private static final String RATE_ORDER_SERVICE = "sayHello";
    private static final String ORDER_FALLBACK = "myCustomerDetailsFallBack";
    private static final String RATE_ORDER_FALLBACK = "sayHelloFallBack";



    @RequestMapping(value = "myAccount", method = RequestMethod.POST)
    public List<Accounts> getAccountsByCustomerId(@RequestBody Customer customer) {
        LOGGER.info("Searching all accounts for customer with id: " + customer.getCustomerId());
        return accountsService.getAccountsList(customer);
    }

    @RequestMapping(value = "/accounts/properties", method = RequestMethod.GET)
    public String getPropertiesDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accConfigServ.getMsg(), accConfigServ.getBuildVersion(), accConfigServ.getMailDetails(), accConfigServ.getActiveBranches());
        String jsonStr = objectWriter.writeValueAsString(properties);
        return jsonStr;
    }

//    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = ORDER_FALLBACK)
    @Retry(name = RETRY_ORDER_SERVICE, fallbackMethod = ORDER_FALLBACK)
    @RequestMapping(value = "/myCoustomerDetails", method = RequestMethod.POST)
    public CustomerDetails myCustomerDetails(@RequestBody Customer customer){
        LOGGER.info("Searching all accounts for customer with id: " + customer.getCustomerId());
        List<Accounts> accountsList = accountsService.getAccountsList(customer);
        LOGGER.info("Searching all loans for customer with id: " + customer.getCustomerId());
        List<Loan> loansList = loansService.getLoansList(customer);
        LOGGER.info("Searching all cards for customer with id: " + customer.getCustomerId());
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

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    @RateLimiter(name = RATE_ORDER_SERVICE, fallbackMethod = RATE_ORDER_FALLBACK)
    public String sayHello(){return "Hello, Welcome to BankApplication";}

    private String sayHelloFallBack(Throwable throwable){
        return "Sorry, you've reach max number of request attempts";
    }
}
