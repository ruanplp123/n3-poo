package n3;

public class PersonagemMulti extends Personagem {

	public PersonagemMulti(String nome, String cla, int nivelPoder) {
		super(nome, cla, nivelPoder);
	}

	public String [] ListaPersonagem() {
		return new String[] {"Personagem1", "Personagem2"};
		
	}
	
	public void criaPersonagem (String nome, String cla) {
		System.out.println("Personagem " + nome + " do clã " + cla + " criado com sucesso!");
	}
	
	public void deletaPersonagem (String nome, String cla) {
		System.out.println("Personagem " + nome + " do clã " + cla + " deletado com sucesso!");
		
	}
	
	public void aumentarPoder () {
		int novoNivelPoder = getNivelPoder() + 2;
		 setNivelPoder(novoNivelPoder);
		 System.out.println("Nível de poder aumentado para: " + novoNivelPoder);
		
	}
}
