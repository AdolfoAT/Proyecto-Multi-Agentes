/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import jade.core.behaviours.OneShotBehaviour;
import Agentes.AgenteExperto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author jehm1
 */

public class RegistrarEvaluador extends OneShotBehaviour{
    
    AgenteExperto E;
    
    public RegistrarEvaluador(AgenteExperto a){
        super(a);
        E=a;
        
    }
    
    public void action(){
        Conexion cc = new Conexion();
        Connection cn = cc.Conectar();
        //int id = E.O.obtenerID();
        //System.out.println(id);
        String Area=E.RE.CBArea.getSelectedItem().toString();
        String Disciplina= E.RE.CBDisciplina.getSelectedItem().toString();
        String Especialidad= E.RE.CBEspecialidad.getSelectedItem().toString();
        String Linea = E.RE.CBLinea.getSelectedItem().toString();
        int idArea=0;
        final String Instruccion2="SELECT idArea from Area where NombreArea like '"+ Area+"';";
        
        System.out.println(Instruccion2);
        try{
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Instruccion2);
            while(rs.next())
            {
                idArea=rs.getInt("idArea");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en el Registro: "+ex);
        }
        int idDisciplina=0;
        final String Instruccion3="SELECT idDisciplina from Disciplina where NombreDisciplina like '"+ Disciplina+"';";
        System.out.println(Instruccion3);
        
        try{
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Instruccion3);
            while(rs.next())
            {
                idDisciplina=rs.getInt("idDisciplina");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en el Registro: "+ex);
        }
        
        int idLinea=0;
        final String Instruccion4="SELECT idLineaDeInvestigación from LineaDeInvestigación where NombreLineaDeInvestigación like '"+Linea+"';";
        System.out.println(Instruccion4);
        
        try{
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Instruccion4);
            while(rs.next())
            {
                idLinea=rs.getInt("idLineaDeInvestigación");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en el Registro: "+ex);
        }
        
        int idEspecialidad=0;
        final String Instruccion5="SELECT idEspecialidad from Especialidad where NombreEspecialidad like '"+ Especialidad +"';";
        System.out.println(Instruccion5);
        
        try{
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(Instruccion5);
            while(rs.next())
            {
                idEspecialidad=rs.getInt("idEspecialidad");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en el Registro: "+ex);
        }
        
        
      //  id++;
        final String Instruccion1="INSERT INTO ptc (Nombre,ApellidoPaterno,ApellidoMaterno,Grado,NivelSNI,Area,DisciplinaExpertise,LineaDeInvestigación,InstitucionAdscripcion,usuario,clave,Especialidad)VALUES "
                                                             + "('"+E.RE.Nombre.getText()+"','"+E.RE.ApellidoPaterno.getText()+"','"+E.RE.ApellidoMaterno.getText()+"','"
                                                             +E.RE.CBGrado.getSelectedItem().toString()+"','"+E.RE.NivelSNI.getText()+"',"+idArea+","+idDisciplina+
                                                             ","+idLinea+",'"+E.RE.Instituto.getText()+"','"+E.RE.Usuario.getText()+"','"+E.RE.Password.getText()+"',"+idEspecialidad+");";
        System.out.println(Instruccion1);
        PreparedStatement pst =null;
        
        try{
            
            pst = E.RE.cn.prepareStatement(Instruccion1);
            pst.executeUpdate();
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en el Registro: "+ex);
        }
    }
    
}
