package application;
import net.jini.space.JavaSpace;

public class WriteMessage {
	JavaSpace space;

    public WriteMessage() {
        try {
        	System.out.println("WRITEMESSAGE");
            System.out.println("Procurando pelo servico JavaSpace...");
            Lookup finder = new Lookup(JavaSpace.class);
            space = (JavaSpace) finder.getService();
            if (space == null) {
                    System.out.println("O servico JavaSpace nao foi encontrado. Encerrando...");
                    System.exit(-1);
            } 
            System.out.println("O servico JavaSpace foi encontrado.");
            System.out.println(space);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Escreve o objeto person do tipo Person no espaço de tuplas.
    public void WritePerson(Person person) throws Exception { 
    	space.write(person, null, 100 * 1000 * 1000);
    }
}
