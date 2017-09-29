package com.fxl.producer.service;

public interface RabbitService {
	
	/**
	 * 发送消息  
	 * @param msg 消息内容  
     * @param routingKey 路由关键字  
	 */
	public void setMessage(String msg, String routingKey);
}
