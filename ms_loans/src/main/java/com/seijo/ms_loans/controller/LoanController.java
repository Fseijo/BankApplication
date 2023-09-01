package com.seijo.ms_loans.controller;

import com.seijo.ms_loans.model.Customer;
import com.seijo.ms_loans.model.Loan;
import com.seijo.ms_loans.repositories.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanRepository loanRepository;

    @RequestMapping(value = "myLoans", method = RequestMethod.POST)
    public List<Loan> getLoansByCustomerId(@RequestBody Customer customer) {
        return loanRepository.findAllByCustomerId(customer.getCustomerId());
//        return loanRepository.getListLoansByCustomerId(customer.getCustomerId());
    }
}
