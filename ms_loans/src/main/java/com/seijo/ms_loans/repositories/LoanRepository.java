package com.seijo.ms_loans.repositories;

import com.seijo.ms_loans.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer>, JpaSpecificationExecutor<Loan> {

    /**
     * Getting all loans by customer id using JpaRepository
     * @param customerId
     * @return a list of accounts
     */
    List<Loan> findAllByCustomerId(int customerId);

    /**
     * Getting all loans by customer id using SQL
     * @param customerId
     * @return a list of accounts
     */
    @Query(value = "select * from loans where customer_id = :customerId",nativeQuery = true)
    public List<Loan>  getListLoansByCustomerId(Integer customerId);
}
