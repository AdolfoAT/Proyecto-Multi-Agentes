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
public class RecomendarDisciplina extends OneShotBehaviour {
    Sistema Sistem;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    LevenshteinDistance ld = new LevenshteinDistance();
    String Tema;
    int i=0;
    int k=1;
    String Tema2[]=new String[15];
    String Tema3;
    String Sinonimo[]=new String[150];
    int IDs[]=new int[150];
    int idDisciplina [] = new int[150];
    int Disciplina=0;
    float Afinidad[][]=new float[15][150];
    int Posicion=0;
    @Override
  public void action()
  {
    // ConsultarReporte();
    //System.out.println("Tema :  "+Tema);
    //System.out.println("Tema 2 " + Tema2[0]);
    // Tema2=SeparaPalabras(Tema);
    Tema=Sistem.Tema;
    Tema2=SeparaPalabras(Tema);
    Evento();
    
    System.out.println("Entraste a Recomendar Disciplina ");
  }
  public RecomendarDisciplina (Sistema S) {
      super(S);
      Sistem=S; 
  }
  public void Evento()
  {
      if(Sistem.Caso==1)
      {
          ConsultarSinonimosDisciplina2();
          Afinidad(); // Comparacion semantica y sintactica del tema con los sinonimos del area
          BuscarAfinidad();// Busca la mayor similitud entre la comparacion 
      }
      else
      if(Sistem.Caso==2)
      {
          ConsultarSinonimosDisciplina();
          Afinidad(); // Comparacion semantica y sintactica del tema con los sinonimos del area
          BuscarAfinidad();// Busca la mayor similitud entre la comparacion 
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
  public void ConsultarSinonimosDisciplina()
  {
      String Instruccion ="select * from disciplina inner join sinonimosdisciplina where idDisciplina = Disciplina_idDisciplina and Area_idArea='"+Sistem.Area+"'";
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

        }
  }
    public void ConsultarSinonimosDisciplina2()
  {
      String Instruccion ="select * from disciplina";
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
      float Mayor=Afinidad[0][0];
      
      for (int j=0;j<i;j++)
      {
          for(int w=1;w<k;w++)
          {
              if(Mayor<Afinidad[j][w])
              {
                  Mayor=Afinidad[j][w];
                   Posicion=w; 
              }
             // System.out.print(Afinidad[j][w]+" ");
          }
         // System.out.println();
      }
      if(Mayor>=75.0)
      {
          Sistem.Caso=3;
          ObtenerDisciplina();
      }
      else
      {
          Sistem.Caso=4;
          Sistem.addBehaviour(new RecomendarLinea(Sistem));
      }
      // System.out.println(Mayor);
  }
  public void ObtenerDisciplina()
  {
      for(int w=1;w<k;w++)
          {
              //System.out.println("Posicion = "+ posicion+" == "+IDs[w]);
              if(Posicion == idDisciplina[w])
              {
                  Disciplina=IDs[w];
              }
          }
      // System.out.println("Area "+area);
      // Sistem.T.Disciplina.setText(String.valueOf(disciplina));
      Sistem.Disciplina=Disciplina;
      System.out.println("Disciplina: "+ Sistem.Disciplina);
      Sistem.addBehaviour(new RecomendarLinea(Sistem));
  }
  
}