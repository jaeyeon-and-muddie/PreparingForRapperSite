package com.skhu.practice.service;

import com.skhu.practice.dto.PurchasesResponseDto;
import com.skhu.practice.entity.Purchases;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchasesService {

    private final UserRepository userRepository;

    public List<PurchasesResponseDto> findAllByUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(NoSuchElementException::new)
                .getMyPurchases()
                .stream()
                .map(Purchases::toResponseDto)
                .collect(Collectors.toList());
    }
}
