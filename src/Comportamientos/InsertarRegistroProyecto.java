/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;
import Agentes.AgenteProponente;
import jade.core.behaviours.OneShotBehaviour;
import javax.swing.JOptionPane;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author!!!¿¿¿ Axel ???!!! 
 */
public class InsertarRegistroProyecto extends OneShotBehaviour {
    AgenteProponente E;
    public Conexion cc = new Conexion();
    public  Connection cn = cc.Conectar();
    public void action()
    {
         final String Instruccion1="INSERT INTO reportedeinvestigacion (Tema,Titulo,Introduccion,Resumen,MarcoTeorico,EstadodelArte,Problematica,Aportaciones,Aspirante_idAspirante,Status)VALUES ('"+E.RP.Tema.getText()+"',"
                 + "'"+E.RP.Titulo.getText()+"','"+E.RP.Introduccion.getText()+"','"+E.RP.Resumen.getText()+"','"+E.RP.MarcoTeorico.getText()+"','"+E.RP.EstadoDelArte.getText()+"','"+E.RP.Problematica.getText()+"','"+E.RP.Aportaciones.getText()+"','"+E.IDProponente+"', 'En Elaboración')";
         //System.out.println(Instruccion1);
         PreparedStatement pst=null;
            try 
            {
               pst= cn.prepareStatement(Instruccion1);
               pst.executeUpdate();
            }
            catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Registro NO Exitoso: "+ex);
       }
            finally {
                if (pst !=null)
                {
                   JOptionPane.showMessageDialog(null, "Registro Exitoso"); 
                }
            }           
}
      public InsertarRegistroProyecto(AgenteProponente A)
    {
        super(A);
        E=A;
       
    }
    
}
    
