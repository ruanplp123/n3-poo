package n3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Personagem> personagens = new ArrayList<>(); // Lista de personagens
		int tipoJogo = 0;
		boolean jogoIniciado = false; // Controla se o jogo está iniciado ou não
		int opcao = -1; // Para não encerrar o loop

		while (opcao != 0) {
			System.out.println("---------MENU--------");
			if (!jogoIniciado) {
				System.out.println("1- Criar novo jogo");
				System.out.println("2- Terminar Jogo");
				System.out.println("3- Sair");
			} else {
				System.out.println("1- Criar personagem");
				System.out.println("2- Listar Personagem");
				System.out.println("3- Excluir Personagem");
				if (tipoJogo == 2) {
					System.out.println("4- Aumentar Poder");
				} else if (tipoJogo == 1) {
					System.out.println("4- Enfrentar Inimigo");
				}
				System.out.println("5- Sair");
			}

			System.out.println("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine();

			if (!jogoIniciado) {
				switch (opcao) {
					case 1:
						System.out.println("Escolha o tipo de jogo: ");
						System.out.println("1: Singleplayer");
						System.out.println("2: Multiplayer");
						tipoJogo = scanner.nextInt();
						if (tipoJogo == 1 || tipoJogo == 2) {
							jogoIniciado = true;
							System.out.println("Novo jogo criado: " + tipoJogo);
						} else {
							System.out.println("Modo de jogo inválido, selecione Multiplayer ou Singleplayer!");
						}
						break;

					case 2:
						jogoIniciado = false;
						tipoJogo = 0;
						System.out.println("Jogo encerrado");
						break;

					case 3:
						System.out.println("Saindo");
						opcao = 0;
						break;
				}
			} else {
				switch (opcao) {
					case 1:
						System.out.println("Digite o nome do personagem: ");
						String nome = scanner.nextLine();
						System.out.println("Digite o clã do personagem: ");
						String cla = scanner.nextLine();
						System.out.println("Digite o nível de poder do personagem: ");
						int nivelPoder = scanner.nextInt();
						scanner.nextLine();

						if (tipoJogo == 1) {
							personagens.add(new PersonagemSingle(nome, cla, nivelPoder));
						} else {
							personagens.add(new PersonagemMulti(nome, cla, nivelPoder));
						}
						System.out.println("Personagem criado com sucesso!");
						break;

					case 2:
						if (personagens.isEmpty()) {
							System.out.println("Nenhum personagem criado");
						} else {
							System.out.println("---Personagens---");
							for (Personagem p : personagens) {
								System.out.println("- " + p.getNome() + " Clã " + p.getCla() + " | Poder: " + p.getNivelPoder());
							}
						}
						break;

					case 3:
						System.out.println("Digite o nome do personagem que deseja excluir:");
						nome = scanner.nextLine();
						boolean encontrado = false;
						for (Personagem p : personagens) {
							if (p.getNome().equalsIgnoreCase(nome)) {
								personagens.remove(p);
								encontrado = true;
								System.out.println("Personagem removido.");
								break;
							}
						}
						if (!encontrado) {
							System.out.println("Personagem não encontrado");
						}
						break;

					case 4:
						if (tipoJogo == 2) {
							if (personagens.isEmpty()) {
								System.out.println("Nenhum personagem disponível para aumentar o poder.");
							} else {
								System.out.println("--- Escolha um personagem para aumentar o poder ---");
								for (int i = 0; i < personagens.size(); i++) {
									System.out.println((i + 1) + ". " + personagens.get(i).getNome());
								}
								System.out.println("Digite o número do personagem");
								int escolha = scanner.nextInt();
								scanner.nextLine();

								if (escolha > 0 && escolha <= personagens.size()) {
									Personagem personagemEscolhido = personagens.get(escolha - 1);

									// Verifica se o personagem é do tipo Multiplayer
									if (personagemEscolhido instanceof PersonagemMulti) {
										((PersonagemMulti) personagemEscolhido).aumentarPoder();
									} else {
										System.out.println("O personagem selecionado não é do modo Multiplayer.");
									}
								} else {
									System.out.println("Opção inválida");
								}
							}
						} else {
							if (personagens.isEmpty()) {
								System.out.println("Nenhum personagem criado. Crie um personagem.");
							} else {
								System.out.println("Escolha o personagem para enfrentar o chefão:");
								for (int i = 0; i < personagens.size(); i++) {
									System.out.println((i + 1) + " - " + personagens.get(i).getNome());
								}
								int escolha = scanner.nextInt();

								if (escolha > 0 && escolha <= personagens.size()) {
									PersonagemSingle escolhido = (PersonagemSingle) personagens.get(escolha - 1);
									if (escolhido instanceof PersonagemSingle) {
										escolhido.enfrentarChefe();
									} else {
										System.out.println("Escolha inválida");
									}
								}
							}
						}
						break;

					case 5:
						System.out.println("Saindo");
						opcao = 0;
						break;
				}
			}
		}

		scanner.close();
	}
}
