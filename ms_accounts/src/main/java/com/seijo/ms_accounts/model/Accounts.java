package com.seijo.ms_accounts.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter @ToString
public class Accounts {
    @Column(name = "customer_id")
    private int customerId;
    @Id
    @Column(name = "account_number")
    private long accountNumber;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "branch_address")
    private String branchAddress;
    @Column(name = "created_dt")
    private LocalDate createdDate;
}
