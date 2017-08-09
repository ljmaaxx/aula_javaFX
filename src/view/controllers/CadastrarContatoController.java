package view.controllers;

import dados.RepositorioPessoa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import negocios.ControllerPessoa;
import negocios.beans.Pessoa;

public class CadastrarContatoController {
	
	private Sistema sis;
	private ControllerPessoa rep;

	@FXML private TextField tfNome;
	@FXML private TextField tfTelefone;
	
	@FXML
	private void limparCampos(){
		tfNome.setText("");
		tfTelefone.setText("");
	}
	
	@FXML
	private void salvarContato(){
		rep = ControllerPessoa.getInstance();
		String nome = tfNome.getText();
		String telefone = tfTelefone.getText();
		if(nome != null && nome != "" && telefone != null && telefone != ""){
			Pessoa p = new Pessoa(nome, telefone);
			rep.cadastrar(p);
			tfNome.setText("");
			tfTelefone.setText("");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucesso");
			alert.setHeaderText("Cadastro Contato");
			alert.setContentText("Contato cadastrado com sucesso");
			alert.showAndWait();
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Informações inválidas");
			alert.setContentText("Informações fornecidas são inválidas");
			alert.showAndWait();
		}
	}
	
	private void setApp(Sistema app){
		this.sis = app;
	}
	
	
	
	
}
