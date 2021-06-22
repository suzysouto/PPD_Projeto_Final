package application;

import java.util.ArrayList;
import java.util.List;

import net.jini.space.JavaSpace;

public class Server {
	
//	static List<LeilaoItem> msgList = new ArrayList<LeilaoItem>();
	static Integer id = 0;
	
	public static void main(String[] args) {
		 try {
	            System.out.println("Procurando pelo servico JavaSpace...");
	            Lookup finder = new Lookup(JavaSpace.class);
	            JavaSpace space = (JavaSpace) finder.getService();
	            if (space == null) {
	                    System.out.println("O servico JavaSpace nao foi encontrado. Encerrando...");
	                    System.exit(-1);
	            } 
	            System.out.println("O servico JavaSpace foi encontrado.");
	            System.out.println(space);

	          while (true) {
	            Message template = new Message();
	            Message msg = (Message) space.take(template, null, 60 * 10000000);
	            if (msg == null) {
	                System.out.println("Tempo de espera esgotado.");
//	                System.exit(0);
	            }
//	            msgList.add((LeilaoItem) msg.content);
	            System.out.println("Mensagem recebida: "+ msg.content);
	            
	            MessageReturn msgReturn = new MessageReturn();
	            msgReturn.number = id;
	            msgReturn.contentReturn = msg.content;
	            
	           
                space.write(msgReturn, null, 60 * 1000);
	        }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
