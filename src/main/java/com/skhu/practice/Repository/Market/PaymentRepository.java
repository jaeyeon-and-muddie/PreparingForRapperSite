package com.skhu.practice.Repository.Market;

import com.skhu.practice.Entity.market.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
