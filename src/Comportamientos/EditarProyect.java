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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adolfo
 */
public class EditarProyect extends OneShotBehaviour{
    
    AgenteProponente AP;
    public Conexion cc = new Conexion();
    public  Connection cn = cc.Conectar();
    public String ID;
    public void action()
    {
        ID=AP.IDProponente;
         modificarproyecto();
    }
    public EditarProyect(AgenteProponente A)
    {
        AP=A;
    }
    
    public void modificarproyecto()
    {
        String Instruccion ="UPDATE `sidecar`.`reportedeinvestigacion` SET `Tema`='"+AP.EP.Tema.getText()+"', `Titulo`='"+AP.EP.Titulo.getText()+"', `Introduccion`='"+AP.EP.Introduccion.getText()+"', `Resumen`='"+AP.EP.Resumen.getText()+"', `MarcoTeorico`='"+AP.EP.MarcoTeorico.getText()+"', `EstadodelArte`='"+AP.EP.EstadoDelArte.getText()+"', `Problematica`='"+AP.EP.Problematica.getText()+"', `Aportaciones`='"+AP.EP.Aportaciones.getText()+"',`Status` = 'En Elaboración' WHERE `idReportedeInvestigacion`='"+AP.IDProyecto+"';";
         PreparedStatement pst=null;
            try 
            {
               pst= cn.prepareStatement(Instruccion);
               pst.executeUpdate();
            }
            catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Actualizacion NO Exitosa");
       }
            finally {
                if (pst !=null)
                {
                   JOptionPane.showMessageDialog(null, "Actualizacion Exitosa"); 
                }
            }  
    }
}
  