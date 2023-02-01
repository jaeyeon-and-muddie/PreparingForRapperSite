package com.skhu.practice.service;

import com.skhu.practice.dto.PaymentLog;
import com.skhu.practice.dto.PaymentRequestDto;
import com.skhu.practice.entity.Payment;
import com.skhu.practice.entity.Users;
import com.skhu.practice.repository.PaymentRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public void save(PaymentRequestDto paymentRequestDto, String username) {
        Users user = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        user.paymentPoint(paymentRequestDto.getSettlementPrice());
        userRepository.save(user);

        paymentRepository.save(Payment.builder()
                .settlementPrice(paymentRequestDto.getSettlementPrice())
                .methodOfPayment(paymentRequestDto.getMethodOfPayment())
                .user(user)
                .build());
    }

    public List<PaymentLog> findUserLog(String username) {
        return paymentRepository.findByUserId(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new).getId())
                .stream()
                .map(Payment::toPaymentLog)
                .collect(Collectors.toList());
    }
}
