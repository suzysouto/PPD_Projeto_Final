package application;
import java.util.ArrayList;
import java.util.List;

import net.jini.space.JavaSpace;

public class ReadMessage {
	
	List<LeilaoItem> msgList = new ArrayList<LeilaoItem>();
	

    public ReadMessage() {
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

//            while (true) {
                Message template = new Message();
                Message msg = (Message) space.take(template, null, 60 * 1000);
                if (msg == null) {
                    System.out.println("Tempo de espera esgotado. Encerrando...");
                    System.exit(0);
                }
                msgList.add((LeilaoItem) msg.content);
                System.out.println("Mensagem recebida: "+ msg.content);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<LeilaoItem> getMsgList(){
    	return msgList;
    }
}
