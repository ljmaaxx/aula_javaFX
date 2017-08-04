package view.controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Sistema extends Application{

	private static Sistema instance;
	
	public static Sistema getInstance(){
		if(instance == null){
			instance = new Sistema();
		}
		return instance;
	}
	
	private Stage primaryStage;
	private Pane rootScene;
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
		this.primaryStage.setTitle("Agenda Telefonica");
		this.rootScene = new Pane();
		
		Scene scene = new Scene(this.rootScene, 600, 400);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
		
		this.openLoginScreen();
	}
	
	private void openLoginScreen(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Sistema.class.getResource("/view/MainTemplate.fxml"));
			Pane pane = (Pane) loader.load();
			
			this.rootScene.getChildren().add(pane);
			MainController controller = loader.getController();
			controller.setApp(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void changeStage(Stage stage){
		this.primaryStage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
