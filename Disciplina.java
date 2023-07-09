public class Disciplina {
    private String codDisciplina;
    private int versao;
	private int periodo;
	private int cargaHoraria;
    private String codCurso;
	private String nomeCurso;
	private String nomeDisciplina;
	private String tipoDisciplina;
	private boolean ativa;

	public Disciplina(String codDisciplina, int versao , int periodo, int cargaHoraria, String codCurso, String nomeCurso,
					  String nomeDisciplina, String tipoDisciplina, boolean ativa) {
		this.codDisciplina = codDisciplina;
		this.versao = versao;
		this.periodo = periodo;
		this.cargaHoraria = cargaHoraria;
		this.codCurso = codCurso;
		this.nomeCurso = nomeCurso;
		this.nomeDisciplina = nomeDisciplina;
		this.tipoDisciplina = tipoDisciplina;
		this.ativa = ativa;
	}

	public String getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getTipoDisciplina() {
		return tipoDisciplina;
	}

	public void setTipoDisciplina(String tipoDisciplina) {
		this.tipoDisciplina = tipoDisciplina;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public boolean isOptativa() {
		return this.tipoDisciplina.toLowerCase().equals("optativa");
	}

	public boolean isObrigatoria() {
		return this.tipoDisciplina.toLowerCase().equals("obrigatória");
	}

	public String nomeCodigo() {
		return this.codDisciplina + " - " + this.nomeDisciplina;
	}
}
