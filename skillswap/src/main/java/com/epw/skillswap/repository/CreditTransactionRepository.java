package com.epw.skillswap.repository;

import com.epw.skillswap.entity.CreditTransaction;
import com.epw.skillswap.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CreditTransactionRepository
        extends JpaRepository<CreditTransaction, UUID> {

    List<CreditTransaction> findBySenderUserId(UUID senderUserId);

    List<CreditTransaction> findByReceiverUserId(UUID receiverUserId);

    List<CreditTransaction> findByTransactionType(
            TransactionType transactionType);
}