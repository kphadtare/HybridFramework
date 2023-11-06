package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	 private static final Logger LOGGER = LogManager.getLogger(Log.class);

	    //Info Level Logs
	    public static void info(String message) {
	        LOGGER.info(message);
	    }

	  
	    
	    //Error Level Logs
	    public static void error(String message) {
	        LOGGER.error(message);
	    }

	    public static void error(String message, Throwable throwable) {
	        LOGGER.error(message);
	    }

	   
	    //Debug Level Logs
	    public static void debug(String message) {
	        LOGGER.debug(message);
	    }

	    public static void debug(Object message) {
	        LOGGER.debug(message);
	    }

}
