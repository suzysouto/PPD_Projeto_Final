package application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainScreen {
	
	public MainScreen(Stage primaryStage) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("FXMLMainScreen.fxml"));
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
