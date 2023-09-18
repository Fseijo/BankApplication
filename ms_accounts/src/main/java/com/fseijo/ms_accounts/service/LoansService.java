package com.fseijo.ms_accounts.service;

import com.fseijo.ms_accounts.model.Customer;
import com.fseijo.ms_accounts.model.Loan;
import com.fseijo.ms_accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoansService {
    private final LoansFeignClient loansFeignClient;
    public List<Loan> getLoansList(Customer customer) {
        return loansFeignClient.getLoansDetails(customer);
    }
}
