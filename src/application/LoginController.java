package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class LoginController {
	@FXML
    private TextField usuarioNome;

    @FXML
    private TextField usuarioNick;

    @FXML
    private TextField usuarioLong;

    @FXML
    private TextField usuarioLat;

    @FXML
    Boolean continuarButton(ActionEvent event) throws Exception {
    	if (usuarioNome.getText().isEmpty() && 
    		usuarioNick.getText().isEmpty() && 
    		usuarioLong.getText().isEmpty() && 
    		usuarioLat.getText().isEmpty()) {
    		return false;
    	}
    	
    	String nome = usuarioNome.getText();
    	String nickname = usuarioNick.getText();
    	Double lat = Double.parseDouble(usuarioLat.getText());
    	Double lon = Double.parseDouble(usuarioLong.getText());
    	Boolean ON = true;
    	
    	Person ps = new Person(nome, nickname, lat, lon, ON);    	
    	
		try {
			WriteMessage wm = new WriteMessage();
	    	wm.WritePerson(ps);
	    	
	    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("main.fxml"));
			Parent root = (Parent) fxmlloader.load();
			
			mainController mc = fxmlloader.getController();
			mc.setPersonUpdate(ps);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			/*Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setUserData(usuarioNome.getText());
			Scene scene = new Scene(root);
			stage.setScene(scene);*/	
	    	
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
    }
}
