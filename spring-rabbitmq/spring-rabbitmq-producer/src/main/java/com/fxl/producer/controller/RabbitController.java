package com.fxl.producer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxl.producer.service.RabbitService;

@Controller
@RequestMapping("/rabbit")
public class RabbitController {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitController.class);
	
	@Autowired
	private RabbitService rabbitService;
	
	@RequestMapping("/setMessage")
	@ResponseBody
	public String setMessage(String msg, String type) {
		logger.info("rabbitmq--收到待发送消息: type[{}]-msg[{}]", type, msg);
		rabbitService.setMessage(msg, type);
		return "{\"success\":1}";
	}
	
}
