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
    	
    	System.out.println("cadastrar");
    	
    	LeilaoItem leilaoItem = new LeilaoItem(
    			nomeTextField.getText(),
    			valorTextField.getText(),
    			descricaoTextArea.getText());
    	
		Thread writeMessageThread = new Thread(){ public void run(){ WriteMessage writeMessage = new WriteMessage(leilaoItem); } };
		writeMessageThread.start();
    	
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
    	leilaoObservableList = FXCollections.observableArrayList(leilaoArrayList);
    	leilaoItemList.setItems(leilaoObservableList);
    }
    
    public MainScreenController() {
    	System.out.println("ABEL");
    	startReadMessage();
		
    }
    
    public void startReadMessage() {
    	Thread readMessageThread = new Thread(){ public void run(){ readMessage(); } };
		readMessageThread.start();
    }
    
    public void readMessage() {
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
                Message template = new Message();
                Message msg = (Message) space.take(template, null, 60 * 1000);
                if (msg == null) {
                    System.out.println("Tempo de espera esgotado");
//                    System.exit(0);
                }
                System.out.println("Mensagem recebida: "+ msg.content);
                leilaoArrayList.add((LeilaoItem) msg.content);
                updateLeilaoListView();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
