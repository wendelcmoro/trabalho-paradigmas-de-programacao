import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grade {
    private static Grade instancia;

    private HashMap<String, Disciplina> grade2019;
    private HashMap<String, Disciplina> grade2011;

    private int disciplinasObrigatoriasNecessarias2019;
    private int disciplinasObrigatoriasNecessarias2011;

    private int disciplinasOptativasNecessarias2019;
    private int disciplinasOptativasNecessarias2011;

    private Grade() {
        this.disciplinasObrigatoriasNecessarias2019 = 37;
        this.disciplinasObrigatoriasNecessarias2011 = 34;

        this.disciplinasOptativasNecessarias2019 = 6;
        this.disciplinasOptativasNecessarias2011 = 6;

        this.grade2019 = new HashMap<>();
        this.grade2011 = new HashMap<>();
    }

    public static Grade getInstance(int ano) {
        if (instancia == null)
            instancia = new Grade();
        instancia.getGrade(ano);
        return instancia;
    }

    private void getGrade(int ano) {
        if (ano == 2019) {
            if (this.grade2019.size() == 0)
                leGrade(2019);
        }

        else if (ano == 2011) {
            if (this.grade2011.size() == 0)
                leGrade(2011);
        }
    }

    private void leGrade(int ano) {
        CsvParser csvParser = new CsvParser();
        csvParser.readCsv("data/grade" + ano + ".csv");

        HashMap<String, Disciplina> grade = new HashMap<>();
        for (int i = 0; i < csvParser.getDataSize(); i++) {
            String codDisciplina = csvParser.getElement(i, "COD_DISCIPLINA");
            int versao = Integer.parseInt(csvParser.getElement(i, "NUM_VERSAO"));
            int periodo = Integer.parseInt(csvParser.getElement(i, "PERIODO_IDEAL"));
            int cargaHoraria = Integer.parseInt(csvParser.getElement(i, "CH_TOTAL"));
            String codCurso = csvParser.getElement(i, "COD_CURSO");
            String nomeCurso = csvParser.getElement(i, "NOME_UNIDADE");
            String nomeDisciplina = csvParser.getElement(i, "NOME_DISCIPLINA");
            String tipoDisciplina = csvParser.getElement(i, "TIPO_DISCIPLINA");
            boolean ativa = true;

            Disciplina disciplina = new Disciplina(codDisciplina, versao, periodo, cargaHoraria, codCurso, nomeCurso,
                    nomeDisciplina, tipoDisciplina, ativa);

            grade.put(codDisciplina, disciplina);
        }

        if (ano == 2011)
            this.grade2011 = grade;
        else if (ano == 2019)
            this.grade2019 = grade;
    }

    public Disciplina getDisciplina(int ano, String codDisciplina) {
        if (ano == 2011)
            return grade2011.get(codDisciplina);
        else if (ano == 2019)
            return grade2019.get(codDisciplina);
        return null;
    }

    public HashMap<String, Disciplina> getDisciplinas(int ano) {
        if (ano == 2019) {
            return grade2019;
        }

        else if (ano == 2011) {
            return grade2011;
        }

        else {
            return null;
        }
    }

    public int getDisciplinasObrigatoriasNecessarias(int ano) {
        if (ano == 2019)
            return disciplinasObrigatoriasNecessarias2019;
        else if (ano == 2011)
            return disciplinasObrigatoriasNecessarias2011;
        return -1;
    }

    public int getDisciplinasOptativasNecessarias(int ano) {
        if (ano == 2019)
            return disciplinasOptativasNecessarias2019;
        else if (ano == 2011)
            return disciplinasOptativasNecessarias2011;
        return -1;
    }

    public List<Disciplina> getDisciplinasPeriodo(int ano, int periodo) {
        HashMap<String, Disciplina> disciplinas = this.getDisciplinas(ano);
        List<Disciplina> discPeriodo = new ArrayList<>();
        for (String codDisciplina : disciplinas.keySet()) {
            if (disciplinas.get(codDisciplina).getPeriodo() == periodo)
                discPeriodo.add(disciplinas.get(codDisciplina));
        }

        return discPeriodo;
    }
}
