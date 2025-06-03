package com.recargapay.walletservice.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WalletDTO {

    private Long walletID;

    private Long userId;

    private BigDecimal balance;

    private LocalDateTime createdAt;
}
