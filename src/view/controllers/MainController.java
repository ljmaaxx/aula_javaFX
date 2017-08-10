package view.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class MainController{

	private Sistema sis;
	
	@FXML private PasswordField passwordField;
	
	@FXML private TextField userField;
	
	@FXML private Button loginButton;
	
	private String user;
	private String pass;
	
	public void initialize(){
		this.sis = Sistema.getInstance();
		this.user = "thiago";
		this.pass = "123456";
		
		this.passwordField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
                login();
            }
		});
		this.loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				login();
			}
		});
		
		
	}
	
	private void login(){
		Stage stage = null;
		Parent root = null;
		boolean loginOk = false;
		try{
			if(userField.getText().equals(user)){
				if(passwordField.getText().equals(pass)){
					stage = (Stage) loginButton.getScene().getWindow();
					root = (Parent) FXMLLoader.load(getClass().getResource("/view/OverviewTemplate.fxml"));
					loginOk = true;
				}else{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Falha de Login");
					alert.setHeaderText("Informações inválidas");
					alert.setContentText("Senha incorreta");
					alert.showAndWait();
				}
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Falha de Login");
				alert.setHeaderText("Informações inválidas");
				alert.setContentText("Usuário não encontrado");
				alert.showAndWait();
			}
			if(loginOk){
				Scene scene = new Scene(root);
				stage.setScene(scene);
				String tituloAtual = stage.getTitle();
				stage.setTitle(tituloAtual +" - "+ (""+user.charAt(0)).toUpperCase() + user.substring(1, user.length()));
				stage.setResizable(true);
				sis.changeStage(stage);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	private void overViewApp(){
		
	}
	
	
	public void setApp(Sistema sis){
		this.sis = sis;
	}

	
	
}
