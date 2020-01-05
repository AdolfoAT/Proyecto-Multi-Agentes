/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agentes;

import Comportamientos.Espiando;
import Comportamientos.ObtenerIds;
import Comportamientos.RecibirMensaje1;
import Conexion.Conexion;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import Interfaces.Login;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import java.sql.Connection;

/**
 *
 * @author Adolfo
 */
public class Sistema extends GuiAgent{
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    //public RegistroProponente RP=new RegistroProponente(this);
    //public RegistroExperto RE=new RegistroExperto(this);
    public Login L;
    public int idProyecto;
    public int idProyecto2;
    public int Area;
    public int Disciplina;
    public int LineaInvestigacion=0;
    public String Tema,Titulo;
    public int idAspirante;
    public int Caso=0;
    public int Contador=1;
    ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
    @Override
    public void setup()
    {
        // T=new Titulo(this);
        // T.setVisible(true);
        addBehaviour(new RecibirMensaje1(this));
        L=new Login(this);
        L.setVisible(true);
        System.out.println("Ejecutando Sistema");
        addBehaviour(new ObtenerIds (this));
        addBehaviour(new Espiando(this));                       
       // AP=new AgenteProponente();
    }
    public void onGuiEvent (GuiEvent E)
    {
        switch (E.getType()) {
            case 1:
               // Ejecutar la interfaz Proponente y su agente 
                msg.setContent(L.ID); 
                msg.setPerformative(ACLMessage.INFORM);
                msg.addReceiver(new AID("Proponente", AID.ISLOCALNAME));
                send(msg);
            break;
            case 2:
               // Ejecutar la inferfaz Evaluador y su agente 
                msg.setContent(L.ID); 
                msg.setPerformative(ACLMessage.INFORM);
                msg.addReceiver(new AID("Evaluador", AID.ISLOCALNAME));
                send(msg);
            break;
            case 3:
               // Ejecutar el registro proponente    
                msg.setContent("Registro"); 
                msg.setPerformative(ACLMessage.CONFIRM);
                msg.addReceiver(new AID("Proponente", AID.ISLOCALNAME));
                send(msg);
            break;
            case 4:
               //  Ejecutar el registro evaluador 
               // RE.setVisible(true);
                msg.setContent("Registro"); 
                msg.setPerformative(ACLMessage.CONFIRM);
                msg.addReceiver(new AID("Evaluador", AID.ISLOCALNAME));
                send(msg);
            break;
        } 
    }
   
}
