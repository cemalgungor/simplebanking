package com.example.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

public class DepositTransaction extends Transaction {
    public DepositTransaction() {
    }

    public DepositTransaction(double amount) {
        this.setApprovalCode(UUID.randomUUID().toString());
        this.setDate(LocalDateTime.now());
        this.setType(TransactionType.DEPOSIT_TRANSACTION);
        super(amount);
    }

    @Override
    public void execute(BankAccount account) {
        account.deposit(getAmount());
    }
}
