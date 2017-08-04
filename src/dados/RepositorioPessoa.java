package dados;

import java.util.ArrayList;

import negocios.beans.Pessoa;

public class RepositorioPessoa {
	private static RepositorioPessoa instance;
	private ArrayList<Pessoa> pessoas;
	
	public static RepositorioPessoa getInstance(){
		if(instance == null){
			instance = new RepositorioPessoa();
		}
		return instance;
	}
	
	private RepositorioPessoa(){
		this.pessoas = new ArrayList<Pessoa>();
	}
	
	public void cadastrar(Pessoa p){
		this.pessoas.add(p);
	}
	
	public ArrayList<Pessoa> listarPessoas(){
		return this.pessoas;
	}
	
	
}
