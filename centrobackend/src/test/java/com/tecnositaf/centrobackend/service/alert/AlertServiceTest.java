package com.tecnositaf.centrobackend.service.alert;

import static org.junit.Assert.assertSame;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tecnositaf.centrobackend.CentroBackendApplication;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.service.AlertService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlertServiceTest {

	@Autowired
	private AlertService alertService;
    @Test
    public void testGetAlertsSizeOnInit(){
        assertSame(3, alertService.getAlerts().size());
    }

    @Test
    public void testGetAlertsValueOnInit(){
        List<Alert> alertsActual = alertService.getAlerts();

        assertSame(3, alertsActual.size());

        Alert alert1 = new Alert(1, 9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1997, Month.APRIL, 18), LocalTime.NOON)),  1);
        
        assert(alert1.equals(alertsActual.get(0)));
    }

    @Test
    public void testGetAlertById() {
        Alert alert1 = new Alert(1, 9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1997, Month.APRIL, 18), LocalTime.NOON)),  1);
        Alert alertId1 = alertService.getAlertById(1);
        assert(alert1.equals(alertId1));
    }
    
    @Test
    public void testGetAlertByIdFailureNotFound() {
        Alert alertId1 = alertService.getAlertById(10);
        assert(alertId1 == null);
    }
    
    @Test
    public void testGetAlertByDevice() {
        List<Alert> alertsActual = alertService.getAlertsByDevice(9);
        assertSame(3, alertsActual.size());
        Alert alert1 = new Alert(1, 9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1997, Month.APRIL, 18), LocalTime.NOON)),  1);
        assert(alert1.equals(alertsActual.get(0)));
    }
    
    @Test
    public void testGetAlertByDeviceFailureNotFound() {
        List<Alert> alertsActual = alertService.getAlertsByDevice(90);
        assert(0 == alertsActual.size());
    }
    
    @Test
    public void testGetAlertByDeviceAndTimestamp() {
        List<Alert> alertsActual = alertService.getAlertsByDevice(9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1997, Month.APRIL, 18), LocalTime.NOON)));
        assertSame(2, alertsActual.size());
		Alert alert3 = new Alert(3, 9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1999, Month.JANUARY, 8), LocalTime.NOON)),  3);
        assert(alert3.equals(alertsActual.get(1)));
    }
    
    @Test
    public void testGetAlertByDeviceAndTimestampFailureNotFound() {
        List<Alert> alertsActual = alertService.getAlertsByDevice(9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2007, Month.APRIL, 18), LocalTime.NOON)));
        assertSame(0, alertsActual.size());
    }
    
    @Test
    public void testGetAlertByStorageYears() {
        List<Alert> alertsActual = alertService.getAlertsByStorageYears(10);
        assertSame(3, alertsActual.size());
		Alert alert3 = new Alert(3, 9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1999, Month.JANUARY, 8), LocalTime.NOON)),  3);
        assert(alert3.equals(alertsActual.get(2)));
    }
    
    @Test
    public void testGetAlertByStorageYearsFailureNotFound() {
        List<Alert> alertsActual = alertService.getAlertsByStorageYears(100);
        assertSame(0, alertsActual.size());
    }
    
    
    @Test
    public void testInsertAlert() {
        List<Alert> alertsActual = alertService.getAlerts();
        Alert alert3 = new Alert(null, 55, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2005, Month.MARCH, 17), LocalTime.MIDNIGHT)),  1);
		
        assertSame(1,alertService.insertNewAlertWithRowsInsertedCheck(alert3)); 
        assertSame(4, alertsActual.size());
		assert(alert3.equals(alertsActual.get(3)));
    }
    
    @Test
    public void testUpdateAlert() {
        List<Alert> alertsActual = alertService.getAlerts();
        Alert alert3 = new Alert(3, 55, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(2005, Month.MARCH, 17), LocalTime.MIDNIGHT)),  1);
		
        assertSame(1,alertService.updateAlert(alert3));        
		assert(alert3.equals(alertsActual.get(2)));
    }
    
    @AfterAll
    public void testDeleteAlert() {
        List<Alert> alertsActual = alertService.getAlerts();
        Alert alert1 = new Alert(1, 9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1997, Month.APRIL, 18), LocalTime.NOON)),  1);
        Alert alert2 = new Alert(2, 9, Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1995, Month.MAY, 7), LocalTime.NOON)),  2);

        assertSame(1, alertService.deleteAlert(alert1));    
        assertSame(2, alertsActual.size());
		assert(alert2.equals(alertsActual.get(0)));
    }
 
}
