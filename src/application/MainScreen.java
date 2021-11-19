package application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainScreen {
	
	private Pane root;
	private Scene scene;
	
	public MainScreen(Stage primaryStage) {
		TelaLogin(primaryStage);
	}
	
	public void TelaLogin(Stage primaryStage) {
		try {
			this.root = FXMLLoader.load(getClass().getResource("login.fxml"));			
			this.scene = new Scene(root);	
			
			primaryStage.setTitle("Chat da Final");			
			primaryStage.setScene(scene);			
			primaryStage.show();			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
