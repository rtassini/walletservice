package com.recargapay.walletservice.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long userId;

    private String name;

    private String email;

    private LocalDateTime createdAt;
}
