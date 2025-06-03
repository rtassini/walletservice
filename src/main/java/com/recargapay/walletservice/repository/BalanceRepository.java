package com.recargapay.walletservice.repository;

import com.recargapay.walletservice.entities.BalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceHistory, Long> {
}
