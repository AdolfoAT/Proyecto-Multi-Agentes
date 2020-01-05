/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import Agentes.AgenteProponente;
import Conexion.Conexion;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Axel
 */
public class InsertarRegistroProponenete extends OneShotBehaviour {
    AgenteProponente AP;
    public Conexion cc = new Conexion();
    public  Connection cn = cc.Conectar();
    ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
    public InsertarRegistroProponenete(AgenteProponente A)
    {
        super (A);
        AP= A;
       
    }   
    public void action()
    {
        String Genero="";
        if (AP.RegPro.Femenino.isSelected())
             Genero="Femenino";
        else
        if(AP.RegPro.Masculino.isSelected())
             Genero="Masculino";
  
         final String Instruccion ="INSERT INTO aspirante (Nombre,ApellidoPaterno,ApellidoMaterno,Edad,Sexo,Telefono,CorreoElectronico,MaximoNiveldeEstudios,TituloObtenido,usuario,contraseña)VALUES ('"+AP.RegPro.Nombre.getText()+"','"+AP.RegPro.ApellidoPaterno.getText()+"','"
                 +AP.RegPro.ApellidoMaterno.getText()+"','"+AP.RegPro.Edad.getText()+"','"+ Genero +"','"+AP.RegPro.Telefono.getText()+"','"+AP.RegPro.CorreoElectronico.getText()+"','"+ AP.RegPro.NivelEstudios.getSelectedItem().toString()+"','"+ AP.RegPro.TituloObtenido.getText()+"','"+AP.RegPro.Usuario.getText()+"','"+AP.RegPro.Password.getText()+"');";
         PreparedStatement pst1=null;
            try 
            {
               pst1= cn.prepareStatement(Instruccion);
               pst1.executeUpdate();
            }
            catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Registro No Exitoso " +ex);
       }
            finally {
                if (pst1 !=null)
                {
                   JOptionPane.showMessageDialog(null, "Registro Exitoso");
                   AP.RegPro.dispose();
                   msg.setContent("Login"); 
                    msg.addReceiver(new AID("Sistema", AID.ISLOCALNAME));
                    AP.send(msg);
                }
            }
           
       }      
}
