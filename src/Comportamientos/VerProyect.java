/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import jade.core.behaviours.OneShotBehaviour;
import Conexion.Conexion;
import Agentes.AgenteExperto;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author jehm1
 */
public class VerProyect extends OneShotBehaviour{
    
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
       
    public AgenteExperto R;
    String TemaProy;
    String TituloProy;
    String IntroProy;
    String ResumenProy;
    String EstadoProy;
    String MarcoProy;
    String ProblematicaProy;
    String AportacionesProy;
    
    public VerProyect(AgenteExperto a){
        super (a);
        R=a;
    }
    
    public void action(){
        MostrarInformacion();
        
    }
    
    public void MostrarInformacion(){
       String nompro = (String)R.IntExp.ProyectoEvaluar.getSelectedItem();
   
       String getInfo = "SELECT Tema, Titulo, Introduccion, Resumen, EstadodelArte, MarcoTeorico, Problematica, Aportaciones FROM reportedeinvestigacion WHERE titulo = '"+nompro+"';";
       
       //String getTema = "SELECT Tema FROM reportedeinvestigacion WHERE Titulo = '"+nompro+"';";
       
       try{
           Statement a = cn.createStatement();
           ResultSet rs = a.executeQuery(getInfo);  
           
           while(rs.next()){
               TemaProy = rs.getString("Tema");
               TituloProy = rs.getString("Titulo");
               IntroProy = rs.getString("Introduccion");
               ResumenProy = rs.getString("Resumen");
               EstadoProy = rs.getString("EstadodelArte");
               MarcoProy = rs.getString("MarcoTeorico");
               ProblematicaProy = rs.getString("Problematica");
               AportacionesProy = rs.getString("Aportaciones");
               System.out.println(getInfo);
           }
           R.AC.Tema.setText(TemaProy);
           R.AC.Titulo.setText(TituloProy);
           R.AC.Introduccion.setText(IntroProy);
           R.AC.Resumen.setText(ResumenProy);
           R.AC.EstadoDelArte.setText(EstadoProy);
           R.AC.MarcoTeorico.setText(MarcoProy);
           R.AC.Problematica.setText(ProblematicaProy);
           R.AC.Aportaciones.setText(AportacionesProy);
       }
       
       catch(Exception exc){
           System.out.println("No ejecutado por: " + exc);
       }
       
    }
    
    
    
}
