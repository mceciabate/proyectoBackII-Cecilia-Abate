package com.msbills.repositories;

import com.msbills.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {

  Optional<Bill> findByCustomerBill(String customer);
}
