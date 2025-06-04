package com.recargapay.walletservice.service;


import com.recargapay.walletservice.dto.WalletDTO;
import com.recargapay.walletservice.entities.BalanceHistory;
import com.recargapay.walletservice.entities.User;
import com.recargapay.walletservice.entities.Wallet;
import com.recargapay.walletservice.repository.BalanceRepository;
import com.recargapay.walletservice.repository.UserRepository;
import com.recargapay.walletservice.repository.WalletRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Validated
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    private final UserRepository userRepository;

    private final BalanceRepository balanceRepository;


    // Method to create a wallet
    public WalletDTO createWallet(WalletDTO walletDTO) {
        User user = userRepository.findById(walletDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + walletDTO.getUserId()));

        Wallet wallet = Wallet.builder()
                .balance(walletDTO.getBalance())
                .userId(user)
                .createdAt(LocalDateTime.now())
                .build();

        Wallet savedWallet = walletRepository.save(wallet);

        // Save the initial balance in the balance repository
        saveBalanceHistory(savedWallet);

        // Convert saved Wallet entity to WalletDTO
        WalletDTO savedWalletDTO = new WalletDTO();
        savedWalletDTO.setWalletID(savedWallet.getWalletID());
        savedWalletDTO.setUserId(savedWallet.getUserId().getUserId());
        savedWalletDTO.setBalance(savedWallet.getBalance());
        savedWalletDTO.setCreatedAt(savedWallet.getCreatedAt());

        return savedWalletDTO;

    }

    private void saveBalanceHistory(Wallet wallet) {
        BalanceHistory balanceHistory = BalanceHistory.builder()
                .wallet(wallet)
                .balance(wallet.getBalance())
                .recordAt(LocalDateTime.now())
                .build();

        balanceRepository.save(balanceHistory);

    }

    public Wallet currentBalance(Long userId) {
        return walletRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("Wallet not found with ID: " + userId));

    }

    public Wallet depositFunds(Long userId, @Valid BigDecimal amount) {
        Wallet wallet = walletRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("Wallet not found with ID: " + userId));

        wallet.setBalance(wallet.getBalance().add(amount));

        walletRepository.save(wallet);

        // Save the updated balance in the balance repository
        saveBalanceHistory(wallet);

        return wallet;
    }

    public Wallet withdrawFunds(Long userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("Wallet not found with ID: " + userId));

        wallet.setBalance(wallet.getBalance().subtract(amount));

        walletRepository.save(wallet);

        // Save the updated balance in the balance repository
        saveBalanceHistory(wallet);

        return wallet;
    }
}
