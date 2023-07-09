import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class PedidoController {

    private ArrayList<Pedido> pedidos;
    private static PedidoController instancia;

    private PedidoController() {
        this.pedidos = new ArrayList<>();
    }

    public static PedidoController getInstance() {
        if (instancia == null)
            instancia = new PedidoController();

        return instancia;
    }

    public void adicionaPedido (Pedido pedido) {
        pedidos.add(pedido);
    }

    public Pedido buscaPedido (Aluno aluno) {
        for (Pedido p : pedidos) {
            if (p.getAluno() == aluno) {
                return p;
            }
        }

        return null;
    }

    // Calcula o número de quebras permitidas
    public int calculaNumMaterias(Aluno aluno) {

        Historico historico = HistoricoController.getInstance().getHistorico(aluno);

        double ira = historico.calculaIRA();
        if (ira >= 80.0) {
            return 1000;
        }
        
        LocalDate hoje = LocalDate.now();
        int ano = hoje.getYear();
        int semestre = hoje.getMonthValue() / 7;
        
        if (semestre == 0) {
            ano--;
            semestre = 2;
        }

        double taxaAprovacao = historico.taxaAprovacaoSemestre(ano, semestre);
        if (taxaAprovacao > 2/3) {
            return 5;
        } else if (taxaAprovacao > 1/2) {
            return 4;
        } else {
            return 3;
        }
    }

    // Checa se uma quebra é válida
    public boolean isValidaQuebra (Aluno aluno, Disciplina disciplina) {
        if (disciplina.isOptativa()) {
            return false;
        }

        if (disciplina.getCodDisciplina().equals("CI215")) {
            Historico historico = HistoricoController.getInstance().getHistorico(aluno);

            Disciplina disc = Grade.getInstance(2011).getDisciplina(2011, "CI212");
            
            return historico.aprovadoDisciplina(disc);
        }

        return true;
    }

    public boolean salvaPedido(Pedido pedido, String caminho) {
        try {
            ManipulaArquivo ma = new ManipulaArquivo();
            ma.salvarArquivo(caminho, Solicitacao.gerarSolicitacao(pedido));
            
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
