package application;

import net.jini.space.JavaSpace;

public class WriteNotify {
	
	 public WriteNotify() {
	        try {
	        	System.out.println("[WRITE NOTIFY]");
	            System.out.println("[WRITE NOTIFY] - Procurando pelo servico JavaSpace...");
	            Lookup finder = new Lookup(JavaSpace.class);
	            JavaSpace space = (JavaSpace) finder.getService();
	            if (space == null) {
	                    System.out.println("[WRITE NOTIFY] - O servico JavaSpace nao foi encontrado. Encerrando...");
	                    System.exit(-1);
	            } 
	            System.out.println("[WRITE NOTIFY] - O servico JavaSpace foi encontrado.");
	            System.out.println(space);
	            
	            Notify note = new Notify();
	            note.id = 1;
	            space.write(note, null, 100 * 100);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
