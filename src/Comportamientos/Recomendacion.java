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
public class Recomendacion extends OneShotBehaviour {
    Sistema Sistem;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    int idEvaluador[]=new int[10];
    int i=0;
    String Nombre[]=new String[10];
    int  v=0;
    int provador=0;
    int linea=0;
    @Override
    public void action ()
    {
        System.out.println("Entraste a recomendacion ");
        ConsultarExpertos();
        InsertarRecomendaciones();
    }
    public Recomendacion(Sistema S, int A)
    {
        super(S);
        Sistem=S;
        linea = A;
    }
    public void ConsultarExpertos()
    {
        System.out.println("Entraste a Consultar Expertos Lineaaa "+ linea);
    String Instruccion2="select * from ptc where lineadeinvestigación='"+linea+"'";
    try {
            PreparedStatement stm= cn.prepareStatement(Instruccion2);
            ResultSet rst = stm.executeQuery();
            while(rst.next()){
                idEvaluador[i]=rst.getInt(1);
                Nombre[i]=rst.getString(2);
                System.out.println(idEvaluador[i]+" ID Evaluador " + Nombre[i] );
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("Error");
        }
    }
    public void InsertarRecomendaciones()
    {   
        for(int e=0;e<i;e++)
        {
           // System.out.print("Interaccion: " + e +"I: "+ i );
            v=e;
            if(ConsultarPTC(e)==false)
                insertar1();
        }
    }
    public void insertar1()
    {
        PreparedStatement pst;    
        pst = null;
        String Instruccion="INSERT INTO `sidecar`.`ptc-asp` (`ptc_idPTC`, `aspirante_idAspirante`, `reportedeinvestigacion_idReportedeInvestigacion`, `Status_reporte`) VALUES ('"+idEvaluador[v]+"', '"+Sistem.idAspirante+"', '"+Sistem.idProyecto2+"', 'Recomendado')";   
        try 
            {
                 pst = cn.prepareStatement(Instruccion);
                 pst.executeUpdate();
            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Registro NO Exitoso");
            }
            if (pst == null)
            {
               JOptionPane.showMessageDialog(null, "Registro NO Exitoso"); 
            }
    }
    public boolean ConsultarPTC(int a)
    {
        String Instrccion1="select *from `ptc-asp` where ptc_idPTC='"+idEvaluador[a]+"' and aspirante_idAspirante='"+Sistem.idAspirante+"' and reportedeinvestigacion_idReportedeInvestigacion ='"+Sistem.idProyecto2+"';";
    try {
            PreparedStatement stm= cn.prepareStatement(Instrccion1);
            ResultSet rst = stm.executeQuery();
            while(rst.next()){
                provador=rst.getInt(1);
               // System.out.println("Provador: "+provador);
            }
        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return provador!=0;
    }
}
