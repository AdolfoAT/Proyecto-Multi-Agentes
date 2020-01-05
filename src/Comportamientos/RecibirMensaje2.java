/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import Agentes.AgenteExperto;
import Interfaces.RegistroExperto;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Adolfo
 */
public class RecibirMensaje2 extends SimpleBehaviour {
    AgenteExperto AE;
    public void action()
    {
         ACLMessage msg= myAgent.receive();
        if(msg!=null && msg.getPerformative()==ACLMessage.CONFIRM)
        {
            AE.RE=new RegistroExperto(AE);
            AE.RE.setVisible(true);
            block();
        }
        else 
            if(msg!=null && msg.getPerformative()==ACLMessage.INFORM)
            {
                System.out.println(msg.getContent());
                AE.ID=msg.getContent();
                AE.IntExp.setVisible(true);
                AE.addBehaviour(new MostrarProyectos(AE));
                AE.addBehaviour(new MostrarSeleccionados(AE));
                AE.addBehaviour(new MostrarCalificados (AE));
                block();
            }
    }
    public RecibirMensaje2(AgenteExperto A)
    {
        super(A);
        AE=A;
    }
    public boolean done()
    {
        return false;
    }
}
