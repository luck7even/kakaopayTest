package com.example.kakaopay.models;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	Payment findPaymentByTid(String tid);
}
