package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class LoginScreenController {

    @FXML
    private TextField userTextField;

    @FXML
    Boolean loginButtonAction(ActionEvent event) {
    	System.out.println("[ENTRAR]");
    	if (userTextField.getText().isEmpty()) {
    		return false;
    	}
		try {
			Parent root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setUserData(userTextField.getText());
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
    	
    }

}
