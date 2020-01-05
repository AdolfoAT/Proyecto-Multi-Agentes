/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;
import jade.core.behaviours.CyclicBehaviour;
import Agentes.Sistema;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Adolfo
 */
public class Espiando extends CyclicBehaviour{
    Sistema Sistem;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    public void action()
    {
       ObtenerIds();
       
       if(Sistem.idProyecto != Sistem.idProyecto2)
       {
           System.out.println(Sistem.idProyecto +" "+Sistem.idProyecto2);
           Sistem.idProyecto=Sistem.idProyecto2;
            ConsultarReporte();
            Sistem.addBehaviour(new RecomendarArea (Sistem));
       }
    }
    public Espiando(Sistema S)
    {
        super(S);
        Sistem=S;  
    }
   
    public void ObtenerIds ()
    {
        String Instruccion ="SELECT MAX(idReportedeInvestigacion) AS idReportedeInvestigacion FROM reportedeinvestigacion";
        try {
                PreparedStatement stm= cn.prepareStatement(Instruccion);
                ResultSet rst = stm.executeQuery();
                if(rst.next()){Sistem.idProyecto2=rst.getInt(1);}
            } catch (SQLException ex) {

            }
    }
     public void ConsultarReporte()
    {
      String Instruccion ="select * from reportedeinvestigacion where idReportedeInvestigacion='"+Sistem.idProyecto2+"'";
      try {
              PreparedStatement stm= cn.prepareStatement(Instruccion);
              ResultSet rst = stm.executeQuery();
              if(rst.next()){
                  Sistem.Tema=rst.getString(2);
                  Sistem.Titulo=rst.getString(3);
                  Sistem.idAspirante=rst.getInt(10);
              }
              //System.out.println("Consultar reporte: "+  Sistem.Tema+" "+Sistem.Titulo+" "+ Sistem.idAspirante);
          } catch (SQLException ex) {

          }
    }
}
