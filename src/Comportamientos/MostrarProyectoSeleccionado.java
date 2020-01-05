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
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Axel
 */
public class MostrarProyectoSeleccionado extends OneShotBehaviour{
   AgenteProponente AP;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    String ID="";
    
     public MostrarProyectoSeleccionado (AgenteProponente A)
    {
        super(A);
        AP=A;
    }
   
    public void action()
    { 
        ID=AP.IDProponente;
        Mostrar();
    }
     public void Mostrar(){
        String Titulo;
        String SQLMostrarSeleccionados="SELECT * FROM `reportedeinvestigacion` WHERE Aspirante_idAspirante = '"+ID+"';";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQLMostrarSeleccionados);
            while (rs.next()) {
                Titulo = rs.getString(3);
                AP.IP.Proyecto.addItem(Titulo);
            }
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error: "+e);
        }
     }
}
