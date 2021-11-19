package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientThread extends Thread {
	BufferedReader input;
	Socket socket;
	String clientName = "vazio";
	String userInput;
	Scanner scanner = new Scanner(System.in);
	String response;
	PrintWriter output;
	mainController mcr;
	
	public ClientThread(String un, mainController mcr) {
		super();
		this.mcr = mcr;
		try {
			socket = new Socket("localhost", 5000);
			userName(un);			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		
	public boolean inputClientName() throws IOException {
		output = new PrintWriter(socket.getOutputStream(), true);
		if (clientName.equals("vazio")) {			
			output.println(clientName);
			return false;
		}
		return true;
	}
	
	public void sendMessage(String msg) throws IOException {
		
		if (inputClientName()) {
			String message = (clientName + ":");
			output.println(message + " " + msg);
		}
	}
	
	@Override
	public void run() {		
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				String response = input.readLine();
				mcr.chatListMessage(response);
				System.out.println(response);
			}
		} catch (IOException e) {
			e.printStackTrace();			
		} finally {
			try {
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void userName(String nameUser) {
		clientName = nameUser;
	}	
}
