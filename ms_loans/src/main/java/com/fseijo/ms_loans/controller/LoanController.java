package com.fseijo.ms_loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fseijo.ms_loans.config.LoansServiceConfig;
import com.fseijo.ms_loans.model.Customer;
import com.fseijo.ms_loans.model.Loan;
import com.fseijo.ms_loans.repositories.LoanRepository;
import com.fseijo.ms_loans.model.Properties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {

    private final LoanRepository loanRepository;
    private final LoansServiceConfig loansServiceConfig;
    private final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);
    @RequestMapping(value = "myLoans", method = RequestMethod.POST)
    public List<Loan> getLoansByCustomerId(@RequestBody Customer customer) {
        LOGGER.info("Searching all loans for customer with id: " + customer.getCustomerId());
        return loanRepository.findAllByCustomerId(customer.getCustomerId());
//        return loanRepository.getListLoansByCustomerId(customer.getCustomerId());
    }

    @RequestMapping(value = "/loans/properties", method = RequestMethod.GET)
    public String getPropertiesDetails() throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loansServiceConfig.getMsg(), loansServiceConfig.getBuildVersion(), loansServiceConfig.getMailDetails(), loansServiceConfig.getActiveBranches());
        String jsonStr = objectWriter.writeValueAsString(properties);
        return jsonStr;
    }
}
