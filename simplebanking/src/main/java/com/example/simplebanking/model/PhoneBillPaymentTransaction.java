package com.example.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@DiscriminatorValue("PhoneBillPaymentTransaction")
public class PhoneBillPaymentTransaction extends Transaction {

    private String provider;
    private String phoneNumber;

    public PhoneBillPaymentTransaction() {}

    public PhoneBillPaymentTransaction(String provider, String phoneNumber, double amount) {
        super(amount);
        this.setApprovalCode(UUID.randomUUID().toString());
        this.setDate(LocalDateTime.now());
        this.setType(TransactionType.PHONE_BILL_PAYMENT_TRANSACTION);
        this.provider = provider;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void execute(BankAccount account) {
        account.debit(getAmount());
    }
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
