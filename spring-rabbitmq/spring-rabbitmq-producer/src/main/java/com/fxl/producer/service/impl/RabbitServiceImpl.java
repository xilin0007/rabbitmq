package com.fxl.producer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxl.producer.service.RabbitService;

@Service
public class RabbitServiceImpl implements RabbitService {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitServiceImpl.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void setMessage(String msg, String routingKey) {
		rabbitTemplate.convertAndSend(routingKey, msg);
		logger.info("rabbitmq--发送消息完成: routingKey[{}]-msg[{}]", routingKey, msg);
	}
	
}
