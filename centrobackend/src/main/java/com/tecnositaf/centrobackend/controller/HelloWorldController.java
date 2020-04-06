package com.tecnositaf.centrobackend.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnositaf.centrobackend.utilities.HelloWorldUtilities;

@RestController
public class HelloWorldController {

	
	@GetMapping("/time/now")
	public 
	ResponseEntity<String> getTime() {
	    return new ResponseEntity<String>("GET Response: " + HelloWorldUtilities.getCurrentTime(), HttpStatus.OK);
	}
	

	
	@GetMapping("/list/random")
	public 
	ResponseEntity<ArrayList<Double>> getRandom() {
		return new ResponseEntity<ArrayList<Double>>(HelloWorldUtilities.getRandomList(), HttpStatus.OK);
	}


	
	
	

}
