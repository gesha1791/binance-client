package net.chaplinskiy.binanceclient.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResult {
    private String token;
    private BigDecimal result;
}
