package view.controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import negocios.ControllerPessoa;
import negocios.beans.Pessoa;

public class Sistema extends Application{

	private static Sistema instance;
	private ControllerPessoa control;
	
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
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(false);
		this.primaryStage.setTitle("Agenda Telefonica");
		this.rootScene = new Pane();
		
		Scene scene = new Scene(this.rootScene, 600, 400);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
		
		this.openLoginScreen();
		this.carregarPessoas();
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
	
	private void carregarPessoas(){
		this.control = ControllerPessoa.getInstance();
		Pessoa a = new Pessoa("Joao", "99999-9999");
		Pessoa b = new Pessoa("Maria", "99999-9999");
		Pessoa c = new Pessoa("Thiago", "99999-9999");
		Pessoa d = new Pessoa("Zé", "99999-9999");
		this.control.cadastrar(a);
		this.control.cadastrar(b);
		this.control.cadastrar(c);
		this.control.cadastrar(d);
	}
	
	public void changeStage(Stage stage){
		this.primaryStage = stage;
	}
	
	public Stage getPrimaryStage(){
		return this.primaryStage;
	}
	
	public Pane getRootScene(){
		return this.rootScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
