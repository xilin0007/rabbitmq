package com.fxl.rabbit.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 模糊匹配，匹配(topic)交换器
 */
public class TopicSend {

	private static final String EXCHANGE_NAME = "topic_logs";
	// 待发送的消息
	private static final String[] routingKeys = new String[] { 
		"quick.orange.rabbit", 
		"lazy.orange.elephant", 
		"quick.orange.fox",
		"lazy.brown.fox", 
		"quick.brown.fox", 
		"quick.orange.male.rabbit", 
		"lazy.orange.male.rabbit" 
	};

	public static void main(String[] argv) {
		
		Connection connection = null;
		Channel channel = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");

			connection = factory.newConnection();
			channel = connection.createChannel();
			// 声明一个匹配模式的交换器
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");

			// 发送消息
			for (String severity : routingKeys) {
				String message = "From " + severity + " routingKey' s message!";
				channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
				System.out.println("TopicSend [x] Sent '" + severity + "':'" + message + "'");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

}
