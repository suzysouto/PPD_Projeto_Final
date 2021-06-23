package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import net.jini.space.JavaSpace;

public class MainScreenController {
	
	List<LeilaoItem> leilaoArrayList = new ArrayList<LeilaoItem>();
	ObservableList<LeilaoItem> leilaoObservableList;
	JavaSpace space;

    @FXML
    private ListView<LeilaoItem> leilaoItemList;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField valorTextField;

    @FXML
    private TextArea descricaoTextArea;

    @FXML
    private TextArea informacoesTextArea;

    @FXML
    private ListView<?> lancesItemList;
    
    @FXML
    private TextField registrarValorTextField;

    @FXML
    private Label itemLabel;

    @FXML
    Boolean cadastrarActionButton(ActionEvent event) {
    	if (cadastrarFieldsIsEmpty()) {
    		return false;
    	}
    	
    	System.out.println("[CADASTRAR]");
    	
    	LeilaoItem leilaoItem = new LeilaoItem(
    			nomeTextField.getText(),
    			valorTextField.getText(),
    			descricaoTextArea.getText());
		
		startWriteMessage(leilaoItem);
		startWriteNotify();
	
    	return true;
    }

    @FXML
    void lancesItemAction(MouseEvent event) {
    	System.out.println("lances");
    }

    @FXML
    void leilaoItemClickAction(MouseEvent event) {
    	System.out.println("leilao");
    }

    @FXML
    void registrarButtonAction(ActionEvent event) {
    	System.out.println("registrar");
    }

    @FXML
    void removerButtonAction(ActionEvent event) {
    	System.out.println("remover");
    }
    
    public Boolean cadastrarFieldsIsEmpty () {
    	Boolean statusBoolean = false;
    	
    	if (nomeTextField.getText().isEmpty()){
    		statusBoolean = true;
    	}else if(valorTextField.getText().isEmpty()) {
    		statusBoolean = true;
    	}else if(descricaoTextArea.getText().isEmpty()) {
    		statusBoolean = true;
    	}
    	
    	return statusBoolean;
    }
    
    public void updateLeilaoListView() {
    	System.out.println("[UPDATE LEILAO VIEW] - Atualizando View");
    	leilaoObservableList = FXCollections.observableArrayList(leilaoArrayList);
    	leilaoItemList.setItems(leilaoObservableList);
    }
    
    public MainScreenController() {
    	System.out.println("INICIANDO TELA PRINCIPAL");
    	startReadMessage();
    	startReadNotify();
		
    }
    
    public void startWriteMessage(LeilaoItem leilaoItem) {
    	Thread writeMessageThread = new Thread(){ public void run(){ WriteMessage writeMessage = new WriteMessage(leilaoItem); } };
		writeMessageThread.start();
    }
    
    public void startWriteNotify() {
    	Thread writeNotifyThread = new Thread(){ public void run(){ WriteNotify writeNotify = new WriteNotify(); } };
		writeNotifyThread.start();
    }
    
    public void startReadMessage() {
    	Thread readMessageThread = new Thread(){ public void run(){ readMessage(); } };
		readMessageThread.start();
    }
    
    public void startReadNotify() {
    	Thread readNotifyThread = new Thread(){ public void run(){ 
    		try {
				this.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		readNotify();
	    	run();} };
	    		
	    readNotifyThread.start();
    }
    
    public void readNotify() {
    	try {
    	System.out.println("[READ NOTIFY]");
        System.out.println("[READ NOTIFY] - Procurando pelo servico JavaSpace...");
        Lookup finder = new Lookup(JavaSpace.class);
        this.space = (JavaSpace) finder.getService();
        if (this.space == null) {
                System.out.println("[READ NOTIFY] - O servico JavaSpace nao foi encontrado. Encerrando...");
                System.exit(-1);
        } 
        System.out.println("[READ NOTIFY] - O servico JavaSpace foi encontrado.");
        System.out.println(this.space);
        
        Notify template = new Notify();
        Notify note = (Notify) space.take(template, null, 100 * 1000 * 1000);
        if (note == null) {
            System.out.println("[READ NOTIFY] - Tempo de espera esgotado");
        }
        space.write(note, null, 100 * 5);
        startReadMessage();
        
    	} catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void readMessage() {
        try {
        	System.out.println("[READ MESSAGE]");
            System.out.println("[READ MESSAGE] - Procurando pelo servico JavaSpace...");
            Lookup finder = new Lookup(JavaSpace.class);
            this.space = (JavaSpace) finder.getService();
            if (this.space == null) {
                    System.out.println("[READ MESSAGE] - O servico JavaSpace nao foi encontrado. Encerrando...");
                    System.exit(-1);
            } 
            System.out.println("[READ MESSAGE] - O servico JavaSpace foi encontrado.");
            System.out.println(this.space);
            
            List<Message> spaceArrayCopy = new ArrayList<Message>();
            List<LeilaoItem> leilaoItems = new ArrayList<LeilaoItem>();
            
            while (true) {
                Message template = new Message();
                Message msg = (Message) space.take(template, null, 100 * 5);
                if (msg == null) {
                    System.out.println("[READ MESSAGE] - Tempo de espera esgotado");
                    break;
                }
                System.out.println("[READ MESSAGE] - Mensagem recebida: "+ msg.content);
                spaceArrayCopy.add(msg);
            }
            
            for (Message msg : spaceArrayCopy) {
            	space.write(msg, null, 100 * 1000 * 1000);
            	leilaoItems.add((LeilaoItem) msg.content);
            }
            
            spaceArrayCopy = new ArrayList<Message>();
            this.leilaoArrayList = leilaoItems;
            leilaoItems = new ArrayList<LeilaoItem>();
            
            updateLeilaoListView();
          
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
