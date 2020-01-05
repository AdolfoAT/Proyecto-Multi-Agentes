/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import Agentes.AgenteProponente;
import Conexion.Conexion;
import jade.core.behaviours.OneShotBehaviour;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Adolfo
 */
public class MostrarProyecto extends OneShotBehaviour{
    AgenteProponente AP;
    public Conexion cc = new Conexion();
    public  Connection cn = cc.Conectar();
    public String ID;
   
    public MostrarProyecto(AgenteProponente A) {
      super(A);
      AP=A;
      //ID=i;
    }
    public void action()
    {
        ID=AP.IDProponente;
      // System.out.println("Entaste a mostrar proyecto y el id del aspirante es " +ID);
       mostrar();
    }
    public void mostrar()
    {
       String Tema = null,Titulo= null,Introduccion=null,Resumen=null,MarcoTeorico=null,Estado=null,Problematica=null,Aportaciones=null;
       String Titulo2 = AP.IP.Proyecto.getSelectedItem().toString();
       String Instruccion="SELECT * from reportedeinvestigacion where Titulo like '"+ Titulo2 +"' and Aspirante_idAspirante= '"+ID+"' ";
       try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Instruccion);
            if(rs.next()) {
                AP.IDProyecto=rs.getString(1);
                Tema=rs.getString(2);
                Titulo = rs.getString(3);
                Introduccion=rs.getString(4);
                Resumen=rs.getString(5);
                MarcoTeorico=rs.getString(6);
                Estado=rs.getString(7);
                Problematica=rs.getString(8);
                Aportaciones=rs.getString(9);
            }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error: "+e);
        }
       if(AP.IDProyecto != null)
       {
            AP.EP.Tema.setText(Tema);
            AP.EP.Titulo.setText(Titulo);
            AP.EP.Introduccion.setText(Introduccion);
            AP.EP.Resumen.setText(Resumen);
            AP.EP.MarcoTeorico.setText(MarcoTeorico);
            AP.EP.EstadoDelArte.setText(Estado);
            AP.EP.Problematica.setText(Problematica);
            AP.EP.Aportaciones.setText(Aportaciones);
       }
       else
       {
           JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
           AP.IP.setVisible(true);
           AP.EP.dispose();
       }
       
      
    }
}
