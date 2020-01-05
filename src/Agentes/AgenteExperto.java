/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Comportamientos.CalificarProyecto;
import Comportamientos.MostrarCalificados;
import Comportamientos.PasarDatos;
import Comportamientos.MostrarProyectos;
import Comportamientos.MostrarSeleccionados;
import Comportamientos.ObtenerIDEvaluador;
import Comportamientos.RecibirMensaje2;
import Comportamientos.RegistrarEvaluador;
import Comportamientos.Status;
import Comportamientos.VerProyect;
import Interfaces.Evaluacion;
import Interfaces.InterfazExperto;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

import Interfaces.RegistroExperto;
import Interfaces.VerProyecto;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Adolfo
 */

public class AgenteExperto extends GuiAgent{
    
    public InterfazExperto IntExp = new InterfazExperto(this);;
    public Evaluacion IntEva=new Evaluacion(this);
    public VerProyecto AC=new VerProyecto(this);
    public RegistroExperto RE=new RegistroExperto(this);
    public ObtenerIDEvaluador O = new ObtenerIDEvaluador(this);
    public String ID;
    ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
    public void setup(){  
      addBehaviour(new RecibirMensaje2(this));
      System.out.println("Ejecutando Evaluador");
    
    }
    
    public void onGuiEvent(GuiEvent GE){
        
        switch(GE.getType()){
        case 0:
            addBehaviour(new Status(this));
            addBehaviour(new MostrarProyectos (this));
            addBehaviour(new MostrarSeleccionados (this));
            addBehaviour(new MostrarCalificados (this));
            break;
        case 1:
            IntEva.setVisible(true);
            addBehaviour(new PasarDatos(this));            
            break;
        case 2:
            IntExp.setVisible(true);
            addBehaviour(new MostrarProyectos(this));
            addBehaviour(new MostrarSeleccionados(this));
            addBehaviour(new MostrarCalificados (this));
            break;
        case 3:
            addBehaviour(new CalificarProyecto(this));
            break;
        case 4:
            AC.setVisible(true);
            addBehaviour(new VerProyect(this));
            break;    
        case 5:
            //addBehaviour(new ObtenerIDEvaluador(this));
            //No es necesario ya que los ids del evaluador son autogenerados
            addBehaviour(new RegistrarEvaluador(this));
            break;
        case 6:
            IntExp.setVisible(true);
            addBehaviour(new MostrarProyectos (this));
            addBehaviour(new MostrarSeleccionados (this));
            addBehaviour(new MostrarCalificados (this));
            break;
        case 7:
           // Log = new Login(this);
           // Log.setVisible(true);
            msg.setContent("Login"); 
            msg.addReceiver(new AID("Sistema", AID.ISLOCALNAME));
            send(msg);
            
            IntExp.setVisible(false);
            break;
        case 8:
            RE.setVisible(true);
            break;
        }
    }
}
