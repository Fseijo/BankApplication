package com.seijo.ms_accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.seijo.ms_accounts.config.AccountsServiceConfig;
import com.seijo.ms_accounts.model.Accounts;
import com.seijo.ms_accounts.model.Customer;
import com.seijo.ms_accounts.model.Properties;
import com.seijo.ms_accounts.repositories.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountsController {
    @Autowired
    private final AccountsRepository accountsRepository;
    @Autowired
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
