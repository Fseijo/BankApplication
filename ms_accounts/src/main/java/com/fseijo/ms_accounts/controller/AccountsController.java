package com.fseijo.ms_accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fseijo.ms_accounts.config.AccountsServiceConfig;
import com.fseijo.ms_accounts.model.Accounts;
import com.fseijo.ms_accounts.model.Customer;
import com.fseijo.ms_accounts.repositories.AccountsRepository;
import com.fseijo.ms_accounts.model.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsRepository accountsRepository;

    private final AccountsServiceConfig accConfigServ;


    @RequestMapping(value = "myAccount", method = RequestMethod.POST)
    public List<Accounts> getAccountsByCustomerId(@RequestBody Customer customer) {
        return accountsRepository.findAllByCustomerId(customer.getCustomerId());
    }

    @RequestMapping(value = "/accounts/properties", method = RequestMethod.GET)
    public String getPropertiesDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accConfigServ.getMsg(), accConfigServ.getBuildVersion(), accConfigServ.getMailDetails(), accConfigServ.getActiveBranches());
        String jsonStr = objectWriter.writeValueAsString(properties);
        return jsonStr;
    }
}
