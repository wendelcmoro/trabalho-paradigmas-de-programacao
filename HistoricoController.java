import java.util.*;

/*
    Essa classe implementa o padrão Singleton
*/

public class HistoricoController {
    private static HistoricoController instancia;

    private ArrayList<Historico> lista;

    private HistoricoController() {
        lista = new ArrayList<>();
    }

    public static HistoricoController getInstance() {
        if (instancia == null)
            instancia = new HistoricoController();

        return instancia;
    }

    // Importa o histórico de um Aluno
    public boolean importaHistorico (Aluno aluno, String caminhoHistorico) {

        // Instancio um parser
        CsvParser parser = new CsvParser();
        boolean importCorreto = parser.readCsv(caminhoHistorico);

        if (!importCorreto) {
            return false;
        }

        //System.out.println(parser.getDataSize());

        // Crio um novo histórico
        Historico historico = new Historico();
        historico.setAluno(aluno);
        historico.setCodCurso(parser.getElement(1, "COD_CURSO"));
        historico.setNomeCurso(parser.getElement(1, "NOME_CURSO"));
        historico.setGrade(Integer.parseInt(parser.getElement(1, "NUM_VERSAO")));

        // Adiciono o histórico na lista
        lista.add(historico);

        int ano = historico.getGrade();
        Grade grade = Grade.getInstance(ano);

        // Obtenho todas as disciplinas cursadas do histórico
        for (int i = 0; i < parser.getDataSize(); i++) {
            
            DisciplinaCursada cursada = new DisciplinaCursada();

            String codDisciplina = parser.getElement(i, "COD_ATIV_CURRIC");
            Disciplina disciplina = grade.getDisciplina(ano, codDisciplina);

            cursada.setDisciplina(disciplina);

            cursada.setAnoCursado(Integer.parseInt(parser.getElement(i, "ANO")));
            cursada.setFrequencia(Integer.parseInt(parser.getElement(i, "FREQUENCIA")));
            cursada.setNota(Double.parseDouble(parser.getElement(i, "MEDIA_FINAL")));
            cursada.setStatus(parser.getElement(i, "SITUACAO"));
            cursada.setSemestreCursado(Integer.parseInt(parser.getElement(i, "PERIODO").substring(0, 1)));

            historico.addDisciplina(cursada);

        }

        return true;

    }

    // Busca o histórico de um Aluno
    public Historico getHistorico (Aluno aluno) {
        Historico historico = null;

        for (Historico i : lista) {
            if (i.getAluno().equals(aluno)) {
                historico = i;
            }
            
        }

        return historico;
    }

    // Cria um array de disciplinas não cursadas
    public ArrayList<Disciplina> naoCursadas (Historico historico) {
        int anoGrade = historico.getGrade();

        ArrayList <Disciplina> disciplinas = new ArrayList<Disciplina> (Grade.getInstance(anoGrade).getDisciplinas(anoGrade).values());

        for (DisciplinaCursada disc : historico.getDisciplinasCursadas()) {
            
            if (disc.getStatus().equals("APROVADO")) {
                disciplinas.remove(disc.getDisciplina());
            }
            
        }

        //Collection<Disciplina> aux = disciplinas.values();
        //new ArrayList<Disciplina>(aux)
        
        return disciplinas;
    }

    // Cria um array de disciplinas não cursadas
    public ArrayList<Disciplina> naoCursadasPeriodo (Historico historico, int periodo) {
        int anoGrade = historico.getGrade();

        ArrayList <Disciplina> naoCursadas = new ArrayList<>();

        ArrayList<Disciplina> disciplinas = new ArrayList<>(Grade.getInstance(anoGrade).getDisciplinas(anoGrade).values());

        for (Disciplina disciplina : disciplinas ) {
            boolean cursada = false;
            for (DisciplinaCursada disc : historico.getDisciplinasCursadas()) {

                if (disc.getDisciplina().getCodDisciplina().equals(disciplina.getCodDisciplina()) && disc.getStatus().equalsIgnoreCase("aprovado")) {
                    cursada = true;
                }
            }
            if (!(cursada) && (disciplina.getPeriodo() == periodo)) {
                naoCursadas.add(disciplina);
            }
        }
        
        return naoCursadas;
    }

}
