package negocios;

import java.util.ArrayList;

import dados.RepositorioPessoa;
import negocios.beans.Pessoa;

public class ControllerPessoa {
	
	private RepositorioPessoa repositorioPessoa;
	
	public ControllerPessoa(){
		this.repositorioPessoa = RepositorioPessoa.getInstance();
	}

	public void cadastrar(Pessoa p){
		this.repositorioPessoa.cadastrar(p);
	}
	
	public ArrayList<Pessoa> listar(){
		return this.repositorioPessoa.listarPessoas();
	}
	
}
