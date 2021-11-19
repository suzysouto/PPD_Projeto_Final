package application;

import java.util.ArrayList;
import java.util.List;
import net.jini.space.JavaSpace;

public class ReadPerson {
	List<Person> todos = new ArrayList<Person>();
	
	JavaSpace space;
	        
    public ReadPerson() {
        try {
            System.out.println("Procurando pelo servico JavaSpace...");
            Lookup finder = new Lookup(JavaSpace.class);
            this.space = (JavaSpace) finder.getService();
            if (this.space == null) {
                    System.out.println("O servico JavaSpace nao foi encontrado. Encerrando...");
                    System.exit(-1);
            } 
            System.out.println("O servico JavaSpace foi encontrado.");
            System.out.println(this.space);
            
            while (true) {
            	//Cria um novo objeto do tipo Person.
	            Person template = new Person();
	            //Retira todo mundo do espaço de tuplas.
	            Person p = (Person) space.take(template, null, 100 * 3); //Typecast ou conversão para o objeto Person.
	            if (p == null) {
	                System.out.println("Tempo de espera esgotado");
	                break;
	            }
	            //Caso p não seja null é adicionado a lista todos do tipo Person.
	            todos.add(p);
	            System.out.println("Mensagem recebida: "+ p.toString());
            }
            //Pra cada people que foi adicionado na lista todos é escrito na tupla.
            for (Person people: todos) {
            	space.write(people, null, 100 * 1000 * 1000);
            }
          
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

	public List<Person> getTodos() {
		return todos;
	}

	public void setTodos(List<Person> todos) {
		this.todos = todos;
	}
}
