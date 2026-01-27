package org.moufid.billingservice.repository;

import org.moufid.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository  extends JpaRepository<Bill, Long> {
    List<Bill> findByCustomerId(long customerId);
}
