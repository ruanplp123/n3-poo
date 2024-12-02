package n3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonagemMulti extends Personagem {
	public PersonagemMulti(String nome, String cla, int nivelPoder) {
		super(nome, cla, nivelPoder);
		this.tipoJogo = "Multiplayer";
	}

	@Override
	public void create() {
		super.create();
		String sql = "INSERT INTO personagem_multi (personagem_id) SELECT id FROM personagem WHERE nome = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, nome);
			pst.executeUpdate();
			System.out.println("Configuração de personagem multiplayer salva com sucesso!");
		} catch (SQLException ex) {
			System.out.println("Erro ao configurar personagem multiplayer: " + ex.getMessage());
		}
	}

	public void aumentarPoder() {
		String sql = "UPDATE personagem SET nivel_poder = nivel_poder + 2 WHERE nome = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, nome);
			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Poder do personagem aumentado com sucesso!");
			} else {
				System.out.println("Personagem não encontrado para aumentar o poder.");
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao aumentar o poder do personagem: " + ex.getMessage());
		}
	}
}