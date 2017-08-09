package view.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import negocios.ControllerPessoa;
import negocios.beans.Pessoa;

public class ListarContatosController {

	@FXML private TextField tfNome;
	@FXML private TextField tfTelefone;
	
	@FXML private Button btnSalvar;
	@FXML private Button btnExcluir;
	
	@FXML private TableView<Pessoa> tvContatos;
	
	private ControllerPessoa control;
	private TableColumn<Pessoa, String> nome;
	private TableColumn<Pessoa, String> telefone;
	private ObservableList<Pessoa> tvDados;
	private Pessoa aux;
	
	public void initialize(){
		control = ControllerPessoa.getInstance();
		tvContatos.setEditable(false);
		tvDados = FXCollections.observableArrayList(control.listar());
		nome = new TableColumn<>("Nome");
		nome.setResizable(false);
		telefone = new TableColumn<>("Telefone");
		telefone.setResizable(false);
		tvContatos.getColumns().addAll(nome, telefone);
		nome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		telefone.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("telefone"));
		
		tvContatos.setItems(tvDados);
		tvContatos.setOnMouseClicked(e -> {
			aux = tvContatos.getSelectionModel().getSelectedItem();
			tfNome.setText(aux.getNome());
			tfTelefone.setText(aux.getTelefone());
		});
	}
	
	public void atualizarContato(){
		if(aux != null){
			String nomeNovo = tfNome.getText();
			String telefoneNovo = tfTelefone.getText();
			aux.setTelefone(telefoneNovo);
			aux.setNome(nomeNovo);
			control.atualizar(aux);
			this.updateList();
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Informações inválidas");
			alert.setContentText("Selecione um contato para ser atualizado!!");
			alert.showAndWait();
		}
	}
	
	private void updateList(){
		tvContatos.getItems().clear();
		tvContatos.getItems().addAll(this.control.listar());
	}
	
	
	
	
	
}
