package application;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SendRabbit {
	ConnectionFactory factory;
	Channel channel;
	Connection connection;
	String QUEUE_NAME;
	
	public SendRabbit(String qUEUE_NAME) {
		super();
		QUEUE_NAME = qUEUE_NAME;
		Connection();
	}
	
	public void Connection() {
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void sendRabbit(String msg) {
		try {
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {			
            e.printStackTrace();
	    }
	}
}
