package material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/** Essa classe descreve informações, sobre estoque
 * @see swing.conexao

 * 
 * @author Wallace
 *
 */
public class estoque {
	private int valor;
	private int codigo;
	private String produto;
	public static int totalEstoque;

	estoque() {
		this.produto = "";
		estoque.totalEstoque++;
	}

	estoque(int numCodigo) {
		this();
		this.codigo = numCodigo;
	}



	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int Codigo) {
		this.codigo = Codigo;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public static int getTotalEstoque() {
		return totalEstoque;
	}

	public static void setTotalEstoque(int totalEstoque) {
		estoque.totalEstoque = totalEstoque;
	}
	public boolean cadastrarProduto(int numValor, int numEstoque, String produto) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "insert into estoque set valor=?, codigo=?, produto=?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numValor); 
			ps.setInt(2, numEstoque); 
			ps.setString(3, produto);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar a conta: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean consultarEstoque(int numCodigo) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			String sql = "select * from estoque where codigo=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numCodigo);  
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { 
				return false; 
			} else {
				while (rs.next()) {
					this.valor = rs.getInt("Valor");
					this.codigo = rs.getInt("codigo");
					this.produto = rs.getString("produto");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o estoque: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public boolean atualizarEstoque(int numValor, int numCodigo, String produto) {
		if (!consultarEstoque(numCodigo))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql = "update estoque set produto=?, codigo=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, produto);
				ps.setDouble(2, codigo);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar : " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	public void deletarProduto(int codigo){
		System.out.println("Deletado");
		if (!consultarEstoque(codigo))
			return;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				String sql ="delete FROM estoque where Codigo=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, Integer.toString(codigo));
				ps.executeUpdate();

			}catch (SQLException erro){
				System.out.println(erro);
				return;
			}
			finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
}
