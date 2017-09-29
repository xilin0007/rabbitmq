package com.fxl.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedQueueListener {
	
	private static final Logger logger = LoggerFactory.getLogger(BlueQueueListener.class);
	
	public void onMessage(String message) {
		logger.info("RedQueueListener Receved:" + message);
	}
}
