package com.recargapay.walletservice.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BalanceRequestDTO {

    private BigDecimal amount;
}
