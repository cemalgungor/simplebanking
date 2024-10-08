package com.example.banking.service.impl;

import com.example.banking.model.BankAccount;
import com.example.banking.repository.BankAccountRepository;
import com.example.simplebanking.model.BankAccount;
import com.example.simplebanking.model.DepositTransaction;
import com.example.simplebanking.model.Transaction;
import com.example.simplebanking.model.WithdrawalTransaction;
import com.example.simplebanking.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountRepository repository;

    @Override
    public BankAccount createAccount(String owner, String accountNumber) {
        BankAccount account = new BankAccount(owner, accountNumber);
        return repository.save(account);
    }
    @Override
    public BankAccount findAccount(String accountNumber) {
        return repository.findById(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
    @Override
    public void credit(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        account.post(new DepositTransaction(amount));
        repository.save(account);
    }
    @Override
    public void debit(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        account.post(new WithdrawalTransaction(amount));
        repository.save(account);
    }
}
