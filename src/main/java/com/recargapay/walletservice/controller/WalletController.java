package com.recargapay.walletservice.controller;


import com.recargapay.walletservice.dto.BalanceRequestDTO;
import com.recargapay.walletservice.dto.BalanceResponseDTO;
import com.recargapay.walletservice.dto.UserDTO;
import com.recargapay.walletservice.dto.WalletDTO;
import com.recargapay.walletservice.entities.Wallet;
import com.recargapay.walletservice.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping(path = "/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public WalletDTO createWallet(@RequestBody @Valid WalletDTO walletDTO) {
        return walletService.createWallet(walletDTO);
    }

    @GetMapping(path = "/balance/current/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public BalanceResponseDTO currentBalance(@RequestParam Long userId) {
        Wallet wallet = walletService.currentBalance(userId);
        return  BalanceResponseDTO.builder()
                .walletId(wallet.getWalletID())
                .balance(wallet.getBalance())
                .build();
    }

    @PutMapping(path = "/deposit/funds/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public BalanceResponseDTO depositFunds(@RequestParam Long userId,
    @RequestBody @Valid BalanceRequestDTO balanceRequestDTO) {
        Wallet wallet = walletService.depositFunds(userId, balanceRequestDTO.getAmount());
        return  BalanceResponseDTO.builder()
                .walletId(wallet.getWalletID())
                .balance(wallet.getBalance())
                .build();
    }

    @PutMapping(path = "/withdraw/funds/{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public BalanceResponseDTO withdrawFunds(@RequestParam Long userId,
                                           @RequestBody @Valid BalanceRequestDTO balanceRequestDTO) {
        Wallet wallet = walletService.withdrawFunds(userId, balanceRequestDTO.getAmount());
        return  BalanceResponseDTO.builder()
                .walletId(wallet.getWalletID())
                .balance(wallet.getBalance())
                .build();
    }
}
