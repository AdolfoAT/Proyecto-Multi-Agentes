/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;
import Agentes.Sistema;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.*;

/**
 *
 * @author Adolfo
 */
public class RecibirMensaje1  extends SimpleBehaviour{
    Sistema Sistem;
    public void action()
    {
        ACLMessage msg= myAgent.receive();
        if(msg!=null && "Login".equals(msg.getContent()))
        {
            System.out.println("Mensaje Recibido de proponente");
            Sistem.L.setVisible(true);
            // block();
        }
        //block();  
    }
    public RecibirMensaje1(Sistema S)
    {
        super(S);
        Sistem=S;
    }
    public boolean done()
    {
        return false;
    }
   
}
