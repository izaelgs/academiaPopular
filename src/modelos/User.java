package modelos;

public class User {
	public String login;
	public String senha;
	public String email;
	public String telefone;
	public String nome;
	public String cpf;
	public String url;	
	private int id;
	public int status;
	
	public User(String login, String senha, String email, String telefone, String nome, String cpf) {		
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.cpf = cpf;		
	}
	
	public User(int status, int id, String login, String senha, String email, String telefone, String nome, String cpf, String url) {	
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		this.cpf = cpf;
		this.url = url;
	}

	public int getId() {
		return id;
	}
	
}
