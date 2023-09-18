package com.fseijo.ms_accounts.service;

import com.fseijo.ms_accounts.model.Accounts;
import com.fseijo.ms_accounts.model.Customer;
import com.fseijo.ms_accounts.repositories.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;
    public List<Accounts> getAccountsList(Customer customer) {
        return accountsRepository.findAllByCustomerId(customer.getCustomerId());
    }
}
