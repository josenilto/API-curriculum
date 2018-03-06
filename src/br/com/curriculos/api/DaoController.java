package br.com.curriculos.api;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import br.com.curriculos.config.Conexao;
import br.com.curriculos.dao.*;

public class DaoController {
 
	   // Configura essas variáveis de acordo com o seu banco  
	   private final String URL = "jdbc:mysql://localhost/javafx_crud",  
	         NOME = "root", SENHA = "senha";  
	  
	   private Connection con;  
	   private Statement comando;  
	  
	   public void apagar(String rg) {  
	      conectar();  
	      try {  
	         comando  
	               .executeUpdate("DELETE FROM Dao WHERE rg = '" + rg  
	                     + "';");  
	      } catch (SQLException e) {  
	         imprimeErro("Erro ao apagar Daos", e.getMessage());  
	      } finally {  
	         fechar();  
	      }  
	   }  
	  
	   public Vector<Dao> buscarTodos() {  
	      conectar();  
	      Vector<Dao> resultados = new Vector<Dao>();  
	      ResultSet rs;  
	      try {  
	         rs = comando.executeQuery("SELECT * FROM Dao");  
	         while (rs.next()) {  
	            Dao temp = new Dao();  
	            // pega todos os atributos da Dao  
	            temp.setRg(rs.getString("rg"));  
	            temp.setNome(rs.getString("nome"));  
	            temp.setIdade(rs.getInt("idade"));  
	            temp.setCidade(rs.getString("cidade"));  
	            temp.setEstado(rs.getString("estado"));  
	            resultados.add(temp);  
	         }  
	         return resultados;  
	      } catch (SQLException e) {  
	         imprimeErro("Erro ao buscar Daos", e.getMessage());  
	         return null;  
	      }  
	   }  
	  
	   public void atualizar(Dao Dao) {  
	      conectar();  
	      String com = "UPDATE Dao SET nome = '" + Dao.getNome()  
	            + "', idade =" + Dao.getIdade() + ", cidade = '"  
	            + Dao.getCidade() + "', estado ='" + Dao.getEstado()  
	            + "' WHERE  rg = '" + Dao.getRg() + "';";  
	      System.out.println("Atualizada!");  
	      try {  
	         comando.executeUpdate(com);  
	      } catch (SQLException e) {  
	         e.printStackTrace();  
	      } finally {  
	         fechar();  
	      }  
	   }  
	  
	   public Vector<Dao> buscar(String rg) {  
	      conectar();  
	      Vector<Dao> resultados = new Vector<Dao>();  
	      ResultSet rs;  
	      try {  
	         rs = comando.executeQuery("SELECT * FROM Dao WHERE rg LIKE '"  
	               + rg + "%';");  
	         while (rs.next()) {  
	            Dao temp = new Dao();  
	            // pega todos os atributos da Dao  
	            temp.setRg(rs.getString("rg"));  
	            temp.setNome(rs.getString("nome"));  
	            temp.setIdade(rs.getInt("idade"));  
	            temp.setCidade(rs.getString("cidade"));  
	            temp.setEstado(rs.getString("estado"));  
	            resultados.add(temp);  
	         }  
	         return resultados;  
	      } catch (SQLException e) {  
	         imprimeErro("Erro ao buscar Dao", e.getMessage());  
	         return null;  
	      }  
	  
	   }  
	  
	   public void insere(Dao Dao){  
	      conectar();  
	      try {  
	         comando.executeUpdate("INSERT INTO Dao VALUES('"  
	               + Dao.getRg() + "', '" + Dao.getNome() + "',"  
	               + Dao.getIdade() + ",'" + Dao.getCidade() + "','"  
	               + Dao.getEstado() + "')");  
	         System.out.println("Inserida!");  
	      } catch (SQLException e) {  
	         imprimeErro("Erro ao inserir Dao", e.getMessage());  
	      } finally {  
	         fechar();  
	      }  
	   }  
	  
	   private void conectar() {  
	      try {  
	         con = Conexao.conexao(URL, NOME, SENHA, Conexao.MYSQL);  
	         comando = con.createStatement();  
	         System.out.println("Conectado!");  
	      } catch (ClassNotFoundException e) {  
	         imprimeErro("Erro ao carregar o driver", e.getMessage());  
	      } catch (SQLException e) {  
	         imprimeErro("Erro ao conectar", e.getMessage());  
	      }  
	   }  
	  
	   private void fechar() {  
	      try {  
	         comando.close();  
	         con.close();  
	         System.out.println("Conexão Fechada");  
	      } catch (SQLException e) {  
	         imprimeErro("Erro ao fechar conexão", e.getMessage());  
	      }  
	   }  
	  
	   private void imprimeErro(String msg, String msgErro) {  
	      JOptionPane.showMessageDialog(null, msg, "Erro crítico", 0);  
	      System.err.println(msg);  
	      System.out.println(msgErro);  
	      System.exit(0);  
	   }  
}
