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
	      
	      while(opcao !=0) {
	    	  System.out.println("---------MENU--------");
	    	  System.out.println("1- Criar novo jogo---");
	    	  System.out.println("2-  Terminar Jogo----");
	    	  if (jogoIniciado) {
	    		  System.out.println("3- Criar personagem--");
	    		  System.out.println("4- Listar Personagem-");
	    		  System.out.println("5- Excluir Personagem");
	    		  if ("Multiplayer".equalsIgnoreCase(tipoJogo)){
	    			  System.out.println("6. --Aumentar Poder---");
	    		  } else if ("Singleplayer".equalsIgnoreCase(tipoJogo)) {
	    			  System.out.println("7. --Enfrentar Inimigo--"); 
	    		  }	   
	    	  }
	    	  
	    	  System.out.println("8. Sair");
	      }
	}

}
