/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Modelo_DocumentosxOficina;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vista.FrmDocumentosxOficina;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.FrmOficinas;

/**
*
 * @author Daniela y Paola...
 */
public class controladorDocumentosxOficina implements ActionListener,MouseListener{
    FrmDocumentosxOficina VtnDocuxOfic ;
    //FrmOficinas vtnDocumentos;
    //Producto_Modelo modelo = new Producto_Modelo();//validad el boton ingresar
    //private Object modelo;
    Modelo_DocumentosxOficina modelo = new  Modelo_DocumentosxOficina();
    
    public enum DocumentoMVC{
        __Nuevo,
        __Guardar,
        __VER__OFICINA
    }
    
    public controladorDocumentosxOficina(FrmDocumentosxOficina vista){
       this.VtnDocuxOfic = vista; 
       
       for (int i = 0; i < modelo.LlenarComboBox1().length; i++) {
        vista.jComcodigodocumento.addItem(modelo.LlenarComboBox1()[i]);
        }
       for (int i = 0; i < modelo.LlenarComboBox2().length; i++) {
        vista.jCombOficinas.addItem(modelo.LlenarComboBox2()[i]);
        }
    }
     
    public void iniciar(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows."
                    + "WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(VtnDocuxOfic);
            VtnDocuxOfic.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {
              JOptionPane.showMessageDialog(VtnDocuxOfic, "Error de driver de "
                      + "video: "+ex.getMessage());
          }
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
        
       this.VtnDocuxOfic.jBtnnuevo.setActionCommand("__Nuevo");
       this.VtnDocuxOfic.jBtnnuevo.addActionListener((ActionListener) this);
      
       this.VtnDocuxOfic.jBtnguardar.setActionCommand("__Guardar");
       this.VtnDocuxOfic.jBtnguardar.addActionListener(this);
       
       this.VtnDocuxOfic.jCombOficinas.setActionCommand("__VER__OFICINA");
        this.VtnDocuxOfic.jCombOficinas.addActionListener(this);
      
       this.VtnDocuxOfic.jTabla.addMouseListener((MouseListener) this);
       this.VtnDocuxOfic.jTabla.setModel(new DefaultTableModel());
    }
 
 
 public void mouseClicked(MouseEvent e) {
        if(e.getButton()== 1)//boton izquierdo
        {
             int Fila = this.VtnDocuxOfic.jTabla.rowAtPoint(e.getPoint());
             if (Fila > -1){
                this.VtnDocuxOfic.jComcodigodocumento.addItem(String.valueOf(
                        this.VtnDocuxOfic.jTabla.getValueAt(Fila, 0)));
                
                this.VtnDocuxOfic.jCombOficinas.addItem(String.valueOf(
                        this.VtnDocuxOfic.jTabla.getValueAt(Fila, 1)));
                
                this.VtnDocuxOfic.tiempo.setText(String.valueOf(
                        this.VtnDocuxOfic.jTabla.getValueAt(Fila, 2)));
                
                this.VtnDocuxOfic.jCombOficinas.setEditable(false);
                this.VtnDocuxOfic.jComcodigodocumento.setEditable(false);
                this.VtnDocuxOfic.tiempo.setEditable(false);
               
             }
        }
    }

   public void mousePressed(MouseEvent e) {}
   public void mouseReleased(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) { }

    public void actionPerformed(ActionEvent e) {
        System.out.println("...");
        switch (DocumentoMVC.valueOf(e.getActionCommand())){
            
            case __Nuevo:{                
                this.VtnDocuxOfic.tiempo.setText("");
                break;                                     
            }
            case __VER__OFICINA:{
                    this.VtnDocuxOfic.jTabla.setModel(this.modelo.getTabla(this.VtnDocuxOfic.jCombOficinas.getSelectedItem().toString()));
                    this.VtnDocuxOfic.jTabla.requestFocus();
                    break;
                }
             
            
            case __Guardar:{
                    System.out.println("Guardando...");
                    if (this.modelo.Nuevo_DocumentosxOficina(modelo.retornarCodigo1((String)this.VtnDocuxOfic.jCombOficinas.getSelectedItem()).toString(),
                                                            modelo.retornarCodigo((String)this.VtnDocuxOfic.jComcodigodocumento.getSelectedItem()),
                                                            this.VtnDocuxOfic.tiempo.getText())){
                        JOptionPane.showMessageDialog(VtnDocuxOfic,"OFICINA CREADA!!. ");
                   //modelo.retornarCodigo((String)this.VtnDocuxOfic.jCombOficinas.getSelectedItem()); 
                   //modelo.retornarCodigo((String)this.VtnDocuxOfic.jComcodigodocumento.getSelectedItem());                   
                   this.VtnDocuxOfic.tiempo.getText();
                   this.VtnDocuxOfic.jTabla.setModel(this.modelo.getTabla(this.VtnDocuxOfic.jCombOficinas.getSelectedItem().toString()));
                   this.VtnDocuxOfic.jTabla.requestFocus();
                    }else {
                        JOptionPane.showMessageDialog(VtnDocuxOfic,"Datos incompletos!!. ");
                    }
                      
                   //JOptionPane.showMessageDialog(VtnDocuxOfic,"DATOS INCORRECTOS!!.");
                    
                   
         break;
       }
     }  
   }  
}   
        
   








    
    
    
    
    

