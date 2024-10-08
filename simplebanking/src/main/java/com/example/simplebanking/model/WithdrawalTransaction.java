package com.example.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@DiscriminatorValue("WITHDRAWAL")
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction() {
    }

    public WithdrawalTransaction(double amount) {
        super(amount);
        this.setApprovalCode(UUID.randomUUID().toString());
        this.setDate(LocalDateTime.now());
        this.setType(TransactionType.WITHDRAWAL_TRANSACTION);
    }

    @Override
    public void execute(BankAccount account) {
        account.withdraw(getAmount());
    }
}