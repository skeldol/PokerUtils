package com.pokersimples.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {
	
	public final static boolean PERFORMANCE = false;
	
	private static Map<String, LocalDateTime> performanceStart = new HashMap<String, LocalDateTime>();
	private static Map<String, Long> performanceDuration = new HashMap<String, Long>();	
	private static Map<String, Integer> performanceStats = new HashMap<String, Integer>();		
	
	public static void error(Object pMsg) {
		debug(pMsg);
	}
	
	public static void performance(String pMethodName) {
		if(!PERFORMANCE) return;
		
		LocalDateTime start = performanceStart.get(pMethodName);	
		
		if(start == null) {
			performanceStart.put(pMethodName, LocalDateTime.now());
			
			// Increment the stat counter for the start
			Integer stats = performanceStats.get(pMethodName);
			if(stats != null) {
				 performanceStats.put(pMethodName, ++stats);
			} else {
				 performanceStats.put(pMethodName, 1);				
			}
			
			
		} else {
			LocalDateTime now = LocalDateTime.now();
			long currentDuration = now.toLocalTime().toNanoOfDay() - start.toLocalTime().toNanoOfDay();
			
			Long cumulativeDurationLong = performanceDuration.get(pMethodName);
			long cumulativeDuration = 0;
			
			if(cumulativeDurationLong != null) { 
				cumulativeDuration = cumulativeDurationLong.longValue();
			}
			
			cumulativeDuration += currentDuration;
			
			performanceDuration.put(pMethodName, cumulativeDuration);		
			performanceStart.remove(pMethodName);
			
		}
	}
	
	
	public static void debug(Object pMsg) {
		
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  		
		
		System.out.println(dtf.format(now) + ":" + pMsg);
	}	
	
	public static void performance(Object pMsg) {
		
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  		
		
		System.out.println(dtf.format(now) + ":PERFORMANCE:" + pMsg);
	}		
	
	
	public static void printStats() {
        System.out.println("Task:Duration:Times Executed:Avg Time"); 
		 for (Map.Entry<String,Long> entry : performanceDuration.entrySet())  
	            System.out.println(entry.getKey() + ":" + entry.getValue() + ":" + performanceStats.get(entry.getKey())); 
	}
}
