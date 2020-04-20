package com.tecnositaf.centrobackend.utilities;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.http.HttpStatus;

import com.tecnositaf.centrobackend.enumeration.ResponseErrorEnum;
import com.tecnositaf.centrobackend.exception.FailureException;

public class DateUtility {

    public static Integer calculateAgeOf(LocalDate birthday){
        return DateUtility.calculateAgeOf(birthday, LocalDate.now());
    }
    public static Integer calculateAgeOf(LocalDate birthday, LocalDate today){
        if( birthday == null )      return null;
        if( today == null )         throw new FailureException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseErrorEnum.ERR_500);
        Period period = Period.between(birthday, today);
        return period.getYears();
    }
    
}