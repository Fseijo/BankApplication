package com.fseijo.ms_loans.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_number")
    private int loanNumber;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name="start_dt")
    private Date startDate;
    @Column(name = "loan_type")
    private String loanType;
    @Column(name = "total_loan")
    private int totalLoan;
    @Column(name = "amount_paid")
    private int amountPaid;
    @Column(name = "outstanding_amount")
    private int outstandingAmount;
    @Column(name = "created_dt")
    private String createDate;
}
