package com.seijo.ms_accounts.controller;

import com.seijo.ms_accounts.model.Accounts;
import com.seijo.ms_accounts.model.Customer;
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

    @RequestMapping(value = "myAccount", method = RequestMethod.POST)
    public List<Accounts> getAccountsByCustomerId(@RequestBody Customer customer){
        return accountsRepository.findAllByCustomerId(customer.getCustomerId());
    }

}
