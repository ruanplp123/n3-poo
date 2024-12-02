package n3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonagemSingle extends Personagem {
	private int chefesDerrotados;

	public PersonagemSingle(String nome, String cla, int nivelPoder) {
		super(nome, cla, nivelPoder);
		this.tipoJogo = "Singleplayer";
		this.chefesDerrotados = 0;
	}

	@Override
	public void create() {
		super.create();
		String sql = "INSERT INTO personagem_single (personagem_id, chefes_derrotados) " +
				"SELECT id, ? FROM personagem WHERE nome = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, chefesDerrotados);
			pst.setString(2, nome);
			pst.executeUpdate();
			System.out.println("Configuração de personagem singleplayer salva com sucesso!");
		} catch (SQLException ex) {
			System.out.println("Erro ao configurar personagem singleplayer: " + ex.getMessage());
		}
	}

	public void enfrentarChefe() {
		int dano = 5; // Define o aumento de poder ao derrotar um inimigo
		nivelPoder += dano; // Atualiza o nível de poder no objeto

		System.out.printf("Você enfrentou um inimigo e ganhou +%d de poder! Nível atual: %d%n", dano, nivelPoder);

		// Atualiza o número de monstros derrotados no banco de dados
		String sql = "UPDATE personagem_single " +
				"SET chefes_derrotados = chefes_derrotados + 1 " +
				"WHERE personagem_id = (SELECT id FROM personagem WHERE nome = ?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, nome); // Define o nome do personagem
			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				if(nivelPoder > 20) {
					System.out.println("Ultimo boss enfrentado, você ganhou o jogo! Parabéns");
				}
				System.out.println("Número de monstros derrotados atualizado no banco com sucesso!");
			} else {
				System.out.println("Nenhum personagem encontrado para atualizar.");
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao atualizar monstros derrotados no banco: " + ex.getMessage());
		}
	}

}
