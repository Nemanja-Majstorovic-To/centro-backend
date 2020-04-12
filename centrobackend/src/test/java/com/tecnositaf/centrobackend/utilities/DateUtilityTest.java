package com.tecnositaf.centrobackend.utilities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tecnositaf.centrobackend.CentroBackendApplication;
import com.tecnositaf.centrobackend.exception.FailureException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DateUtilityTest {
	@Test
    public void testSuccess(){
        Timestamp today = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        Timestamp birthday = Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1995, Month.MAY, 7), LocalTime.NOON));
        assertSame( 24, DateUtility.calculateAgeOf(birthday, today) );

    }

    @Test
    public void testBirthDayNullValue(){
        Timestamp today = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        Timestamp birthday = null;
        assertNull( DateUtility.calculateAgeOf(birthday, today) );
    }

    @Test
    public void testTodayNullValue(){
        Timestamp today = null;
        Timestamp birthday = Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1995, Month.MAY, 7), LocalTime.NOON));
        assertThrows( FailureException.class, ()->DateUtility.calculateAgeOf(birthday, today) );
    }
}
