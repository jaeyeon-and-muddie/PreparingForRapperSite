package com.skhu.practice.Entity.market;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum How {
    GOOGLE("PAY_GOOGLE"),
    KAKAO("PAY_KAKAO"),
    DEPOSIT("PAY_DEPOSIT"),

    POINT("PAY_POINT");

    private final String value;


}
