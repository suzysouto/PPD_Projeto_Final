package application;
import net.jini.space.JavaSpace;
import java.util.Scanner;

public class WriteMessage {

    public WriteMessage(Object message) {
        try {
        	System.out.println("[WRITE MESSAGE]");
            System.out.println("[WRITE MESSAGE] - Procurando pelo servico JavaSpace...");
            Lookup finder = new Lookup(JavaSpace.class);
            JavaSpace space = (JavaSpace) finder.getService();
            if (space == null) {
                    System.out.println("[WRITE MESSAGE] - O servico JavaSpace nao foi encontrado. Encerrando...");
                    System.exit(-1);
            } 
            System.out.println("[WRITE MESSAGE] - O servico JavaSpace foi encontrado.");
            System.out.println(space);
            
            Message msg = new Message();
            msg.content = message;
            space.write(msg, null, 100 * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
