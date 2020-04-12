package com.tecnositaf.centrobackend.utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.tecnositaf.centrobackend.dto.DTOAlert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlertUtilityTest {
	
    /***************************************************** INSERT **************************************************************/

	@Test
    public void testTrueInsert() {
        DTOAlert dtoAlert = new DTOAlert(null, 55, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2005, Month.MARCH, 17), LocalTime.MIDNIGHT)),  1, null);
        assertTrue( AlertUtility.isValidAlertForInsert(dtoAlert) );
    }

    @Test
    public void testFalseInsertIdAlertNotNull() {
        DTOAlert dtoAlert = new DTOAlert(9, 55, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2005, Month.MARCH, 17), LocalTime.MIDNIGHT)),  1, null);
        assertFalse( AlertUtility.isValidAlertForInsert(dtoAlert) );
    }
    @Test
    public void testFalseInsertIdDeviceFkNull() {
        DTOAlert dtoAlert = new DTOAlert(null, null, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2005, Month.MARCH, 17), LocalTime.MIDNIGHT)),  1, null);
        assertFalse( AlertUtility.isValidAlertForInsert(dtoAlert) );
    }
    @Test
    public void testFalseInsertTimestampNull() {
        DTOAlert dtoAlert = new DTOAlert(null, 55, null,  1, null);
        assertFalse( AlertUtility.isValidAlertForInsert(dtoAlert) );
    }
    @Test
    public void testFalseInsertIdTypeNull() {
        DTOAlert dtoAlert = new DTOAlert(null, 55, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2005, Month.MARCH, 17), LocalTime.MIDNIGHT)),  null, null);
        assertFalse( AlertUtility.isValidAlertForInsert(dtoAlert) );
    }
    
    /***************************************************** UPDATE **************************************************************/
    @Test
    public void testTrueUpdate() {
        DTOAlert dtoAlert = new DTOAlert(3, 55, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2005, Month.MARCH, 17), LocalTime.MIDNIGHT)),  1, null);
        assertTrue( AlertUtility.isValidAlertForUpdate(dtoAlert) );
    }
    
    @Test
    public void testFalseUpdateIdAlertNull() {
        DTOAlert dtoAlert = new DTOAlert(null, 55, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2005, Month.MARCH, 17), LocalTime.MIDNIGHT)),  1, null);
        assertFalse( AlertUtility.isValidAlertForUpdate(dtoAlert) );
    }
    @Test
    public void testFalseUpdateIdDeviceFkNull() {
        DTOAlert dtoAlert = new DTOAlert(null, null, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2005, Month.MARCH, 17), LocalTime.MIDNIGHT)),  1, null);
        assertFalse( AlertUtility.isValidAlertForUpdate(dtoAlert) );
    }
    @Test
    public void testFalseUpdateTimestampNull() {
        DTOAlert dtoAlert = new DTOAlert(null, 55, null,  1, null);
        assertFalse( AlertUtility.isValidAlertForUpdate(dtoAlert) );
    }
    @Test
    public void testFalseUpdateIdTypeNull() {
        DTOAlert dtoAlert = new DTOAlert(null, 55, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2005, Month.MARCH, 17), LocalTime.MIDNIGHT)),  null, null);
        assertFalse( AlertUtility.isValidAlertForUpdate(dtoAlert) );
    }
   
}
