package com.revature.app;

import com.revature.logging.AppLogger;

public class Driver {
	
	public static void main(String[] args) {
		
		AppLogger.logger.warn("Warn using AppLogger class");
		
		for ( ; ; ) {
			System.out.println("po");
		}
	}
}
