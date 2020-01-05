/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Agentes.AgenteExperto;
import Agentes.Sistema;
import Conexion.Conexion;
import jade.gui.GuiEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Axel
 */
public class RegistroExperto extends javax.swing.JFrame {
    public Conexion cc = new Conexion();
    public  Connection cn = cc.Conectar();
    public AgenteExperto AE;
    /**
     * Creates new form RegistroExperto
     */
    public RegistroExperto() {
        initComponents();
        MostrarAreas();
    }
    
    public RegistroExperto(AgenteExperto a){
        initComponents();
        MostrarAreas();
        AE=a;
    }
    public void MostrarAreas(){
        String DatosAreas;
        CBArea.removeAllItems();
        String SQLMostrarAreas ="select NombreArea from area;";
        try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQLMostrarAreas);
                while(rs.next()){
                    DatosAreas=rs.getString("NombreArea");
                    CBArea.addItem(DatosAreas);
                }
            }catch(Exception exc){
                System.out.println("No ejecutado por: "+exc);
            }
    }
    
    public void MostrarEspecialidad(int IDE){
        String Datos;
        CBEspecialidad.removeAllItems();
        String SQLMostrarEspecialidad ="select NombreEspecialidad from Especialidad where Area_idArea="+IDE+";";
        
        try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQLMostrarEspecialidad);
                while(rs.next()){
                    Datos=rs.getString("NombreEspecialidad");
                    CBEspecialidad.addItem(Datos);
                }
            }catch(Exception exc){
                System.out.println("No ejecutado por: "+exc);
            }
    }
    
    public void MostrarDisciplina(int IDD){
        String Datos;
        CBDisciplina.removeAllItems();
        String SQLMostrarDisciplinas ="select NombreDisciplina from Disciplina where Area_idArea="+IDD+";";
        
        try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQLMostrarDisciplinas);
                while(rs.next()){
                    Datos=rs.getString("NombreDisciplina");
                    CBDisciplina.addItem(Datos);
                }
            }catch(Exception exc){
                System.out.println("No ejecutado por: "+exc);
            }
    }
    
    public void MostrarLinea(int IDL){
        String Datos;
        CBLinea.removeAllItems();
        String SQLMostrarDisciplinas ="select NombreLineaDeInvestigación from LineaDeInvestigación where Disciplina_idDisciplina="+IDL+";";
        
        try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQLMostrarDisciplinas);
                while(rs.next()){
                    Datos=rs.getString("NombreLineaDeInvestigación");
                    CBLinea.addItem(Datos);
                }
            }catch(Exception exc){
                System.out.println("No ejecutado por: "+exc);
            }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        Registro = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        CBGrado = new javax.swing.JComboBox<>();
        CBArea = new javax.swing.JComboBox<>();
        CBDisciplina = new javax.swing.JComboBox<>();
        CBLinea = new javax.swing.JComboBox<>();
        Nombre = new javax.swing.JTextField();
        ApellidoPaterno = new javax.swing.JTextField();
        ApellidoMaterno = new javax.swing.JTextField();
        NivelSNI = new javax.swing.JTextField();
        Instituto = new javax.swing.JTextField();
        Usuario = new javax.swing.JTextField();
        Password = new javax.swing.JPasswordField();
        CBEspecialidad = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jLabel2.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Registro Evaluador");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nombre experto");

        jLabel4.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Usuario");

        jLabel5.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Contraseña");

        jLabel6.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Grado");

        jLabel7.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Apellido paterno");

        Regresar.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        Regresar.setActionCommand("Regresar");
        Regresar.setLabel("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        Registro.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        Registro.setText("Registrar");
        Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistroActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Disciplina expertista");

        jLabel9.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Apellido meterno");

        jLabel10.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Área Expertista");

        jLabel11.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nivel SNI");

        jLabel12.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Linea investigación");

        jLabel13.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Especialidad");

        jLabel14.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Instituto adscripcion");

        CBGrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctorado", "Maestría ", "Licenciatura" }));
        CBGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBGradoActionPerformed(evt);
            }
        });

        CBArea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBAreaItemStateChanged(evt);
            }
        });
        CBArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBAreaActionPerformed(evt);
            }
        });

        CBDisciplina.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBDisciplinaItemStateChanged(evt);
            }
        });

        ApellidoPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidoPaternoActionPerformed(evt);
            }
        });

        ApellidoMaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidoMaternoActionPerformed(evt);
            }
        });

        NivelSNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NivelSNIActionPerformed(evt);
            }
        });

        Instituto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InstitutoActionPerformed(evt);
            }
        });

        Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioActionPerformed(evt);
            }
        });

        CBEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBEspecialidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(95, 95, 95)
                        .addComponent(Instituto))
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel10))
                                    .addGap(93, 93, 93))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(Registro)
                                    .addGap(73, 73, 73)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))
                                .addGap(135, 135, 135)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NivelSNI)
                            .addComponent(ApellidoMaterno)
                            .addComponent(ApellidoPaterno)
                            .addComponent(Nombre)
                            .addComponent(CBGrado, 0, 250, Short.MAX_VALUE)
                            .addComponent(CBArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBDisciplina, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBLinea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(Regresar)
                                .addGap(58, 58, 58))
                            .addComponent(CBEspecialidad, 0, 250, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(183, 183, 183)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Usuario)
                            .addComponent(Password))))
                .addGap(76, 76, 76))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CBGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(CBArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(NivelSNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(CBEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(CBDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(CBLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(Instituto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Registro)
                    .addComponent(Regresar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jLabel2)
                .addContainerGap(177, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBGradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBGradoActionPerformed

    private void CBAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBAreaActionPerformed
        
    }//GEN-LAST:event_CBAreaActionPerformed

    private void CBAreaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBAreaItemStateChanged
        // TODO add your handling code here:
        String NombreArea=(String) CBArea.getSelectedItem();
        int idArea= 0;
        String sql ="SELECT idArea FROM area where NombreArea like '"+NombreArea+"'";
        
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    idArea=rs.getInt("idArea");
        
                }
            }catch(Exception exc){
                System.out.println(exc);
            }
            MostrarDisciplina(idArea);
            MostrarEspecialidad(idArea);
    }//GEN-LAST:event_CBAreaItemStateChanged

    private void CBDisciplinaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBDisciplinaItemStateChanged
        // TODO add your handling code here:
        String NombreDisciplina=(String) CBDisciplina.getSelectedItem();
        int idDisciplina= 0;
        String sql ="SELECT idDisciplina FROM Disciplina where NombreDisciplina like '"+NombreDisciplina+"'";
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    idDisciplina=rs.getInt("idDisciplina");
                    System.out.println(idDisciplina);
        
                }
            }catch(Exception exc){
                System.out.println(exc);
            }
            MostrarLinea(idDisciplina);
    }//GEN-LAST:event_CBDisciplinaItemStateChanged

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        // TODO add your handling code here:
        int evento=7;
        GuiEvent EventoSeleccionar=new GuiEvent(this,evento);
        AE.postGuiEvent(EventoSeleccionar);
        
        dispose();
    }//GEN-LAST:event_RegresarActionPerformed

    private void ApellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidoPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidoPaternoActionPerformed

    private void ApellidoMaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidoMaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidoMaternoActionPerformed

    private void NivelSNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NivelSNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NivelSNIActionPerformed

    private void InstitutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InstitutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InstitutoActionPerformed

    private void UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioActionPerformed

    private void CBEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBEspecialidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBEspecialidadActionPerformed

    private void RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistroActionPerformed
        // TODO add your handling code here:
        int evento = 5;
        GuiEvent EventoRegistrar = new GuiEvent(this,evento);
        AE.postGuiEvent(EventoRegistrar);
    }//GEN-LAST:event_RegistroActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroExperto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroExperto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroExperto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroExperto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new RegistroExperto().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField ApellidoMaterno;
    public javax.swing.JTextField ApellidoPaterno;
    public javax.swing.JComboBox<String> CBArea;
    public javax.swing.JComboBox<String> CBDisciplina;
    public javax.swing.JComboBox<String> CBEspecialidad;
    public javax.swing.JComboBox<String> CBGrado;
    public javax.swing.JComboBox<String> CBLinea;
    public javax.swing.JTextField Instituto;
    public javax.swing.JTextField NivelSNI;
    public javax.swing.JTextField Nombre;
    public javax.swing.JPasswordField Password;
    public javax.swing.JButton Registro;
    public javax.swing.JButton Regresar;
    public javax.swing.JTextField Usuario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
