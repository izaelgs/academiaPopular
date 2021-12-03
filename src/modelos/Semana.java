package modelos;

public class Semana {
	int id;
	public int id_aluno;
	public int id_professor;
	public Semana(String login, String senha, String email, String telefone, String nome, String cpf) {				
	}
	
	public Semana(int id, int id_aluno, int id_professor) {		
		this.id = id;
		this.id_aluno = id_aluno;
		this.id_professor = id_professor;
	}
	
	public Semana(int id_aluno, int id_professor) {		
		this.id_aluno = id_aluno;
		this.id_professor = id_professor;
	}

	public int getId_aluno() {
		return id_aluno;
	}
	
	public int getId_professor() {
		return id_professor;
	}
}
