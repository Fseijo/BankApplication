package com.fseijo.ms_accounts.service.client;

import com.fseijo.ms_accounts.model.Cards;
import com.fseijo.ms_accounts.model.Customer;
import com.fseijo.ms_accounts.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("loans")
public interface LoansFeignClient {
    @RequestMapping(value = "myLoans", method = RequestMethod.POST, consumes = "application/json")
    List<Loan> getLoansDetails(@RequestBody Customer customer);
}
