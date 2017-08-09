package dados;

import java.util.ArrayList;

import negocios.beans.Pessoa;

public class RepositorioPessoa {
	private static RepositorioPessoa instance;
	private ArrayList<Pessoa> pessoas;
	private int base;
	
	public static RepositorioPessoa getInstance(){
		if(instance == null){
			instance = new RepositorioPessoa();
		}
		return instance;
	}
	
	private RepositorioPessoa(){
		this.base = 1;
		this.pessoas = new ArrayList<Pessoa>();
	}
	
	public void cadastrar(Pessoa p){
		p.setId(base);
		this.pessoas.add(p);
		this.base++;
	}
	
	public ArrayList<Pessoa> listarPessoas(){
		return this.pessoas;
	}
	
	public void atualizar(Pessoa p){
		this.pessoas.forEach(e -> {
			if(e.getId() == p.getId()){
				e.setNome(p.getNome());
				e.setTelefone(p.getTelefone());
			}
		});
	}
	
	
}
