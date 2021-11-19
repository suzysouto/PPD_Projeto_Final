package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.lang.Thread;

public class mainController {
	private List<Person> allOfThem = new ArrayList<Person>();
	List<Person> myPeople = new ArrayList<Person>();
	Boolean ON = true;
	ClientThread ct;
	Person personUpdate;
	ReceiveRabbit reRa;
	Double ray = 0.0;
	
	@FXML
    private ListView<String> menssageList;

    @FXML
    private TextField messageInField;

    @FXML
    private TextField latChange;

    @FXML
    private TextField longChange;

    @FXML
    private ListView<String> contactsList;
    
    @FXML
    private TextField distChange;
    
    @FXML
    private CheckBox offlineMarkedCheck;
    
     @FXML	
	 void updateInformationButton(ActionEvent event) { }
	 

    @FXML
    void offlineMarked(ActionEvent event) {
    	ON = offlineMarkedCheck.isSelected();
    	personUpdate.setON(!ON);
    	readPersonUpdate(personUpdate);
    	
    	if (offlineMarkedCheck.isSelected() == true) {
    		System.out.println(showContactsOnline() + " - Offline");
    	}
    }

    @FXML
    void submitMessageButton(ActionEvent event) {
    	System.out.println("Deu certo enviar!");
		String msg = messageInField.getText();		
	  
		try {
			ct.sendMessage(listasSeparadas());
			ct.sendMessage(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void updateContactsButton(ActionEvent event) {
    	readPerson();
    	System.out.println("Update!!!");
    }
    
    @FXML
    void initialize(){
    	startChat(this);
    	startChatRabbit(this);
    }
    
	public mainController() {
		readPerson();
	}
	
	public void startChat(mainController maCo) {
        Thread startchatThread = new Thread(){ 
        	public void run(){ 
        		try {
 	               this.sleep(500); 
 	               ct = new ClientThread(personUpdate.nickname, maCo); 
 	   			   ct.start();
 	   			   ct.sendMessage(personUpdate.nickname);
 	            } catch (InterruptedException | IOException e) {
 	                // TODO Auto-generated catch block
 	                e.printStackTrace();
 	            }
	        } 
        };
        startchatThread.start();
    }
	
	public void startChatRabbit(mainController mcl) {
		Thread startchatrabbitThread = new Thread(){ 
        	public void run(){ 
	            try {
	               this.sleep(500); 
	               reRa = new ReceiveRabbit(personUpdate.nickname, mcl); 
	   			   reRa.start();
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        } 
        };
        startchatrabbitThread.start();
	}
	
	public void readPerson() {
		Thread rp = new Thread() {
			public void run() {
				ReadPerson readP = new ReadPerson();
				allOfThem = readP.getTodos();
				myPeople = showContacts(allOfThem);
				
	    		System.out.println(myPeople.toString());
	    		
	    		updateOnlineContacs();
			}
		};
		rp.start();
	}
	
	public void readPersonUpdate(Person per) {
		Thread rpu = new Thread() {
			public void run() {
				ReadPersonUpdate readUpdate = new ReadPersonUpdate(per);
				allOfThem = readUpdate.getTodos();
				myPeople = showContacts(allOfThem);
				
	    		updateOnlineContacs();
			}
		};
		rpu.start();
	}
	
	public List<Person> showContacts(List<Person> people) {
    	List<Person> showContactsFilter = new ArrayList<Person>();
    	Boolean existAllOfThem;
    	for (Person p : people) {
    		existAllOfThem = false;
    		for (Person myPerson : myPeople) {
    			if ((myPerson.nome.equals(p.nome)) && (myPerson.nickname.equals(p.nickname))){
    				existAllOfThem = true;
    			}
    		}
    		if (existAllOfThem || ON) {
    			showContactsFilter.add(p);
    		}
    	}
    	return showContactsFilter;
    }
	
	public ObservableList<String> showContactsOnline() {
    	ObservableList<String> showOnlineContactsList;
    	List<String> myPeopleOnline = new ArrayList<String>();
    	for (Person p: myPeople) {
    		myPeopleOnline.add(p.getNickname());
    	}
    	showOnlineContactsList = FXCollections.observableArrayList(myPeopleOnline);
    	return showOnlineContactsList;
	}
	
	public void updateOnlineContacs() {
		contactsList.setItems(showContactsOnline());
    }
	
	public void setPersonUpdate(Person personUpdate) {
		this.personUpdate = personUpdate;
	}
	
	public void chatListMessage(String chatMessage) {
		menssageList.getItems().add(chatMessage);
	}
	
	public String listasSeparadas() {
		String offlineList = "";
		for (Person po : myPeople) {
			if (po.ON == false) {
				System.out.println(offlineList = offlineList + po.nickname + " - ");
			}
		}
		return offlineList;
	}
}