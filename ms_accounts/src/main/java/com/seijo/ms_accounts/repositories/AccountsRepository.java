package com.seijo.ms_accounts.repositories;

import com.seijo.ms_accounts.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer>, JpaSpecificationExecutor<Accounts> {

    /**
     * Getting all accounts by customer id using JpaRepository
     * @param customerId
     * @return a list of accounts
     */
    List<Accounts> findAllByCustomerId(int customerId);

    /**
     * Getting all accounts by customer id using SQL
     * @param customerId
     * @return a list of accounts
     */

    @Query(value = "select * from accounts where customer_id = :customerId",nativeQuery = true)
    public List<Accounts>  getListAccountByCustomerId(Integer customerId);
}
