package com.fxl.rabbit.topic;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReceiveLogsTopic1 {

	private static final String EXCHANGE_NAME = "topic_logs";
	// ·�ɹؼ���
	private static final String[] routingKeys = new String[] { "*.orange.*" };

	public static void main(String[] argv) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		// ����һ��ƥ��ģʽ�Ľ�����
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String queueName = channel.queueDeclare().getQueue();
		
		// ��·�ɹؼ���
		for (String bindingKey : routingKeys) {
			channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
			System.out.println("ReceiveLogsTopic1 exchange:" + EXCHANGE_NAME + ", queue:" + queueName + ", BindRoutingKey:" + bindingKey);
		}

		System.out.println("ReceiveLogsTopic1 [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("ReceiveLogsTopic1 [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}

}
