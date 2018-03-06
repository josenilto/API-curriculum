package br.com.curriculos.teste;
import java.util.Iterator;
import java.util.Vector;

import br.com.curriculos.api.DaoController;
import br.com.curriculos.dao.Dao;  
  
public class Teste {  
  
   public static void main(String[] args) {  
      Dao Dao = new Dao();  
      DaoController daoCtrl = new DaoController();  
      Dao.setNome("José da Silva");  
      Dao.setRg("12345678X");  
      Dao.setIdade(20);  
      Dao.setEstado("SP");  
      Dao.setCidade("São Paulo");  
  
      daoCtrl.insere(Dao);  
  
      Vector<Dao> resultado = daoCtrl.buscar("12345678X");  
  
      for (Iterator<Dao> iterator = resultado.iterator(); iterator  
            .hasNext();) {  
         Dao p = iterator.next();  
         System.out.println("Dao Encontrada: " + p.getNome());  
      }  
      Dao.setNome("José da Silva Sauro");  
  
      daoCtrl.atualizar(Dao);  
  
      resultado = daoCtrl.buscar("12345678X");  
  
      for (Iterator<Dao> iterator = resultado.iterator(); iterator  
            .hasNext();) {  
         Dao p = iterator.next();  
         System.out.println("Dao Encontrada: " + p.getNome());  
      }  
  
      daoCtrl.apagar("12345678X");  
  
      resultado = daoCtrl.buscar("12345678X");  
  
      if (resultado.size() == 0) {  
         System.out.println("Dao foi apagada com sucesso");  
      } else {  
         System.out.println("A Dao permanece no banco de dados");  
      }  
  
   }  
}  