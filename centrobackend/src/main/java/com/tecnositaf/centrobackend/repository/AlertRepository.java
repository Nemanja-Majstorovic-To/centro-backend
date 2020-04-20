package com.tecnositaf.centrobackend.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.tecnositaf.centrobackend.model.Alert;

@Repository
public interface AlertRepository extends MongoRepository<Alert, String> {
			
	public ArrayList<Alert> findByIdDeviceFk(Integer idDeviceFk);
	
	@Query("{'idDeviceFk': ?0, 'localDate': { $gte : ?1 }}")
	public ArrayList<Alert> findByIdDeviceFkAndDate(Integer idDeviceFk, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate);

}
