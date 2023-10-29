package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;


    public Transaction createWithdrawal(Transaction transaction){

        if(transaction.getAmount() > accountRepository.findAccountByCbu(transaction.getCbu()).getBalance()){
            throw new InsufficientFundsException("Insufficient funds");
        }
        transaction.setAmount(-transaction.getAmount());
        return transactionRepository.save(transaction);
    }

    public Transaction createDeposit(Transaction transaction){
        if(transaction.getAmount().isNaN() || transaction.getAmount() < 0){
            throw new DepositNegativeSumException("Amount negative or null");
        }
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> findById(Long id){
        return transactionRepository.findById(id);
    }

    public Collection<Transaction> findTransactionsByCbu(Long cbu) {
        return transactionRepository.findTransactionsByCbu(cbu);
    }

    public void deleteById(Long id){
        transactionRepository.deleteById(id);
    }
}
