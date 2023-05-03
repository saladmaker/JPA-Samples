package com.khaled.jpa.learning.converter;

import static jakarta.persistence.GenerationType.AUTO;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Date;

import java.time.LocalDate;
import java.time.MonthDay;

/**
 *
 * @author khaled
 */
@Converter(autoApply = true)
public class MonthDayConverter implements AttributeConverter<MonthDay, Date>{
    public Date convertToDatabaseColumn(MonthDay day){
        if(null != day){
            return Date.valueOf(day.atYear(1));
        }
        return null;
    }
    public MonthDay convertToEntityAttribute(Date date){
        if(null != date){
            LocalDate localDate = date.toLocalDate();
            return MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
        }
        return null;
    }
}
