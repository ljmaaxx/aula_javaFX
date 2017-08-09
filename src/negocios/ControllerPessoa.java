package negocios;

import java.util.ArrayList;

import dados.RepositorioPessoa;
import negocios.beans.Pessoa;

public class ControllerPessoa {
	
	private static ControllerPessoa instance;
	private RepositorioPessoa repositorioPessoa;
	
	public static ControllerPessoa getInstance(){
		if(instance == null){
			instance = new ControllerPessoa();
		}
		return instance;
	}
	
	private ControllerPessoa(){
		this.repositorioPessoa = RepositorioPessoa.getInstance();
	}

	public void cadastrar(Pessoa p){
		this.repositorioPessoa.cadastrar(p);
	}
	
	public ArrayList<Pessoa> listar(){
		return this.repositorioPessoa.listarPessoas();
	}
	
	public void atualizar(Pessoa p){
		this.repositorioPessoa.atualizar(p);
	}
	
}
