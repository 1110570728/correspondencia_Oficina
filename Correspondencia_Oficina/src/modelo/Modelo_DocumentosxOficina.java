/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Daniela y Paola...
 */
public class Modelo_DocumentosxOficina extends Conexion_oficina{
    
     public  DefaultTableModel getTabla(String nombreoficina){
        DefaultTableModel tableModel = new DefaultTableModel();
        int Registros = 0;
        String[] ColumNames = {"Oficina","Documento","Tiempo de Gestión"};
        String sql = "SELECT count(*) as total FROM DocumentosxOficina";
         try {             
            PreparedStatement pstm = this.getConexion().prepareStatement(sql);
            ResultSet Resultado = pstm.executeQuery();
            Resultado.next();
            Registros = Resultado.getInt("Total");
            Resultado.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
         
         Object[][] data = new String[Registros][3];
          sql = "select O.Nomb_Ofic, TD.Nomb_Tipo, DXO.Tiem_Gest\n"
                + "from DocumentosxOficina DXO join Oficinas O on"
                + "(DXO.Codi_ofic = o.Codi_ofic) join Tipos_De_Documentos TD on (DXO.Codi_Tipo = TD.Codi_Tipo)\n"
                +"where O.Nomb_Ofic = '"+nombreoficina+"';";    
          
    
          
          try {
              PreparedStatement pstm = this.getConexion().prepareStatement(sql);
              ResultSet resultado = pstm.executeQuery();
              int i = 0;
              while(resultado.next()){
                  data[i][0] = resultado.getString("Nomb_Ofic");
                  data[i][1] = resultado.getString("Nomb_Tipo");
                  data[i][2] = resultado.getString("Tiem_Gest");
                  i++;                  
              }
              resultado.close();
              
               tableModel.setDataVector(data, ColumNames);
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
          }
          
          return tableModel;
          
     }
     
     public String[] LlenarComboBox(){
        String[] Codi_Tipo = new String[33];
        
        String sql ="select Codi_Tipo from DocumentosxOficina order by Codi_Tipo" ;
        
        

        try{
            PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            int i = 0;
            while(resultado.next()){
                Codi_Tipo[i] = resultado.getString("Codi_Tipo");
                i++;
            }
            resultado.close();
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
        return Codi_Tipo;
    }
     
     public String codi_ofic(String codioficina){
        String codigoficina = "";
        
        String sql = "select codi_ofic from DocumentosxOficina where codi_ofic = '"
                + codioficina+"';" ;
        
        try{
            PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()){
                codigoficina = resultado.getString("codi_ofic");
                JOptionPane.showMessageDialog(null, codigoficina);
            }
            resultado.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return codigoficina;
    }
     
     public boolean Nuevo_DocumentosxOficina(String Codi_Ofic, String Codi_Tipo, String Tiem_Gest){         
         System.out.println("inicia Almacenamiento");
           if (Valida_Documentos(Codi_Ofic, Codi_Tipo, Tiem_Gest)) {
               System.out.println(Codi_Tipo);
               String SQl = "Insert into DocumentosxOficina(codi_ofic,codi_tipo,tiem_gest) "
                      + "Values('"+Codi_Ofic+"','"+Codi_Tipo+"','"+Tiem_Gest+"');";
               System.out.println(SQl);
                try {
                  PreparedStatement pstm = this.getConexion().prepareStatement(SQl);
                  pstm.execute();
                  pstm.close();
                  return true;
              } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e.getMessage());
              }
              return false;
            }else{ 
               return false;                       
            }  
             
    }

      private boolean Valida_Documentos (String Codi_Ofic, String Codi_Tipo, String Tiem_Gest ){
     
          if (Codi_Ofic.equals(" - ")) {
              return false;
          }else {
              if (Codi_Tipo.length() > 0  && Tiem_Gest.length() > 0){
                  return true;
              }
              else {
                  return false;
              }
          }
      }
      
       public String[] LlenarCombo(){
        String[] Codi_Tipo = new String[33];
        
        String sql ="select Codi_Tipo from DocumentosxOficina order by Codi_Tipo" ;
        
        

        try{
            PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            int i = 0;
            while(resultado.next()){
                Codi_Tipo[i] = resultado.getString("Codi_Tipo");
                i++;
            }
            resultado.close();
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
        return Codi_Tipo;
    }
     
     public String Nomb_ofic(String codioficinas){
        String codigoficina = "";
        
        String sql = "select codi_ofic from OFICINAS where codi_ofic = '"
                + codioficinas+"';" ;
        
        try{
            PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()){
                codigoficina = resultado.getString("codi_ofic");
                JOptionPane.showMessageDialog(null, codigoficina);
            }
            resultado.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return codigoficina;
    }
     
     public boolean Nuevo_OFICINAS(String Codi_Ofic, String Nomb_Ofic, String Telefono, String Email){         
           if (Nuevo_OFICINAS(Codi_Ofic, Nomb_Ofic, Telefono, Email)) {
               System.out.println(Codi_Ofic);
               String SQl = "Insert into OFICINAS "
                      + "Values('"+Codi_Ofic+"','"+Nomb_Ofic+"','"+Telefono+"','"+Email+"');";
               System.out.println(SQl);
                try {
                  PreparedStatement pstm = this.getConexion().prepareStatement(SQl);
                  pstm.execute();
                  pstm.close();
                  return true;
              } catch (Exception e) {
                  JOptionPane.showMessageDialog(null, e.getMessage());
              }
              return false;
            }else{ 
               return false;                       
            }
     }
     public String[] LlenarComboBox1(){
            String SQL = "Select Nomb_Tipo From tipos_de_documentos;";
            int i = 0;
                    
            try {
                PreparedStatement Sentencia = this.getConexion().prepareStatement(SQL);
                ResultSet Resultado = Sentencia.executeQuery();                
                while(Resultado.next()){                
                  i++;
                }      
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            
            String[] Combo = new String[i];
            
            SQL = "Select Nomb_Tipo From tipos_de_documentos order by Nomb_Tipo;";
            
            try {
                PreparedStatement Sentencia = this.getConexion().prepareStatement(SQL);
                ResultSet Resultado = Sentencia.executeQuery();
                i = 0;
                while(Resultado.next()){
                  Combo[i] = Resultado.getString("Nomb_Tipo");
                  i++;
                }
                Resultado.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return Combo;  
         }
     public String[] LlenarComboBox2(){
            String SQL = "Select Nomb_ofic From oficinas;";
            int i = 0;
                    
            try {
                PreparedStatement Sentencia = this.getConexion().prepareStatement(SQL);
                ResultSet Resultado = Sentencia.executeQuery();                
                while(Resultado.next()){                
                  i++;
                }      
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            
            String[] Combo = new String[i];
            
            SQL = "Select Nomb_ofic From oficinas order by Nomb_ofic;";
            
            try {
                PreparedStatement Sentencia = this.getConexion().prepareStatement(SQL);
                ResultSet Resultado = Sentencia.executeQuery();
                i = 0;
                while(Resultado.next()){
                  Combo[i] = Resultado.getString("Nomb_ofic");
                  i++;
                }
                Resultado.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return Combo;  
         }
           
      public String retornarCodigo (String nombre) {                
        String sql = "SELECT codi_tipo FROM tipos_de_documentos WHERE Nomb_tipo='"+ nombre +"';";
        String codigo="";
                
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(sql);
            ResultSet Resultado = pstm.executeQuery();
            Resultado.next();            
            codigo = Resultado.getString("codi_tipo");
            Resultado.close();
            return codigo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return codigo;
        }
        }
        
               public String retornarCodigo1 (String nombre) {                
        String sql = "SELECT codi_ofic FROM oficinas WHERE Nomb_ofic='"+ nombre +"';";
        String codigo="";
                
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(sql);
            ResultSet Resultado = pstm.executeQuery();
            Resultado.next();            
            codigo = Resultado.getString("codi_ofic");
            Resultado.close();
            return codigo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return codigo;
        }
    }
}


