package com.recargapay.walletservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(min = 1, max = 50, message = "50 max caracteres")
    private String name;

    @NotBlank
    @Size(min = 1, max = 100, message = "100 max caracteres")
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
