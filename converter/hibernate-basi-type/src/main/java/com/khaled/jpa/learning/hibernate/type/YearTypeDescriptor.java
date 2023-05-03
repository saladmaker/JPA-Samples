package com.khaled.jpa.learning.hibernate.type;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractClassJavaType;


import java.time.Year;
import java.util.Objects;


public class YearTypeDescriptor extends AbstractClassJavaType<Year>{
    public static final YearTypeDescriptor INSTANCE = new YearTypeDescriptor();

    public YearTypeDescriptor(){
        super(Year.class);
    }
    public boolean areEquals(Year year1, Year yer2){
        return Objects.equals(year1, yer2);
    }
    public String toString(Year year){
        return year.toString();
    }
    public Year fromString(String value){
        return Year.parse(value);
    }
    @SuppressWarnings("unchecked")
    @Override
    public <X> X unwrap(Year value, Class<X> type, WrapperOptions options){
        if(value== null) return null;
        if(String.class.isAssignableFrom(type)) return (X) toString(value);
        if(Number.class.isAssignableFrom(type)){
            Short shortValue = (short) value.getValue();
            return (X)(shortValue);
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> Year wrap(X value,  WrapperOptions options){

        if(value == null) return null;
        if(value instanceof String s){
            return fromString(s);
        } 
        if(value instanceof Number n){
            return Year.of(n.shortValue());
        }
        throw unknownWrap(value.getClass());
    }
}
