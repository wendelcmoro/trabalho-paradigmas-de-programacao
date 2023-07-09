import java.util.ArrayList;

public class AlunoController{

	private static AlunoController instancia;

    private ArrayList<Aluno> lista;

    private AlunoController() {
    	lista = new ArrayList<>();
    }

    public static AlunoController getInstance() {
        if (instancia == null)
            instancia = new AlunoController();

        return instancia;
    }

	
	public Aluno criaAluno(String nome, String grr, boolean formando) {
		Aluno aluno = new Aluno(nome, grr, formando);
		lista.add(aluno);

		return aluno;
	}
	
	public void atualizaAluno (Aluno aluno, String nome, String grr) {
		aluno.setNome(nome);
		aluno.setGrr(grr);
	}

	// Busca aluno por GRR ou Nome
	public Aluno encontraAluno (String identificador) {
		
		Aluno aluno = null;
		for (Aluno i : lista) {
			
			if (i.getGrr().equals(identificador)) {
				aluno = i;
			}

			else if (i.getNome().contains(identificador)){
				aluno = i;
			}
		}

		return aluno;
	}
	
	public void deletaAluno(Aluno aluno) {
		
		for (Aluno i : lista) {
			if (i.equals(i)) {
				lista.remove(i);
			}
		}
		
	}
}
