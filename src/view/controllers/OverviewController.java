package view.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OverviewController {

	private Sistema sis;
	
	@FXML private MenuItem miListar;
	@FXML private MenuItem miNovo;
	@FXML private MenuItem miAbout;
	
	@FXML
	private void novoContato(){
		sis = Sistema.getInstance();
		Stage stage;
		Parent root;
		Parent old;
		try{
			old = (Parent) FXMLLoader.load(getClass().getResource("/view/OverviewTemplate.fxml"));
			root = (Parent) FXMLLoader.load(getClass().getResource("/view/CadastrarContatoTemplate.fxml"));
			((BorderPane) old).setCenter(root);
			Scene scene = new Scene(old);
			stage = sis.getPrimaryStage();
			stage.setScene(scene);
			sis.changeStage(stage);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@FXML
	private void listarContatos(){
		sis = Sistema.getInstance();
		Stage stage;
		Parent root;
		Parent old;
		try{
			old = (Parent) FXMLLoader.load(getClass().getResource("/view/OverviewTemplate.fxml"));
			root = (Parent) FXMLLoader.load(getClass().getResource("/view/ListarContatosTemplate.fxml"));
			((BorderPane) old).setCenter(root);
			Scene scene = new Scene(old);
			stage = sis.getPrimaryStage();
			stage.setScene(scene);
			sis.changeStage(stage);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setApp(Sistema app){
		this.sis = app;
	}
	
	
	
}
