package com.skhu.practice.service;

import com.skhu.practice.dto.SalesResponseDto;
import com.skhu.practice.entity.Sales;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final UserRepository userRepository;

    public List<SalesResponseDto> findAllByUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new)
                .getMySales()
                .stream()
                .map(Sales::toResponseDto)
                .collect(Collectors.toList());
    }
}
