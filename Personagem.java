package n3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Personagem {
	Connection connection = null;
	protected String nome;
	protected String cla;
	protected int nivelPoder;
	protected String tipoJogo;

	public Personagem(String nome, String cla, int nivelPoder) {
		this.nome = nome;
		this.cla = cla;
		this.nivelPoder = nivelPoder;
		this.tipoJogo = "Indefinido";
		this.connection = DatabaseManager.getDatabaseManager();
	}

	public void create() {
		String sql = "INSERT INTO personagem (nome, cla, nivel_poder, tipo_jogo) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, nome);
			pst.setString(2, cla);
			pst.setInt(3, nivelPoder);
			pst.setString(4, tipoJogo);
			pst.executeUpdate();
			System.out.println("Personagem cadastrado com sucesso!");
		} catch (SQLException ex) {
			System.out.println("Erro ao cadastrar personagem: " + ex.getMessage());
		}
	}

	public void readAll() {
		String sql = "SELECT * FROM personagem";
		try (PreparedStatement pst = connection.prepareStatement(sql);
			 ResultSet rst = pst.executeQuery()) {
			System.out.println("---- Lista de Personagens ----");
			while (rst.next()) {
				System.out.printf("ID: %d | Nome: %s | Clã: %s | Nível de Poder: %d | Tipo: %s\n",
						rst.getInt("id"),
						rst.getString("nome"),
						rst.getString("cla"),
						rst.getInt("nivel_poder"),
						rst.getString("tipo_jogo"));
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao listar personagens: " + ex.getMessage());
		}
	}

	public boolean find(String nome) {
		String sql = "SELECT * FROM personagem WHERE nome = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setString(1, nome);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao consultar personagem: " + ex.getMessage());
			return false;
		}
	}

	public String getNome() {
		return nome;
	}

	public void delete(int id) {
		String sql = "DELETE FROM personagem WHERE id = ?";
		try (PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.setInt(1, id);
			int rows = pst.executeUpdate();
			if (rows > 0) {
				System.out.println("Personagem deletado com sucesso!");
			} else {
				System.out.println("Nenhum personagem encontrado com o ID fornecido.");
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao deletar personagem: " + ex.getMessage());
		}
	}
}
