package com.recargapay.walletservice.repository;

import com.recargapay.walletservice.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
