import java.util.*;


public class Historico {
	private Aluno aluno;

	private List<DisciplinaCursada> disciplinasCursadas;
	private int grade;
	private String codCurso;
	private String nomeCurso;
	private int periodo;


	public Historico () {
		disciplinasCursadas = new ArrayList<>();
	}

	public Historico (Aluno aluno, List<DisciplinaCursada> disciplinasCursadas, int grade, 
			String codCurso, String nomeCurso, int periodo) {

		this.aluno = aluno;
		this.disciplinasCursadas = disciplinasCursadas;
		this.grade = grade;
		this.codCurso = codCurso;
		this.nomeCurso = nomeCurso;
		this.periodo = periodo;
	}


	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<DisciplinaCursada> getDisciplinasCursadas() {
		return this.disciplinasCursadas;
	}

	public void setDisciplinasCursadas(List<DisciplinaCursada> disciplinasCursadas) {
		this.disciplinasCursadas = disciplinasCursadas;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getCodCurso() {
		return this.codCurso;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}

	public String getNomeCurso() {
		return this.nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public int getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	
	public void addDisciplina (DisciplinaCursada disciplina) {
		this.disciplinasCursadas.add(disciplina);
	}

	public boolean aprovadoDisciplina (Disciplina disciplina) {
		for (DisciplinaCursada disc : disciplinasCursadas) {
			if (disc.getDisciplina().equals(disciplina) && disc.isAprovado()) {
				return true;
			}
		}

		return false;
	}

	public double calculaIRA () {

        double ira = 0;
        double divisor = 0;

        for (DisciplinaCursada dc : disciplinasCursadas) {
            ira += dc.getNota() * dc.getDisciplina().getCargaHoraria();
            divisor += dc.getDisciplina().getCargaHoraria();
        }

        ira = ira/divisor;

        return ira;
	}


    // Cria um array de disciplinas cursadas em um certo semestre
    public ArrayList<DisciplinaCursada> cursadasNoSemestre (int ano, int semestre) {
        ArrayList<DisciplinaCursada> cursadas = new ArrayList<>();

        for (DisciplinaCursada i : disciplinasCursadas) {
            if (i.getAnoCursado() == ano && i.getSemestreCursado() == semestre) {
                cursadas.add(i);
            }
        }

        return cursadas;
    }
	
    // Cria um array de disciplinas cursadas referente a um certo periodo
	public ArrayList<DisciplinaCursada> cursadasReferenteSemestre (int periodo) {
        ArrayList<DisciplinaCursada> cursadas = new ArrayList<>();
		
		for (DisciplinaCursada i : disciplinasCursadas) {
            if (i.getDisciplina().getPeriodo() == periodo) {
                cursadas.add(i);
            }
		}
		
		return cursadas;
	}

	public double taxaAprovacaoSemestre (int ano, int semestre) {

        double taxaAprovacao = 0;
        double qntMaterias = 0;

		ArrayList<DisciplinaCursada> cursadas = cursadasNoSemestre (ano, semestre);
		
		for (DisciplinaCursada disc : cursadas) {
			if (disc.getStatus().equalsIgnoreCase("APROVADO")) {
				taxaAprovacao++;
			}
			qntMaterias++;
		}
	
		taxaAprovacao = taxaAprovacao/qntMaterias;
		return taxaAprovacao;
	}

	public boolean cursouDisciplina (String codigoDisciplina) {
		for (DisciplinaCursada disc : disciplinasCursadas) {
			if (disc.getDisciplina().getCodDisciplina().equals(codigoDisciplina) && disc.isAprovado()) {
				return true;
			}
		}
		return false;
	}

}
