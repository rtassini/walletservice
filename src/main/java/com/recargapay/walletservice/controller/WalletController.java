package com.recargapay.walletservice.controller;


import com.recargapay.walletservice.dto.UserDTO;
import com.recargapay.walletservice.dto.WalletDTO;
import com.recargapay.walletservice.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public WalletDTO createWallet(@RequestBody @Valid WalletDTO walletDTO) {
        return walletService.createWallet(walletDTO);
    }
}
