/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;
import Agentes.AgenteProponente;
import Interfaces.InterfazProponente;
import Interfaces.RegistroProponente;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.*;

/**
 *
 * @author Adolfo
 */
public class RecibirMensaje  extends SimpleBehaviour{
    AgenteProponente AP;
    public void action()
    {
        ACLMessage msg= myAgent.receive();
        if(msg!=null && msg.getPerformative()==ACLMessage.CONFIRM)
        {
            AP.RegPro=new RegistroProponente(AP);
            AP.RegPro.setVisible(true);
            block();
        }
        else 
            if(msg!=null && msg.getPerformative()==ACLMessage.INFORM)
            {
                String ID=msg.getContent();
                AP.IDProponente=ID;
                AP.IP=new InterfazProponente(AP);
                AP.IP.setVisible(true);
                AP.addBehaviour(new MostrarProyectoSeleccionado(AP)); 
                block();
            }
    }
    public RecibirMensaje(AgenteProponente A)
    {
        super(A);
        AP=A;
    }
    public boolean done()
    {
        return false;
    }
   
}
