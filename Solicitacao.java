import java.util.ArrayList;
import java.util.List;

public class Solicitacao {
    public static List<String> gerarSolicitacao(Pedido pedido) {
        Aluno aluno = pedido.getAluno();

        List<String> solicitacao = new ArrayList<>();
        solicitacao.add("Nome: " + aluno.getNome());

        solicitacao.add("GRR: " + aluno.getGrr());

        HistoricoController controller = HistoricoController.getInstance();
        Historico historicoAluno = controller.getHistorico(aluno);
        solicitacao.add("Curso: " + historicoAluno.getNomeCurso());

        if (aluno.isFormando())
            solicitacao.add("É formando? Sim");
        else
            solicitacao.add("É formando? Não");

        String naoCursadas = "Disciplinas não cursadas: ";

        for (int i = 1; i <= 3; i++) {
            ArrayList<Disciplina> disciplinasFaltantes = controller.naoCursadasPeriodo(historicoAluno, i);
            if (disciplinasFaltantes.size() > 0) {
                for (Disciplina disc : disciplinasFaltantes) {
                    naoCursadas += " " + disc.getCodDisciplina();
                }
            }

        }
        solicitacao.add(naoCursadas);

        int anoGrade = historicoAluno.getGrade();
        Grade grade = Grade.getInstance(anoGrade);

        int obrigatoriasCursadas = 0;
        int optativasCursadas = 0;
        for (DisciplinaCursada disciplina : historicoAluno.getDisciplinasCursadas()) {
            if (disciplina.getDisciplina().isObrigatoria()) {
                obrigatoriasCursadas++;
            }
            else {
                optativasCursadas++;
            }
        }

        solicitacao.add("Quantidade de disciplinas obrigatorias que ainda precisa cursar: " +
                (grade.getDisciplinasObrigatoriasNecessarias(anoGrade) - obrigatoriasCursadas));
        solicitacao.add("Quantidade de disciplinas optativas que ainda precisa cursar: " +
                (grade.getDisciplinasOptativasNecessarias(anoGrade) - optativasCursadas));

        String disciplinasSolicitadas = "";
        for (Disciplina disciplina : pedido.getDisciplinas()) {
            disciplinasSolicitadas += disciplina.getCodDisciplina() + ", ";
        }
        disciplinasSolicitadas = disciplinasSolicitadas.substring(0, disciplinasSolicitadas.length() - 2);
        solicitacao.add("Disciplinas solicitadas além da barreira / requisito: " + disciplinasSolicitadas);

        return solicitacao;
    }
}
