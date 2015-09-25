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

/**
 *
 * 
 */
public class modelo_oficina extends Conexion_oficina{
    
    
    public  DefaultTableModel getTabla(){
        DefaultTableModel tableModel = new DefaultTableModel();
        int Registros = 0;
        String[] ColumNames = {"Codi_Ofic","Nomb_Ofic","Telefono","Email"};
    
    
    
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT "
                    + "count(*) as Total FROM OFICINAS;");
            ResultSet Resultado = pstm.executeQuery();
            Resultado.next();
            Registros = Resultado.getInt("Total");
            Resultado.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        Object[][] data = new String[Registros][4];
          try {
              PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * "
                                + "FROM OFICINAS");
              ResultSet resultado = pstm.executeQuery();
              int i=0;
              while(resultado.next()){
                  data[i][0] = resultado.getString("Codi_Ofic");
                  data[i][1] = resultado.getString("Nomb_Ofic");
                  data[i][2] = resultado.getString("Telefono");
                  data[i][3] = resultado.getString("Email");
                  i++;
                  
              }
              resultado.close();
              
          tableModel.setDataVector(data, ColumNames);
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
          }
          
          return tableModel;
          
      }
    public String[] Llenarjtexoficina(){
        String[] nomb_ofic = new String[33];
        
        String sql ="select Nomb_Ofic from OFICINAS order by Nomb_Ofic" ;
        
        try{
            PreparedStatement sentencia = this.getConexion().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            int i = 0;
            while(resultado.next()){
                nomb_ofic[i] = resultado.getString("Nomb_Ofic");
                i++;
            }
            resultado.close();
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
        return nomb_ofic;
    }
         public String[] LlenarComboBox(){
          String SQL = "Select codi_Tipo from tipos_de_documentos;";
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
            
          SQL = "Select codi_Tipo From  tipos_de_documentos order by codi_Tipo;";
            
            try {
                PreparedStatement Sentencia = this.getConexion().prepareStatement(SQL);
                ResultSet Resultado = Sentencia.executeQuery();
                i = 0;
                while(Resultado.next()){
                  Combo[i] = Resultado.getString("codi_Tipo");                  
                  i++;
                }
                Resultado.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return Combo;  
      }
   

     

 public boolean Nuevo_oficina(String Codi_Ofic, String jtexoficina, String Telefono, String Email){         
           if (Valida_Datos(Codi_Ofic, jtexoficina, Telefono, Email)) {
               System.out.println(jtexoficina);
               String SQl = "Insert into OFICINAS "
                      + "Values('"+Codi_Ofic+"','"+jtexoficina+"','"+Telefono+"','"+Email+"');";
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
 private boolean Valida_Datos (String Codi_Ofic, String jtexoficina, String Telefono, String Email ){
     
          if (Codi_Ofic.equals(" - ")) {
              return false;
          }else {
              if (jtexoficina.length() > 0  && Telefono.length() > 0 && Email.length() > 0){
                  return true;
              }
              else {
                  return false;
              }
          }
      }

   // public Object Llenarjtexoficina() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        
}



    



