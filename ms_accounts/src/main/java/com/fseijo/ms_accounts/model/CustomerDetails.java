package com.fseijo.ms_accounts.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDetails {
    private List<Accounts> accounts;
    private List<Loan> loans;
    private List<Cards> cards;
}
