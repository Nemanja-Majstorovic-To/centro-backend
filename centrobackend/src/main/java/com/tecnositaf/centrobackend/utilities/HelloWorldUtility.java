package com.tecnositaf.centrobackend.utilities;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HelloWorldUtility {
	
	public static String getCurrentTime(){
	     LocalTime time = LocalTime.now();
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
         String formattedTime=time.format(formatter);
	     return "Current time of the day: " + formattedTime;
	 }
	
	public static ArrayList<Double> getRandomList() {
		ArrayList<Double> list = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			list.add(i, Math.random());
		}		
		return list;
	}
}
