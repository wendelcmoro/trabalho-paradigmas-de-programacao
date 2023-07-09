public class DisciplinaCursada {
    private Disciplina disciplina;

    private String status;
    private double nota;
    private int semestreCursado;
    private int anoCursado;
    private int frequencia;

    public DisciplinaCursada () {
    }

    public DisciplinaCursada (Disciplina disciplina, String status) {
        this.status = status;
        this.disciplina = disciplina;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }

    public void setSemestreCursado(int semestreCursado) {
        this.semestreCursado = semestreCursado;
    }

    public int getSemestreCursado() {
        return semestreCursado;
    }

    public int getAnoCursado() {
        return anoCursado;
    }

    public void setAnoCursado(int anoCursado) {
        this.anoCursado = anoCursado;
    }
    
    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public boolean isAprovado() {
        return status.toLowerCase().equals("aprovado");
    }
}