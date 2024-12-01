package n3;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		  Scanner scanner = new Scanner(System.in); //lista pra armazenar os personagens
	      ArrayList<Personagem> personagens = new ArrayList<>(); // variavel pra armazenar o tipo de jogo (multi ou single)	
	      String tipoJogo = null;
	      boolean jogoIniciado = false; // controla se o jogo ta iniciado ou n
	      
	      int opcao = -1; // pra nao encerrar o loop
	      
	      while(opcao != 0) {
	    	  System.out.println("---------MENU--------");
	    	  if(!jogoIniciado) {
	    	  System.out.println("1- Criar novo jogo---");
	    	  System.out.println("2- Terminar Jogo-----");
	    	  }else {
	    		  System.out.println("3- Criar personagem--");
	    		  System.out.println("4- Listar Personagem-");
	    		  System.out.println("5- Excluir Personagem");
	    		  if ("Multiplayer".equalsIgnoreCase(tipoJogo)){
	    			  System.out.println("6. --Aumentar Poder---");
	    		  } else if ("Singleplayer".equalsIgnoreCase(tipoJogo)) {
	    			  System.out.println("6. --Enfrentar Inimigo--"); 
	    		  }	   
	    	  }
	    	  
	    	  System.out.println("8. Sair");
	    	  
	    	  System.out.println("Escolha uma opção: ");
	    	  opcao = scanner.nextInt();
	    	  scanner.nextLine();
	    	  
	    	  switch(opcao) {
	    	  	case 1:
	    	  		System.out.println("Escolha o tipo de jogo (Singleplayer/Multiplayer): ");
	    	  		tipoJogo = scanner.nextLine();
	    	  		if ("Singleplayer".equalsIgnoreCase(tipoJogo) || "Multiplayer".equalsIgnoreCase(tipoJogo)) {
	    	  			jogoIniciado = true;
	    	  			System.out.println("Novo jogo criado: " + tipoJogo);
	    	  		}else {
	    	  			System.out.println("Modo de jogo invalido, para que seja possivel jogar selecione Multiplayer ou Singleplayer!");
	    	  		}
	    	  		break;
	    	  		
	    	  	case 2:
	    	  		if (jogoIniciado) {
	    	  			jogoIniciado = false;
	    	  			tipoJogo = null;
	    	  			System.out.println("Jogo encerrado");
	    	  		}else {
	    	  			System.out.println("Nenhum jogo em andamento");
	    	  		}
	    	  		break;
	    	  		
	    	  	case 3: 
	    	  		if (jogoIniciado) {
	    	  			System.out.println("Digite o nome do personagem: ");
	    	  			String nome = scanner.nextLine();
	    	  			System.out.println("Digite o clã do personagem: ");
	    	  			String cla = scanner.nextLine();
	    	  			System.out.println("Digite o nivel de poder do personagem: ");
	    	  			int nivelPoder = scanner.nextInt();
	    	  			scanner.nextLine();
	    	  			
	    	  			personagens.add(new Personagem (nome, cla, nivelPoder));
	    	  			System.out.println("Personagem criado com sucesso!");
	    	  		} else {
	    	  			System.out.println("Inicie um jogo antes de criar o personagem");
	    	  		}
	    	  		break;
	    	  		
	    	  	case 4:
	    	  		if(jogoIniciado) {
	    	  			if(personagens.isEmpty()) {
	    	  				System.out.println("Nenhum personagem criado");
	    	  			}else {
	    	  				System.out.println("---Personagens---");
	    	  				for (Personagem p : personagens) {
	    	  					System.out.println("- "+ p.getNome() + " Clã " + p.getCla() + " | Poder: " + p.getNivelPoder());
	    	  				}
	    	  			}
	    	  		}else {
	    	  			System.out.println("Inicie um jogo primeiro.");
	    	  		}
	    	  		break;
	    	  		
	    	  	case 5:
	    	  		if (jogoIniciado) {
	    	  			System.out.println("Digite o nome do personagem que deseja excluir:");
	    	  			String nome = scanner.nextLine();
	    	  			for (Personagem p : personagens) {
	    	  				if(p.getNome().equalsIgnoreCase(nome));
	    	  				personagens.remove(p);
	    	  				System.out.println("Personagem removido.");
	    	  				break;
	    	  			}
	    	  			
	    	  		}
	    	  	
				boolean encontrado = false;
				if (!encontrado) {
	    	  			System.out.println("Personagem não encontrado");
	    	  		}
	    	  	
	    	   else {
	    		  System.out.println("Inicie um jogo primeiro");
	    	  }
	    	  break;
	    	  
	    	  	case 6:
	    	  		if ("Multiplayer".equalsIgnoreCase(tipoJogo)) {
	    	  	        if (personagens.isEmpty()) {
	    	  	            System.out.println("Nenhum personagem disponível para aumentar o poder.");
	    	  	        } else {
	    	  	            System.out.println("--- Escolha um personagem para aumentar o poder ---");
	    	  	            for (int i = 0; i < personagens.size(); i++) {
	    	  	            	System.out.println((i + 1) + ". " +personagens.get(i).getNome());
	    	  	            }
	    	  				System.out.println("Digite o numero do personagem");
	    	  				int escolha = scanner.nextInt();
	    	  				scanner.nextLine();
	    	  				
	    	  				if (escolha > 0 && escolha <= personagens.size()) {
	    	  					ArrayList<Personagem> personagem = null;
								Personagem personagemEscolhido = personagem.get(escolha - 1);
	    	  					
	    	  					//isso verifica se é um personagem do modo de jogo multi   	  					
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
	    	  			System.out.println("O modo de jogo atual não permite aumentar o poder.");
	    	  			
	    	  		}  if (jogoIniciado && "Singleplayer".equalsIgnoreCase(tipoJogo)) {
	    	  			if (personagens.isEmpty()) {
	    	  				System.out.println("Nenhum personagem criado. Crie um personagem.");
	    	  			}else {
	    	  				System.out.println("Escolha o personagem para enfrentar o chefão");
	    	  				for (int i = 0; i < personagens.size(); i++) {
	    	  					System.out.println(i + 1 + " - " + personagens.get(i).getNome());
	    	  				   }
	    	  				int escolha = scanner.nextInt();
	    	  				scanner.nextLine();
	    	  				
	    	  				if (escolha > 0 && escolha <= personagens.size()) {
	    	  					Personagem escolhido = personagens.get(escolha - 1);
	    	  					if (escolhido instanceof PersonagemSingle) {
	    	  						((PersonagemSingle)escolhido).enfrentarChefe();
	    	  					}else {
	    	  						System.out.println("Escolha invalida");
	    	  					}
	    	  				}
	    	  					else {
	    	  						System.out.println("Inicie um jogo no modo singleplayer para enfrentar um chefão");
	    	  			}
	    	  				break;
	    	  			}
	    	  		}
	    	  				
	    	  				case 0:
	    	  					System.out.println("Saindo");
	    	  					break;
	    	  	}
	    	  } while (opcao != 0);
	    	  
	    	  scanner.close();
	      }
	

}

