package com.skhu.practice.service;

import com.skhu.practice.dto.ProductRequestDto;
import com.skhu.practice.dto.ProductResponseDto;
import com.skhu.practice.entity.Product;
import com.skhu.practice.repository.ProductRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<ProductResponseDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(Product::toResponseDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(NoSuchElementException::new)
                .toResponseDto();
    }

    public void save(ProductRequestDto productRequestDto, String username) {
        productRepository.save(Product.builder()
                .stock(productRequestDto.getStock())
                .explain(productRequestDto.getExplain())
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .registrant(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                .build());
    }
}
