/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import Agentes.AgenteExperto;
import Conexion.Conexion;
import jade.core.behaviours.OneShotBehaviour;
import java.sql.Connection;


/**
 *
 * @author EduardoMisael
 */
public class PasarDatos extends OneShotBehaviour{

    AgenteExperto AE;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    
    public PasarDatos(AgenteExperto a){
        super (a);
        AE=a;
    }

    
    public void action(){
        PasarDatos();
       
    }
    
    public void PasarDatos(){
        
        String NombreProyecto = AE.IntExp.ProyectoEvaluar.getSelectedItem().toString();
        System.out.println(NombreProyecto);
        AE.IntEva.NomProyecto.setText(NombreProyecto);
        
        
    }
    
}
