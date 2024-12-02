package n3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Personagem> personagens = new ArrayList<>(); // Lista de personagens
		int tipoJogo = 0; // 1: Singleplayer, 2: Multiplayer
		boolean jogoIniciado = false; // Controla se o jogo está iniciado
		int opcao = -1; // Opção do menu

		while (opcao != 0) {
			System.out.println("--------- MENU ---------");
			if (!jogoIniciado) {
				System.out.println("1- Criar novo jogo");
				System.out.println("2- Sair");
			} else {
				System.out.println("1- Criar personagem");
				System.out.println("2- Listar personagens");
				System.out.println("3- Excluir personagem");
				if (tipoJogo == 2) {
					System.out.println("4- Aumentar poder (Multiplayer)");
				} else if (tipoJogo == 1) {
					System.out.println("4- Enfrentar inimigo (Singleplayer)");
				}
				System.out.println("5- Sair");
			}

			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine(); // Limpa o buffer do scanner

			if (!jogoIniciado) {
				switch (opcao) {
					case 1: // Criar novo jogo
						System.out.println("Escolha o tipo de jogo:");
						System.out.println("1: Singleplayer");
						System.out.println("2: Multiplayer");
						tipoJogo = scanner.nextInt();
						scanner.nextLine(); // Limpa o buffer do scanner

						if (tipoJogo == 1 || tipoJogo == 2) {
							jogoIniciado = true;
							System.out.println("Novo jogo criado: " + (tipoJogo == 1 ? "Singleplayer" : "Multiplayer"));
						} else {
							System.out.println("Modo de jogo inválido! Escolha 1 ou 2.");
						}
						break;

					case 2: // Sair
						System.out.println("Saindo...");
						opcao = 0;
						break;

					default:
						System.out.println("Opção inválida.");
				}
			} else {
				switch (opcao) {
					case 1: // Criar personagem
						System.out.print("Digite o nome do personagem: ");
						String nome = scanner.nextLine();

						System.out.print("Digite o clã do personagem: ");
						String cla = scanner.nextLine();

						System.out.print("Digite o nível de poder do personagem: ");
						int nivelPoder = scanner.nextInt();
						scanner.nextLine();

						Personagem personagem;
						if (tipoJogo == 1) {
							personagem = new PersonagemSingle(nome, cla, nivelPoder);
						} else {
							personagem = new PersonagemMulti(nome, cla, nivelPoder);
						}

						personagem.create();
						personagens.add(personagem);
						System.out.println("Personagem criado com sucesso!");
						break;

					case 2: // Lista os personagens
						if (personagens.isEmpty()) {
							System.out.println("Nenhum personagem criado.");
						} else {
							System.out.println("---- Lista de Personagens ----");
							for (Personagem p : personagens) {
								System.out.printf("Nome: %s | Clã: %s | Nível de Poder: %d | Tipo: %s\n",
										p.getNome(), p.cla, p.nivelPoder, p.tipoJogo);
							}
						}
						break;1

					case 3: // Exclui um personagem
						System.out.print("Digite o nome do personagem que deseja excluir: ");
						nome = scanner.nextLine();

						boolean encontrado = false;
						for (Personagem p : personagens) {
							if (p.find(nome)) {
								personagens.remove(p);
								encontrado = true;
								System.out.println("Personagem removido.");
								break;
							}
						}

						if (!encontrado) {
							System.out.println("Personagem não encontrado.");
						}
						break;

					case 4: // Ações específicas por tipo de jogo
						if (tipoJogo == 2) { // Multiplayer: Aumentar poder
							if (personagens.isEmpty()) {
								System.out.println("Nenhum personagem disponível para aumentar o poder.");
							} else {
								System.out.println("---- Escolha um personagem para aumentar o poder ----");
								for (int i = 0; i < personagens.size(); i++) {
									System.out.println((i + 1) + ". " + personagens.get(i).getNome());
								}

								System.out.print("Digite o número do personagem: ");
								int escolha = scanner.nextInt();
								scanner.nextLine();

								if (escolha > 0 && escolha <= personagens.size()) {
									Personagem personagemEscolhido = personagens.get(escolha - 1);

									if (personagemEscolhido instanceof PersonagemMulti) {
										((PersonagemMulti) personagemEscolhido).aumentarPoder();
									} else {
										System.out.println("O personagem selecionado não é do modo Multiplayer.");
									}
								} else {
									System.out.println("Opção inválida.");
								}
							}
						} else { // Singleplayer: Enfrentar inimigo
							if (personagens.isEmpty()) {
								System.out.println("Nenhum personagem criado. Crie um personagem.");
							} else {
								System.out.println("---- Escolha o personagem para enfrentar o chefão ----");
								for (int i = 0; i < personagens.size(); i++) {
									System.out.println((i + 1) + ". " + personagens.get(i).getNome());
								}

								System.out.print("Digite o número do personagem: ");
								int escolha = scanner.nextInt();

								if (escolha > 0 && escolha <= personagens.size()) {
									Personagem personagemEscolhido = personagens.get(escolha - 1);

									if (personagemEscolhido instanceof PersonagemSingle) {
										((PersonagemSingle) personagemEscolhido).enfrentarChefe();
									} else {
										System.out.println("O personagem selecionado não é do modo Singleplayer.");
									}
								}
							}
						}
						break;

					case 5: // Sair
						System.out.println("Saindo...");
						opcao = 0;
						break;

					default:
						System.out.println("Opção inválida.");
				}
			}
		}

		scanner.close();
	}
}
