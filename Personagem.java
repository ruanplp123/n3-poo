package n3;

public class Personagem {
	
	private String nome;
	private String cla;
	private int nivelPoder; 
	private String tipoJogo; // pro maninho escolher se vai jogar multiplayer ou singleplayer
	
	public Personagem(String nome, String cla, int nivelPoder) {
	this.nome = nome;
	this.cla = cla;
	this.nivelPoder = nivelPoder;
	this.tipoJogo = "Indefinido";
}
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getCla(){
		return cla;
	}
	
	public void setCla(String cla){
		this.cla = cla;
	}
	
	public int getNivelPoder(){
		return nivelPoder;
	}
	
	public void setNivelPoder(int nivelPoder) {
		this.nivelPoder = nivelPoder;
	}
	
	public String getTipoJogo() {
		return tipoJogo;
	}
	
	public void setTipoJogo(String tipoJogo) { 
		 if (tipoJogo.equalsIgnoreCase("Singleplayer") || tipoJogo.equalsIgnoreCase("Multiplayer")) {
	            this.tipoJogo = tipoJogo;
	        } else {
	            System.out.println("Tipo de jogo inválido. Use 'Singleplayer' ou 'Multiplayer'.");
	        }
	}
}


