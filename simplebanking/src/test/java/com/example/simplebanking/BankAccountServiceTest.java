package com.example.simplebanking;

import com.example.banking.model.BankAccount;
import com.example.banking.model.DepositTransaction;
import com.example.banking.model.WithdrawalTransaction;
import com.example.banking.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static javafx.beans.binding.Bindings.when;
import static org.mockito.Mockito.*;

public class BankAccountServiceTest {

    private BankAccount account;
    @Test
    public void testTransactionProcessing() {
        BankAccount account = new BankAccount("Jim", "12345");
        account.post(new DepositTransaction(1000));
        account.post(new WithdrawalTransaction(200));
        account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));

        assertEquals(703.50, account.getBalance(), 0.0001);
    }
}