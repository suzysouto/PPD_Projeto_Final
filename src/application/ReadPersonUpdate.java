package application;

import java.util.ArrayList;
import java.util.List;
import net.jini.space.JavaSpace;

public class ReadPersonUpdate {
	List<Person> todos = new ArrayList<Person>();
	
	JavaSpace space;
	        
    public ReadPersonUpdate(Person updatedPerson) {
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
          //Pra cada people que foi adicionado na lista todos se o nome o nick do objeto Person forem iguais ao updatedPerson, vai ser colocado
          //na lista todos pegando pelo índice de people que é o elemento que será substituído e o elemento que será guardado na posição especificada
          //depois atribui o updatedPerson ao people e em seguida é escrito na tupla.
            for (Person people: todos) {
            	if (people.nome.equals(updatedPerson.nome) && people.nickname.equals(updatedPerson.nickname)) {
            		todos.set(todos.indexOf(people), updatedPerson);
            		people = updatedPerson;
            	}
            	space.write(people, null, 100 * 1000 * 1000);
            }
            
            System.out.println(todos.toString());
          
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
