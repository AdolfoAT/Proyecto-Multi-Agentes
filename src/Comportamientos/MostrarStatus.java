/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import Agentes.AgenteProponente;
import Conexion.Conexion;
import jade.core.behaviours.OneShotBehaviour;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Adolfo
 */
public class MostrarStatus extends OneShotBehaviour {
    AgenteProponente AP;
    public Conexion cc = new Conexion();
    public  Connection cn = cc.Conectar();
    String ID_Proponente;
    String Statuss[]=new String[15];
    float Calificacion[]=new float[15];
    float Suma;
    float CalificacionFinal;
    String Titulo;
    int j=0;
    int i=0;
    String ID;
    public void action()
    {
        ID_Proponente=AP.IDProponente;
        i=0;
        j=0;
        Titulo = AP.IP.Proyecto.getSelectedItem().toString();
        Suma=0;
        CalificacionFinal=0;
        if(status1()==true)
        {
            //System.out.println("Entraste a Status");
            status2();
            VerificarStatus();
        }
        else 
        if(status1()==false)
        {
           default1();
        }
        
    }
    public MostrarStatus(AgenteProponente A)
    {
        super(A);
        AP=A;
    }
    public boolean status1()
    {
        boolean validar=false;
        String Status =null;
        String Instruccion="SELECT * from reportedeinvestigacion where Titulo like '"+ Titulo +"' and Aspirante_idAspirante= '"+ID_Proponente+"' ";
        try {
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(Instruccion);
             if(rs.next()) {
                 ID=rs.getString(1);
               Status=rs.getString(11);
              // System.out.println("Status " +Status);
             }
         }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Error: "+e);
         }
        if(Status==null || Status.equals("En Elaboración"))
           validar=false;
        else 
            if ( Status.equals("Terminado"))
            validar=true;
        return validar;
    }
    public void status2()
    {
         String Instruccion="select * from  `ptc-asp` where aspirante_idAspirante='"+ID_Proponente+"' and reportedeinvestigacion_idReportedeInvestigacion='"+ID+"'; ";
        try {
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(Instruccion);
             while(rs.next()) {
               Statuss[i]=rs.getString(5);
               Calificacion[i]=rs.getFloat(6);
               i++;
             }
         }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Error: "+e);
         }
    }
    public void VerificarStatus()
    {
        
        if (i>=3)
        {
            for(int k=0;k<i;k++)
            {
                System.out.println("Status " + Statuss[k]);
                if(Statuss[k].equals("Calificado") || Statuss[k].equals("Dictaminado"))
                {
                    j++;
                }
            }
            dictaminar();
        }
        else
        {
            AP.Stat.Proyecto.setText(Titulo);
            AP.Stat.Dic.setText("No ha sido dictaminado");
            AP.Stat.Status.setText("En Evaluacion");
            AP.Stat.Calificacion.setText("No se ha Calificado");
        }
    }
    public void dictaminar()
    {
        if(i==j)
        {
            for(int k=0;k<i;k++)
            {
                Suma=Suma+Calificacion[k];
            }
            CalificacionFinal=Suma/i;
            System.out.println("Suma " +Suma +" Evaluadores " +i);
            if(CalificacionFinal >=8.5 && CalificacionFinal <=10.0)
            {
                AP.Stat.Proyecto.setText(Titulo);
                AP.Stat.Dic.setText("Aprobado");
                AP.Stat.Status.setText("Dictaminado");
                AP.Stat.Calificacion.setText(String.valueOf(CalificacionFinal));
                JOptionPane.showMessageDialog(null,"El proyecto ya ha sido dictaminado","Importante", JOptionPane.INFORMATION_MESSAGE);
                Actualizar();
            }
            else
                if(CalificacionFinal >=0 && CalificacionFinal < 8.5)
                {
                    AP.Stat.Proyecto.setText(Titulo);
                    AP.Stat.Dic.setText("No Aprobado");
                    AP.Stat.Status.setText("Dictaminado");
                    AP.Stat.Calificacion.setText(String.valueOf(CalificacionFinal));
                }
        }
        else
        {
            AP.Stat.Proyecto.setText(Titulo);
            AP.Stat.Dic.setText("No ha sido dictaminado");
            AP.Stat.Status.setText("En Evaluacion");
            AP.Stat.Calificacion.setText("No se ha Calificado");
        }
    }
    public void Actualizar()
    {
            String SQLActualizar = "UPDATE sidecar.`ptc-asp` SET Status_Reporte='Dictaminado' WHERE `reportedeinvestigacion_idReportedeInvestigacion`= '"+ID+"' and aspirante_idAspirante='"+ID_Proponente+"';";
        
            try {
                PreparedStatement ps = cn.prepareStatement(SQLActualizar);
                System.out.println(SQLActualizar);
                ps.executeUpdate();             
                //JOptionPane.showMessageDialog(null,"Modificado con exito","Importante", JOptionPane.INFORMATION_MESSAGE);
            }catch(HeadlessException | SQLException exc){
                System.out.println("Error: "+exc);
                JOptionPane.showMessageDialog(null,"Error: "+exc,"Importante", JOptionPane.ERROR);
            }
    }
    public void default1()
    {
       AP.Stat.Proyecto.setText(Titulo);
       AP.Stat.Dic.setText("No ha sido dictaminado");
       AP.Stat.Status.setText("En Evaluacion");
       AP.Stat.Calificacion.setText("No se ha Calificado"); 
    }
}
