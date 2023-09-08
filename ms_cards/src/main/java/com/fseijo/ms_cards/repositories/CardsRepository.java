package com.fseijo.ms_cards.repositories;

import com.fseijo.ms_cards.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Integer>, JpaSpecificationExecutor<Cards> {

    /**
     * Getting all cards by customer id using JpaRepository
     *
     * @param customerId
     * @return a list of accounts
     */
    List<Cards> findAllByCustomerId(int customerId);

    /**
     * Getting all cards by customer id using SQL
     * @param customerId
     * @return a list of accounts
     */

    @Query(value = "select * from cards where customer_id = :customerId",nativeQuery = true)
    public List<Cards>  getListCardsByCustomerId(Integer customerId);
}
