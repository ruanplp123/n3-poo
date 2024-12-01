package n3;

public class PersonagemSingle extends Personagem {

	private int chefesDerrotados;
	
	
	public PersonagemSingle(String nome, String cla, int nivelPoder) {
		super(nome, cla, nivelPoder);
		this.chefesDerrotados = 0;
	}
	
	public void enfrentarChefe() {
		if (chefesDerrotados < 5) {
			chefesDerrotados ++;
			System.out.println("Voce derrotou um chefão. Total de chefões derrotados: " + chefesDerrotados);
			if (chefesDerrotados == 5) {
				System.out.println("Parabens, voce derrotou todos os chefões e zerou o jogo");
				
			}
		} else {
			System.out.println("O jogo ja foi zerado");
		}
	}
	
	public int getChefesDerrotados() {
		return chefesDerrotados;
	}

	public String[] listaPersonagem() {
		return new String[] {"Personagem A", "Personagem B", "Personagem C"};
	}
	
	public void criaPersonagem(String nome, String cla, int nivelPoder) {
		
		System.out.println("Personagem " + nome + " do clã " + cla + "Com nivel de poder:" + nivelPoder + "Criado com sucesso!");
	}
	
	public void deletaPersonagem (String nome, String cla) {
		
		System.out.println("Personagem " + nome + " do clã " + cla + "Deletado com sucesso!");

	}
	
	 public void enfrentaInimigo() {
	        int dano = 5; 
	        int novoNivelPoder = getNivelPoder() + dano;
	        setNivelPoder(novoNivelPoder);
	        System.out.println("Você enfrentou um inimigo e derrotou ele + " + dano + " de poder! Nível atual: " + novoNivelPoder);
	 }
}
