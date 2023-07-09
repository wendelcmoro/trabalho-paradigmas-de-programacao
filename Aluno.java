public class Aluno{

	private String nome;
	private String grr;
	private boolean formando;

	public Aluno(){};
	
	public Aluno(String nome, String grr, boolean formando){
		this.nome = nome;
		this.grr = grr;
		this.formando = formando;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public void setGrr(String grr){
		this.grr = grr;
	}
	
	public String getGrr(){
		return this.grr;
	}

	public boolean isFormando() {
		return formando;
	}

	public void setFormando(boolean formando) {
		this.formando = formando;
	}
}
