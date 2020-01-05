/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Comportamientos.InsertarRegistroProponenete;
import Comportamientos.RecibirMensaje;
import Interfaces.EditarProyecto;

import Interfaces.InterfazProponente;
import Interfaces.RegistroProyecto;
import Interfaces.RegistroProponente;
import Interfaces.Status;
import jade.core.AID;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Axel
 */
public class AgenteProponente extends GuiAgent {
 
   public InterfazProponente IP=new InterfazProponente(this);
   public RegistroProponente RegPro=new RegistroProponente(this);
   public RegistroProyecto RP=new RegistroProyecto(this);
   public InsertarRegistroProponenete RE=new InsertarRegistroProponenete(this);
   public EditarProyecto EP = new EditarProyecto(this);
   public Status Stat= new Status(this);
   ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
   public String IDProponente;
   public int idHistorialRegisPro=0;
   public String IDProyecto;
   
   public void setup()
    {
      System.out.println("Ejecutando Agente Proponente");
      addBehaviour(new RecibirMensaje(this));
    }  
    public void onGuiEvent(GuiEvent E)
    {
        switch(E.getType()){
            case 1:
               // Ejecutar la interfaz proponente
               RP.setVisible(true);
            break;
            case 2:
                //Insertar registro de proyecto
                 addBehaviour(new InsertarRegistroProponenete(this));
            break;
            case 3:
                // Cerarar secion de la interfaz Proponente
                msg.setContent("Login"); 
                msg.addReceiver(new AID("Sistema", AID.ISLOCALNAME));
                send(msg);
            break;
        }
    }
}
