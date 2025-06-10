/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testes;

import com.fatec.atendimentolocalhost.database.Database;
import com.fatec.atendimentolocalhost.exceptions.DBException;
import com.fatec.atendimentolocalhost.exceptions.TipoSeguroValidacaoException;
import com.fatec.atendimentolocalhost.model.dao.TipoSeguroDAO;
import com.fatec.atendimentolocalhost.model.entities.TipoSeguro;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alber
 */
public class TesteSeguroDAO {
//    public static void main(String[] args) {
//        
//        List<TipoSeguro> listaSeguros = new ArrayList<>();
//        
//        Scanner sc = new Scanner(System.in);
//        
//        Database db = new Database();
//        
//        TipoSeguroDAO tsDao = new TipoSeguroDAO(db);
//        
//        try {
//            tsDao.removeAll();
//        }
//        catch (DBException e) {
//            System.out.println("Erro: " + e.getMessage());
//        }
//        
//        int n = sc.nextInt();
//        for (int i=1; i<=n; i++){
//            TipoSeguro ts = new TipoSeguro();
//            
//            ts.setId(i);
//            ts.setDescricao(sc.next());
//            ts.setNome(sc.next());
//            sc.next();
//            try{
//                ts.setTaxa(new BigDecimal(sc.next()));           
//            }
//            catch (TipoSeguroValidacaoException e){
//                System.out.println("Erro: " + e.getMessage());
//            }
//            listaSeguros.add(ts);
//        }
//        
//        for (TipoSeguro ts : listaSeguros){
//            try {
//                tsDao.createWithId(ts);
//            }
//            catch (DBException e){
//                System.out.println("Erro: " + e.getMessage());
//            }
//        }
//            
//        
//        
//    }
}
