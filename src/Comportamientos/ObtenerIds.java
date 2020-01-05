/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import Agentes.Sistema;
import Conexion.Conexion;
import jade.core.behaviours.OneShotBehaviour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adolfo
 */
public class ObtenerIds extends OneShotBehaviour{
    Sistema Sistem;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    public void action()
    {
    String Instruccion ="SELECT MAX(idReportedeInvestigacion) AS idReportedeInvestigacion FROM reportedeinvestigacion";
    try {
            PreparedStatement stm= cn.prepareStatement(Instruccion);
            ResultSet rst = stm.executeQuery();
            if(rst.next()){Sistem.idProyecto=rst.getInt(1);}
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    public ObtenerIds (Sistema S)
    {
        super(S);
       Sistem =S;
    }
    
}
