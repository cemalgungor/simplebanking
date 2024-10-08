package com.example.simplebanking.service;

import com.example.simplebanking.model.BankAccount;

public interface BankAccountService {

    public BankAccount createAccount(String owner, String accountNumber) ;
    public BankAccount findAccount(String accountNumber);
    public void credit(String accountNumber, double amount);
    public void debit(String accountNumber, double amount);
}
