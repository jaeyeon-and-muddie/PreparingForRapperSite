package com.skhu.practice.Entity.market;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum What {
    CHARGE("CHARGE"),
    BUY("BUY"),
    SELL("SELL");

    private final String value;
}
