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
}
