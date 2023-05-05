
package ugallery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import ugallery.control.Biblioteca;
import ugallery.view.BibliotecaForm;
import ugallery.view.ConvertidorForm;
import ugallery.view.EditorForm;


public class Inicio extends javax.swing.JFrame {
    Biblioteca biblioteca =  new Biblioteca();
    private static final String BIBLIOTECA_DATA_FILE = "biblioteca_data.bin";
   
    public Inicio() {
        initComponents();
        setLocationRelativeTo(null);
        this.setSize(600, 400);
        
        biblioteca = cargarBiblioteca();
    }
    
    private static void guardarBiblioteca(Biblioteca biblioteca) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BIBLIOTECA_DATA_FILE))) {
            oos.writeObject(biblioteca);
        } catch (IOException e) {
            System.err.println("Error al guardar la biblioteca: " + e.getMessage());
        }
    }
    
    private static Biblioteca cargarBiblioteca() {
        File bibliotecaDataFile = new File(BIBLIOTECA_DATA_FILE);
        if (bibliotecaDataFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bibliotecaDataFile))) {
                return (Biblioteca) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar la biblioteca: " + e.getMessage());
            }
        }

        // Si no hay datos guardados, crea una nueva instancia de Biblioteca
        return new Biblioteca();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnBiblioteca = new javax.swing.JButton();
        btnEditor = new javax.swing.JButton();
        btnConvertidor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ugallery");
        setResizable(false);
        setSize(new java.awt.Dimension(600, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setText("MENU");

        lblUsuario.setText("Usuario");

        btnBiblioteca.setText("Ingresar a Biblioteca");
        btnBiblioteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBibliotecaActionPerformed(evt);
            }
        });

        btnEditor.setText("Ingresar a Editor");
        btnEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditorActionPerformed(evt);
            }
        });

        btnConvertidor.setText("Ingresar a Convertidor");
        btnConvertidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvertidorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBiblioteca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConvertidor, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                .addGap(64, 64, 64))
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTitulo)
                .addGap(84, 84, 84)
                .addComponent(btnBiblioteca)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditor)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario))
                .addGap(18, 18, 18)
                .addComponent(btnConvertidor)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBibliotecaActionPerformed
        String nombreUsuario = txtUsuario.getText().trim();
                if (nombreUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    biblioteca.agregarUsuarioSiNoExiste(nombreUsuario);
                    BibliotecaForm biblio = new BibliotecaForm(nombreUsuario,biblioteca);
                    biblio.setVisible(true);
                }
                
    }//GEN-LAST:event_btnBibliotecaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        guardarBiblioteca(biblioteca);
                System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void btnEditorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditorActionPerformed
        String nombreUsuario = txtUsuario.getText().trim();
        if (nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            biblioteca.agregarUsuarioSiNoExiste(nombreUsuario);
            EditorForm editor = new EditorForm(nombreUsuario,biblioteca);
            editor.setVisible(true);
        }
    }//GEN-LAST:event_btnEditorActionPerformed

    private void btnConvertidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvertidorActionPerformed
        String nombreUsuario = txtUsuario.getText().trim();
        if (nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            biblioteca.agregarUsuarioSiNoExiste(nombreUsuario);
            ConvertidorForm editor = new ConvertidorForm(biblioteca);
            editor.setVisible(true);
        }
    }//GEN-LAST:event_btnConvertidorActionPerformed

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBiblioteca;
    private javax.swing.JButton btnConvertidor;
    private javax.swing.JButton btnEditor;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
