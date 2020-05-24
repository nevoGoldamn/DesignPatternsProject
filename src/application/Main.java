package application;
	
import guiControllers.MainSceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	private static MainSceneController mainSceneController;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml"));
			AnchorPane pane = (AnchorPane)loader.load();//root of the fxml file
			Scene scene = new Scene(pane);
			mainSceneController = (MainSceneController) loader.getController();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setTitle("Insurance Company");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
