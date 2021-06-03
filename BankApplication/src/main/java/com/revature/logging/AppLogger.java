package com.revature.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLogger {

	// need this single logger object
	public static final Logger logger = LogManager.getLogger(AppLogger.class);
}
