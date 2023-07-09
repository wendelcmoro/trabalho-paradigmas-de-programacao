
// Menu
import java.io.*;
import java.time.*;
import java.util.*;

public class Menu {
	public static void main(String args[]) {

		Scanner teclado = new Scanner(System.in);

		exibeInicio();

		Aluno aluno = cadastroAluno();
		uiRequisicao(teclado, aluno, HistoricoController.getInstance().getHistorico(aluno));
	}

	public static void exibeInicio () {
		System.out.println ("------------------------");
		System.out.println ("| Bem vindo ao sistema |");
		System.out.println ("------------------------");
		System.out.println();
	}

	public static Aluno cadastroAluno () {
		System.out.println("É necessário cadastrar um usuário para usar o sistema");
		Scanner teclado = new Scanner(System.in);

		System.out.println ("Por favor digite seu Nome:");
		String nome = teclado.nextLine();

		System.out.println ("Por favor digite seu GRR:");
		String grr = teclado.nextLine();

		boolean formando = false;
		boolean inputCorreto = false;
		while (!inputCorreto) {
			System.out.println ("É formando? (sim/nao)");
			String in = teclado.nextLine();

			if (in.equalsIgnoreCase("sim")) {
				formando = true;
				inputCorreto = true;
			} else if (in.equalsIgnoreCase("nao")) {
				formando = false;
				inputCorreto = true;
			}
		}

		Aluno aluno = AlunoController.getInstance().criaAluno(nome, grr, formando);
		HistoricoController controller = HistoricoController.getInstance();

		String caminhoHistorico = "";
		boolean dadosCorretos = false;

		while (!dadosCorretos) {
			System.out.println("Digite o caminho do seu arquivo csv do histórico: ");
			caminhoHistorico = teclado.nextLine();

			dadosCorretos = controller.importaHistorico(aluno, caminhoHistorico);
			
			if (!dadosCorretos) {
				mensagemErro("Arquivo incorreto");
			}
		}

		return aluno;
	}

	public static void uiRequisicao (Scanner teclado, Aluno aluno, Historico historico) {
		Pedido pedido = new Pedido(aluno);
		PedidoController pedidoController = PedidoController.getInstance();

		pedidoController.adicionaPedido(pedido);

		Grade gradeController = Grade.getInstance(2011);

		int ano;
		int semestre;

		exibeMenu();

		int opcao = 1;

		while (opcao != 0) {

			System.out.println("\n* Digite uma opção (9: Menu, 0: Sair): ");

			try {
				opcao = Integer.parseInt(teclado.nextLine());
			} catch (Exception e) {
				mensagemErro("Digite uma opção válida");
				continue;
			}

			Disciplina disciplina;
			String in;
			switch (opcao) {

				case 1:
					imprimirCursadas(historico);
					break;
				case 2:
					imprimirNaoCursadas(historico, 3);
					break;
				case 3:
					imprimirNaoCursadas(historico, 8);
					break;
				case 4:
				    try {

                        System.out.println("> Digite o ano do semestre:");
                        ano = Integer.parseInt(teclado.nextLine());

                        System.out.println("> Digite o semestre: (1/2)");
                        semestre = Integer.parseInt(teclado.nextLine());
                    
					} catch (Exception e) {
				        mensagemErro("Ano ou semestre inválido");
				        break;
                    }

                    double taxaAprovacao = historico.taxaAprovacaoSemestre(ano, semestre);

					double repFalta = 0;
					for (DisciplinaCursada disc : historico.cursadasNoSemestre(ano, semestre)) {
						if (disc.getStatus().equalsIgnoreCase("Reprovado por Frequência")) {
							repFalta++;
						}
					}

					System.out.println("Taxa de aprovação do semestre passado: " + (taxaAprovacao*100));
					System.out.println("Reprovações por falta: " + repFalta);
					System.out.println("Quantidade de materias possíveis de pedir: " + pedido.getQuantLimite());

					break;

				case 5:
				    if (pedido.getDisciplinas().size() == 0) {
				        mensagemErro("Nenhuma Disciplina presente no pedido.");
                    }
				    
					else {
						System.out.println("\n- - - - Disciplinas no pedido - - - -");
                        for (Disciplina disc : pedido.getDisciplinas()) {
                            System.out.println(disc.nomeCodigo());
                        }
                    }

					break;

				case 6:
					System.out.println("\nSegundo as regras do colegiado, você ainda pode pedir: " + pedido.getQuantLimite());
					System.out.println("Digite o código de uma disciplina a adicionar: ");
					in = teclado.nextLine();

					disciplina = gradeController.getDisciplina(2011, in);
					
					if (disciplina != null) {
						if (historico.cursouDisciplina(disciplina.getCodDisciplina())){
							mensagemErro("Disciplina já cursada");
						} else {
							if (pedido.addDisciplina(disciplina))
								System.out.println("<Disciplina adicionada>");
							else
								mensagemErro("Disciplina já está presente no pedido.");
						}
					} else {
						mensagemErro("Disciplina não encontrada");
					}

					break;

				case 7:
					System.out.println("Digite o código de uma disciplina a remover: ");
					in = teclado.nextLine();

					disciplina = gradeController.getDisciplina(2011, in);
					
					if (disciplina != null) {
						pedido.removeDisciplina(disciplina);
						System.out.println("<Disciplina removida> ");
					} else {
						mensagemErro("Disciplina não encontrada");
					}

					break;

				case 8:
					if(pedidoController.salvaPedido(pedido, "solicitacao.txt"))
						System.out.println("\n* * * * Solicitação salva no arquivo 'solicitacao.txt' * * * *");
					else
						mensagemErro("Não há nenhuma disciplina no pedido");
					break;

				case 9:
					exibeMenu();
					break;

				case 0:
					System.out.println("----------------------------------");
					System.out.println("| Obrigado por usar o programa!! |");
					System.out.println("----------------------------------");

					break;

				default:
					mensagemErro("Escolha uma oção válida");
			}
		}
	}

	public static void mensagemErro (String erro) {
		System.out.println( "\n>>> " + erro + " <<<\n");
	}

	public static void exibeMenu () {
		System.out.println("------------------------------------------------");
		System.out.println("|               MENU REQUISICÃO                |");
		System.out.println("|                                              |");
		System.out.println("| 1 - Exibir disciplinas cursadas              |");
		System.out.println("| 2 - Exibir disciplinas faltantes da barreira |");
		System.out.println("| 3 - Exibir disciplinas não cursadas          |");
		System.out.println("| 4 - Exibir dados de um semestre              |");
		System.out.println("| 5 - Exibir disciplinas escolhidas            |");
		System.out.println("| 6 - Escolher disciplina                      |");
		System.out.println("| 7 - Remover disciplina                       |");
		System.out.println("| 8 - Salvar pedido                            |");
		System.out.println("| 9 - Reexibir menu                            |");
		System.out.println("|                                              |");
		System.out.println("| 0 - Voltar ao menu principal                 |");
		System.out.println("------------------------------------------------");
	}

	public static void imprimirCursadas(Historico historico) {
		System.out.println("\n- - - - DISCIPLINAS CURSADAS - - - -");

		for (int i = 1; i < 9; i++) {
			ArrayList<DisciplinaCursada> disciplinaCursadas = historico.cursadasReferenteSemestre(i);
			if (disciplinaCursadas.size() > 0) {
				System.out.println("--- PERIODO " + i);
				for (DisciplinaCursada disc : disciplinaCursadas) {
					System.out.println(disc.getDisciplina().nomeCodigo());
				}
				System.out.println();
			}

		}
	}

	public static void imprimirNaoCursadas(Historico historico, int periodoMax) {
		System.out.println("\n- - - - DISCIPLINAS NÃO CURSADAS - - - -");

		HistoricoController controller = HistoricoController.getInstance();
		for (int i = 1; i <= periodoMax; i++) {
			ArrayList<Disciplina> disciplinasFaltantes = controller.naoCursadasPeriodo(historico, i);
			if (disciplinasFaltantes.size() > 0) {
				System.out.println("--- PERIODO " + i);
				for (Disciplina disc : disciplinasFaltantes) {
					System.out.println(disc.nomeCodigo());
				}
				System.out.println();
			}

		}
	}

}
