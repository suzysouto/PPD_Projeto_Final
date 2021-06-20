package application;

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

public class MainScreenController {
	
	ReadMessage readMessage;
	List<LeilaoItem> leilaoArrayList;
	ObservableList<LeilaoItem> leilaoObservableList;

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
    	updateLeilaoListView();
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
    	System.out.println("ABEL EH BALDE");
    	initReadMessage();
    	leilaoArrayList = this.readMessage.getMsgList();
    	leilaoObservableList = FXCollections.observableArrayList(leilaoArrayList);
    	leilaoItemList.setItems(leilaoObservableList);
    }
    
    public MainScreenController() {
    	System.out.println("ABEL");
    	initReadMessage();
		
    }
    
    public void initReadMessage() {
    	Thread readMessageThread = new Thread(){ public void run(){ readMessage = new ReadMessage(); } };
		readMessageThread.start();
    }

}
