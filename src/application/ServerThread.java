package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rabbitmq.client.DnsSrvRecordAddressResolver.SrvRecord;

public class ServerThread extends Thread {
	private Socket socket;
	private ArrayList<ServerThread> threadList;
	private PrintWriter output;	 
	private static List<String> secondList = new ArrayList<String>();
	SendRabbit sr;
	
	public ServerThread(Socket socket, ArrayList<ServerThread> threadList) {
		super();
		this.socket = socket;
		this.threadList = threadList;
	}
	

	public static void main(String[] args) {
		//Lista onde serão adicionados todos os clientes da thread.
		ArrayList<ServerThread> threadList = new ArrayList<>();
		
		try (ServerSocket serversocket = new ServerSocket(5000)) {
			while(true) {
				Socket socket = serversocket.accept();
				ServerThread serverThread = new ServerThread(socket, threadList);
				
				//Lendo a entrada do cliente.
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String nomeCliente = input.readLine();
				List<String> removeDoubleList = Arrays.asList(nomeCliente.split(": "));
				String doubleList = "";
				if (removeDoubleList.size() > 1) {
					doubleList = removeDoubleList.get(1);
				}
				secondList.add(doubleList);
				
				//Iniciando a thread.
				threadList.add(serverThread);
				serverThread.start();
			}
		} catch (Exception e) {
			System.out.println("Error occured in main: " + e.getStackTrace());
		}
	}
	
	@Override
	public void run() {
		try {
			//Lendo a entrada do cliente.
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//retornando o output para o cliente. O parametro true é pra dar um flush no buffer. 
			output = new PrintWriter(socket.getOutputStream(), true);
			
			//Loop infinito pro servidor.
			while(true) {
				String offlinePeople = input.readLine();
				List<String> removeSenderList = Arrays.asList(offlinePeople.split(": "));
				List<String> offlinePeopleList = new ArrayList<String>();
				if (removeSenderList.size() > 1) {
					offlinePeopleList = Arrays.asList(removeSenderList.get(1).split(" - "));
				}				
				String outputString = input.readLine();
				//Caso o usuario digite "exit".
				if(outputString.equals("exit")) {
					break;
				} 
				printToAllClients(outputString, offlinePeopleList);
				System.out.println("Server received: " + outputString);
			}
		} catch (Exception e) {
			System.out.println("Error occured " + e.getStackTrace());
		}
		
	}
	
	private void printToAllClients(String outputString, List<String> offlinePeopleList) {	
		int index = 0;
		
		for (ServerThread st: threadList) {
			Boolean coisa = true;
			if (offlinePeopleList.isEmpty()) {
				st.output.println(outputString);
				index++;
			} else {
				for (String people : offlinePeopleList) {
					if (people.equals(secondList.get(index))) {
						System.out.println("ENVIANDO OFFLINE!!");
						sr = new SendRabbit(people);
						sr.sendRabbit(outputString);
						index++;
						coisa = false;
					} 
				}
				if (coisa) {
					st.output.println(outputString);
					index++;
				}
			}
		}
	}
	
	
}
