package com.example.simplebanking.controller;

import com.example.banking.dto.BankAccountDTO;
import com.example.banking.model.BankAccount;
import com.example.banking.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account/v1")
public class BankAccountController {
    @Autowired
    private BankAccountService service;

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<?> credit(@PathVariable String accountNumber, @RequestBody Double amount) {
        service.credit(accountNumber, amount);
        return ResponseEntity.ok().body(createResponse("OK"));
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<?> debit(@PathVariable String accountNumber, @RequestBody Double amount) {
        service.debit(accountNumber, amount);
        return ResponseEntity.ok().body(createResponse("OK"));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccountDTO> getAccount(@PathVariable String accountNumber) {
        BankAccount account = service.findAccount(accountNumber);
        BankAccountDTO accountDTO = new BankAccountDTO(
                account.getAccountNumber(),
                account.getOwner(),
                account.getBalance(),
                account.getCreateDate(),
                account.getTransactions()
        );
        return ResponseEntity.ok(accountDTO);
    }

    private Object createResponse(String status) {
        return new Response() {
            public String getStatus() {
                return status;
            }

            public String getApprovalCode() {
                return UUID.randomUUID().toString();
            }
        };
    }

    private interface Response {
        String getStatus();
        String getApprovalCode();
    }
}