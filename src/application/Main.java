package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	ReadMessage readMessage;
	
	@Override
	public void start(Stage primaryStage) {
		
//		Thread readMessageThread = new Thread(){ public void run(){ readMessage = new ReadMessage(); } };
//		readMessageThread.start();
	
		MainScreen mainScreen = new MainScreen(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
