package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainScreenController {

    @FXML
    private ListView<?> leilaoItemList;

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
    
    Boolean cadastrarFieldsIsEmpty () {
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

}
