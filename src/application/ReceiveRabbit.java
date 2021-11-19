package application;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveRabbit extends Thread {
	ConnectionFactory factory;
	Channel channel;
	Connection connection;
	String QUEUE_NAME;
	DeliverCallback deliverCallback;
	mainController mC;
	
    public ReceiveRabbit(String qUEUE_NAME, mainController MC) {
		super();
		QUEUE_NAME = qUEUE_NAME;
		this.mC = MC;
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
    
	public void run() {
		deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            mC.chatListMessage("[Offline] " + message);
        };
        try {
			channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}