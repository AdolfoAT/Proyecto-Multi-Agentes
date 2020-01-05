/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import Agentes.Sistema;
import Conexion.Conexion;
import jade.core.behaviours.OneShotBehaviour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 *
 * @author Adolfo
 */
public class RecomendarLinea extends OneShotBehaviour {
    Sistema Sistem;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    LevenshteinDistance ld = new LevenshteinDistance();
//  int Id= Sistem.idProyecto2;
    String Tema;
    int i=0;
    int k=1;
    String Tema2[]=new String[15];
    String Tema3;
    String Sinonimo[]=new String[150];
    int IDs[]=new int[150];
    int idDisciplina [] = new int[150];
    int Linea=0;
    float Afinidad[][]=new float[15][150];
    int Posicion[]=new int[100];
    @Override
  public void action()
  {
    // ConsultarReporte();
    // System.out.println("Tema :  "+Tema);
    // System.out.println("Tema 2 " + Tema2[0]);
    // Tema2=SeparaPalabras(Tema);
    Tema=Sistem.Titulo;
    // System.out.println("Titulo "+Tema);
    Tema2=SeparaPalabras(Tema);
    Evento();
    System.out.println("Entraste a Recomendar Linea");
  }
  public RecomendarLinea (Sistema S) {
      super(S);
      Sistem=S; 
  }
  public void Evento()
  {
      if(Sistem.Caso==3)
      {
            ConsultarSinonimosLinea();   //Consulto en la base de datos los sinonimos pertenecientes a una area 
            Afinidad(); // Comparacion semantica y sintactica del tema con los sinonimos del area
            BuscarAfinidad();// Busca la mayor similitud entre la comparacion 
            ObtenerLinea(); // Obtiene el area 
      }
      else
          if(Sistem.Caso==4)
          {
            ConsultarSinonimosLinea2();   //Consulto en la base de datos los sinonimos pertenecientes a una area 
            Afinidad(); // Comparacion semantica y sintactica del tema con los sinonimos del area
            BuscarAfinidad();// Busca la mayor similitud entre la comparacion 
            ObtenerLinea(); // Obtiene el area 
          }
  }
  public String[] SeparaPalabras(String Texto)
  {
        String s1=Texto;
        String s2[]=new String[15];
        StringTokenizer st = new StringTokenizer (s1);

        // bucle por todas las palabras
        while (st.hasMoreTokens())
        {
            s2[i] = st.nextToken();
           // System.out.println ("    Palabra " + i + " es: " + s2[i]);
            i++;
        }
        return s2;
  }
  public void ConsultarSinonimosLinea()
  {
      String Instruccion ="select * from lineadeinvestigación inner join sinonimosinvestigacion where idLineaDeInvestigación=LineaDeInvestigación_idLineaDeInvestigación and Disciplina_idDisciplina = '"+Sistem.Disciplina+"'";
    try {
            PreparedStatement stm= cn.prepareStatement(Instruccion);
            ResultSet rst = stm.executeQuery();
            while(rst.next()){
                IDs[k]=rst.getInt(1);
               // System.out.println("Id Sinonimo : "+IDs[k]+" " + k);
                Sinonimo[k]=rst.getString(5);
                idDisciplina[k]=rst.getInt(4);
                k++;
            }
        } catch (SQLException ex) {
           System.out.println("Error en Base de datos");
        }
  }
    public void ConsultarSinonimosLinea2()
  {
      String Instruccion ="select * from sinonimosinvestigacion";
    try {
            PreparedStatement stm= cn.prepareStatement(Instruccion);
            ResultSet rst = stm.executeQuery();
            while(rst.next()){
                IDs[k]=rst.getInt(1);
               // System.out.println("Id Sinonimo : "+IDs[k]+" " + k);
                Sinonimo[k]=rst.getString(2);
                idDisciplina[k]=rst.getInt(3);
                k++;
            }
        } catch (SQLException ex) {

        }
  }
  public void Afinidad()
  {
      for(int j=0;j<i;j++)
     {
         Tema3=Tema2[j];
         // System.out.println("Tema 3= " +Tema2[j]);
         for(int w=1;w<k;w++)
         {
            String Sinonimo2=Sinonimo[w];
             ld.setWords(Tema3, Sinonimo2);
             Afinidad[j][w]= ld.getAfinidad() *100;
             // System.out.print(Tema3+" "+Sinonimo2);
             // System.out.println("Afinidad "+ Afinidad[j][w]);
         }
     }
  }
  public void BuscarAfinidad()
  {
     // float Mayor;
      
      for (int j=0;j<i;j++)
      {
          for(int w=1;w<k;w++)
          {
              if(Afinidad[j][w]>75.0)
              {
                 // Mayor=Afinidad[j][w];
                   Posicion[w]=w; 
              }
             // System.out.print(Afinidad[j][w]+" ");
          }
         // System.out.println();
      }
      // System.out.println(Mayor);
  }
  public void ObtenerLinea()
  {
      //System.out.println("Linea de Investigacion encontradas ");
      for(int w=1;w<k;w++)
          {
              //System.out.println("Posicion = "+ posicion+" == "+IDs[w]);
              if(Posicion[w] == idDisciplina[w])
              {
                  Linea=IDs[w];
                  System.out.println("Linea "+Linea);
                  //Sistem.LineaInvestigacion=Linea;
                  Sistem.addBehaviour(new Recomendacion(Sistem,Linea));
              }
          }
      // System.out.println("Area "+area);
      //Sistem.T.Linea.setText(String.valueOf(disciplina));
      //System.out.println("Linea de investigacion: "+Sistem.LineaInvestigacion);
  }
  
}
