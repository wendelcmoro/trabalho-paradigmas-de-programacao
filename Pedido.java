import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private ArrayList<Disciplina> disciplinas;
    private Aluno aluno;

    private int faltantesBarreira;
    private int faltantesRequisitos;

    private int quantLimite;

    private String justificativa;

    public Pedido (Aluno aluno) {
        this.disciplinas = new ArrayList<>();
        this.quantLimite = PedidoController.getInstance().calculaNumMaterias(aluno);
        this.aluno = aluno;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Aluno getAluno() {
        return this.aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public int getFaltantesBarreira() {
        return this.faltantesBarreira;
    }

    public void setFaltantesBarreira(int faltantesBarreira) {
        this.faltantesBarreira = faltantesBarreira;
    }

    public int getFaltantesRequisitos() {
        return this.faltantesRequisitos;
    }

    public void setFaltantesRequisitos(int faltantesRequisitos) {
        this.faltantesRequisitos = faltantesRequisitos;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public boolean addDisciplina(Disciplina disciplina) {
        if (disciplinaNoPedido(disciplina))
            return false;

        disciplinas.add(disciplina);
        quantLimite--;
        return true;
    }

    public void removeDisciplina(Disciplina disciplina) {
        if (disciplinaNoPedido(disciplina))
            quantLimite++;
        disciplinas.remove(disciplina);
    }

    public int getQuantLimite() {
        return this.quantLimite;
    }

    private boolean disciplinaNoPedido(Disciplina disciplina) {
        for (Disciplina disc : disciplinas) {
            if (disc == disciplina)
                return true;
        }
        return false;
    }

}