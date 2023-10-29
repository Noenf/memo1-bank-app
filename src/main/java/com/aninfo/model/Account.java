package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;
    private static final Double maxPromoDiscount = 500.00;
    private static final Double minPromoAmount = 2000.00;

    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getMaxPromoDiscount(){return maxPromoDiscount;}

    public Double getMinPromoAmount(){return minPromoAmount;}


}
