/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.modelo_oficina;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vista.FrmOficinas;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Daniela y paola
 */
public class Controlador_oficina implements ActionListener,MouseListener{
    
    FrmOficinas VtnOficinas ;
    //Producto_Modelo modelo = new Producto_Modelo();//validad el boton ingresar
    //private Object modelo;
    modelo_oficina modelo = new  modelo_oficina();
    
      public enum oficinaMVC{
        __Nuevo,
        __Adicionar,
    }
     public Controlador_oficina(FrmOficinas vista){
       this.VtnOficinas = vista; for (int i = 0; i < modelo.Llenarjtexoficina().length; i++) {
        vista.jtexoficina.setText(modelo.Llenarjtexoficina()[i]);
       
        }
    }
 public void iniciar(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows."
                    + "WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(VtnOficinas);
            VtnOficinas.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {
              JOptionPane.showMessageDialog(VtnOficinas, "Error de driver de "
                      + "video: "+ex.getMessage());
          }
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}


        //this.VtnOficinas.Nuevo.setActionCommand("__Nuevo");
        //this.VtnOficinas.Nuevo.addActionListener((ActionListener) this);        
        this.VtnOficinas.adicionar.setActionCommand("__Adicionar");
        this.VtnOficinas.adicionar.addActionListener((ActionListener) this);
        


  
        this.VtnOficinas.Tabla.addMouseListener((MouseListener) this);
        this.VtnOficinas.Tabla.setModel(new DefaultTableModel());
    }
 
 
 public void mouseClicked(MouseEvent e) {
        if(e.getButton()== 1)//boton izquierdo
        {
             int Fila = this.VtnOficinas.Tabla.rowAtPoint(e.getPoint());
             if (Fila > -1){                
                this.VtnOficinas.codi_ofic.setText(String.valueOf(
                        this.VtnOficinas.Tabla.getValueAt(Fila, 0)));
                
                this.VtnOficinas.jtexoficina.setText(String.valueOf(
                        this.VtnOficinas.Tabla.getValueAt(Fila, 1)));
                
                this.VtnOficinas.Telefono.setText(String.valueOf(
                        this.VtnOficinas.Tabla.getValueAt(Fila, 2)));
                
                this.VtnOficinas.Email.setText(String.valueOf(
                        this.VtnOficinas.Tabla.getValueAt(Fila, 3)));
                
                
                
                this.VtnOficinas.codi_ofic.setEditable(false);
                this.VtnOficinas.jtexoficina.setEditable(false);
                this.VtnOficinas.Telefono.setEditable(false); 
                this.VtnOficinas.Email.setEditable(false);
             
                
               
             
                
                
             }
        }
    }

   public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) { }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Hola");
        switch (oficinaMVC.valueOf(e.getActionCommand())){
            
                case __Nuevo:{
                    this.VtnOficinas.Tabla.setModel(this.modelo.getTabla());
                    this.VtnOficinas.Tabla.requestFocus();
                    break;
                }
                case __Adicionar:{
                    System.out.println("Hola");
                  if (this.modelo.Nuevo_oficina(this.VtnOficinas.codi_ofic.getText(),
                          this.VtnOficinas.jtexoficina.getText(),
                          this.VtnOficinas.Telefono.getText(),
                          this.VtnOficinas.Email.getText()));
                   JOptionPane.showMessageDialog(VtnOficinas,"OFICINA CREADA!!. ");
                   this.VtnOficinas.codi_ofic.setText(""); 
                   this.VtnOficinas.jtexoficina.setText("");                   
                   this.VtnOficinas.Telefono.setText("");
                   this.VtnOficinas.Email.setText("");
            }
         // else 
               JOptionPane.showMessageDialog(VtnOficinas,"INGRESA NUEVOS DATOS!!.");
        
            break;
   
           }
                   
                    
                
                
               

          }
        }
   









