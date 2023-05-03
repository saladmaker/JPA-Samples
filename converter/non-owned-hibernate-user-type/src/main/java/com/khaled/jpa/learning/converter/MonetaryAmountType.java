package com.khaled.jpa.learning.converter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.ValueAccess;
import org.hibernate.usertype.CompositeUserType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.money.MonetaryAmount;
import javax.money.Monetary;

public class MonetaryAmountType implements CompositeUserType<MonetaryAmount>{
    public static class MonetaryAmountMapper{
        BigDecimal amount;
        String currency;
    }
    public MonetaryAmountType(){}

    @Override
    public MonetaryAmount assemble(Serializable cache, Object owner) {
        return (MonetaryAmount) cache;
    }

    @Override
    public MonetaryAmount deepCopy(MonetaryAmount money) {
        return money;
    }

    @Override
    public Serializable disassemble(MonetaryAmount money) {
        return (Serializable)money;
    }

    @Override
    public Class<?> embeddable() {
        return MonetaryAmountMapper.class;
    }

    @Override
    public boolean equals(MonetaryAmount money1, MonetaryAmount money2) {
        return Objects.equals(money1, money2);
    }

    @Override
    public Object getPropertyValue(MonetaryAmount money, int index) throws HibernateException {
        return switch(index){
            case 0 ->  money.getNumber().numberValue(BigDecimal.class);
            case 1 ->  money.getCurrency().getCurrencyCode();
            default ->  null;
        };
    }

    @Override
    public int hashCode(MonetaryAmount money) {
        return money.hashCode();
    }

    @Override
    public MonetaryAmount instantiate(ValueAccess valueAccessor, SessionFactoryImplementor session) {
        var amount = valueAccessor.getValue(0, BigDecimal.class);
        var currency = valueAccessor.getValue(1, String.class);
        return Monetary.getDefaultAmountFactory().setCurrency(currency).setNumber(amount).create();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public MonetaryAmount replace(MonetaryAmount detached, MonetaryAmount managed, Object owner) {
        return detached;
    }

    @Override
    public Class<MonetaryAmount> returnedClass() {
        return MonetaryAmount.class;
    }
    
}
