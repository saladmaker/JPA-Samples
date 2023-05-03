package com.khaled.jpa.learning.converter;

import jakarta.persistence.Embeddable;

import java.util.Objects;

import java.math.BigDecimal;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

@Embeddable
public class Price{
    private String currency;
    private BigDecimal amount;

    Price(){}
    public Price(String currency, BigDecimal amount){
        Objects.requireNonNull(currency);
        Objects.requireNonNull(amount);
        this.currency = currency;
        this.amount = amount;
    }
    public static Price ofMoney(MonetaryAmount money){
        return new Price(money.getCurrency().getCurrencyCode(), money.getNumber().numberValue(BigDecimal.class));
    }
    public MonetaryAmount toMonetaryAmount(){
        return Monetary.getDefaultAmountFactory().setCurrency(currency).setNumber(amount).create();
    }
    public String currency(){
        return currency;
    }
    public BigDecimal amount(){
        return amount;
    }
}
