package com.tecnositaf.centrobackend.utilities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

import org.springframework.http.HttpStatus;

import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;
import com.tecnositaf.centrobackend.exception.FailureException;

public class DateUtility {

    public static Integer calculateAgeOf(Timestamp timestamp){
    	
        return DateUtility.calculateAgeOf(timestamp, Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.now())));
    }
    public static Integer calculateAgeOf(Timestamp timestamp, Timestamp today){
        if( timestamp == null )      return null;
        if( today == null )         throw new FailureException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseErrorEnum.ERR_500);
        Period period = Period.between(timestamp.toLocalDateTime().toLocalDate(), today.toLocalDateTime().toLocalDate());
        return period.getYears();
    }
    
}