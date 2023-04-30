/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ugallery.view;

import java.awt.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import ugallery.control.Biblioteca;
import ugallery.control.Categoria;
import ugallery.control.Usuario;

/**
 *
 * @author David
 */

public class BibliotecaForm extends javax.swing.JFrame {
    private String usuario;
    private Biblioteca biblioteca;
    private Usuario user;
    /**
     * Creates new form Biblioteca
     */
    public BibliotecaForm() {
        initComponents();
    }
    
    public BibliotecaForm(String usuario, Biblioteca biblioteca) {
        initComponents();
        setLocationRelativeTo(null);
        this.usuario = usuario;
        this.biblioteca = biblioteca;
        this.user = this.biblioteca.recuperarUsuario(usuario);
        lblUsuario.setText(user.getNombre());
        
        actualizarCategoriasArea();
    }
    
    private void actualizarCategoriasArea() {
        ArrayList<String> categorias = user.getListaCategorias();
        String categoriasTexto = String.join("\n", categorias);
        categoriasArea.setText(categoriasTexto);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nortePanel = new javax.swing.JPanel();
        lblCategorias = new javax.swing.JLabel();
        btnAgregarImagen = new javax.swing.JButton();
        btnEliminarImagen = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        estePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        categoriasArea = new javax.swing.JTextArea();
        surPanel = new javax.swing.JPanel();
        btnAgregarCategoria = new javax.swing.JButton();
        btnEliminarCategoria = new javax.swing.JButton();
        txtSalir = new javax.swing.JButton();
        centroPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblCategorias.setText("Categorias");

        btnAgregarImagen.setText("Agregar Imagen");
        btnAgregarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImagenActionPerformed(evt);
            }
        });

        btnEliminarImagen.setText("Eliminar Imagen");

        javax.swing.GroupLayout nortePanelLayout = new javax.swing.GroupLayout(nortePanel);
        nortePanel.setLayout(nortePanelLayout);
        nortePanelLayout.setHorizontalGroup(
            nortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nortePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarImagen)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarImagen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        nortePanelLayout.setVerticalGroup(
            nortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nortePanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(nortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarImagen)
                    .addComponent(btnEliminarImagen)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(nortePanel, java.awt.BorderLayout.PAGE_START);

        categoriasArea.setColumns(20);
        categoriasArea.setRows(5);
        jScrollPane2.setViewportView(categoriasArea);

        javax.swing.GroupLayout estePanelLayout = new javax.swing.GroupLayout(estePanel);
        estePanel.setLayout(estePanelLayout);
        estePanelLayout.setHorizontalGroup(
            estePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, estePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        estePanelLayout.setVerticalGroup(
            estePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, estePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(estePanel, java.awt.BorderLayout.LINE_START);

        btnAgregarCategoria.setText("Agregar Categoria");
        btnAgregarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCategoriaActionPerformed(evt);
            }
        });

        btnEliminarCategoria.setText("Eliminar Categoria");

        txtSalir.setText("Salir");
        txtSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout surPanelLayout = new javax.swing.GroupLayout(surPanel);
        surPanel.setLayout(surPanelLayout);
        surPanelLayout.setHorizontalGroup(
            surPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(surPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnAgregarCategoria)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 290, Short.MAX_VALUE)
                .addComponent(txtSalir)
                .addContainerGap())
        );
        surPanelLayout.setVerticalGroup(
            surPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, surPanelLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(surPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarCategoria)
                    .addComponent(btnEliminarCategoria)
                    .addComponent(txtSalir))
                .addGap(34, 34, 34))
        );

        getContentPane().add(surPanel, java.awt.BorderLayout.PAGE_END);

        jButton1.setText("<");

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setText(">");

        javax.swing.GroupLayout centroPanelLayout = new javax.swing.GroupLayout(centroPanel);
        centroPanel.setLayout(centroPanelLayout);
        centroPanelLayout.setHorizontalGroup(
            centroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        centroPanelLayout.setVerticalGroup(
            centroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(centroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(centroPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "png", "gif", "bmp", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();

            // Solicitar el nombre de la categoría en la que se desea almacenar la imagen
            String nombreCategoria = JOptionPane.showInputDialog(this, "Ingrese el nombre de la categoría en la que desea almacenar la imagen:");

            if (nombreCategoria != null) {
                // Buscar la categoría en la lista de categorías del usuario
                Categoria categoriaEncontrada = null;
                for (Categoria categoria : user.getCategorias()) {
                    if (categoria.getNombre().equalsIgnoreCase(nombreCategoria)) {
                        categoriaEncontrada = categoria;
                        break;
                    }
                }

                // Si la categoría existe, mover la imagen seleccionada a la carpeta de esa categoría
                if (categoriaEncontrada != null) {
                    Path destino = categoriaEncontrada.getDirectorio().resolve(selectedFile.getName());
                    try {
                        Files.copy(selectedFile.toPath(), destino);
                        System.out.println("Archivo copiado a: " + destino.toString());
                    } catch (IOException e) {
                        System.err.println("Error al copiar la imagen: " + e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Categoría no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnAgregarImagenActionPerformed

    private void btnAgregarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCategoriaActionPerformed
        String nuevaCategoria = JOptionPane.showInputDialog(this, "Ingrese el nombre de la nueva categoría:", "Agregar Categoría", JOptionPane.PLAIN_MESSAGE);
        if (nuevaCategoria != null && !nuevaCategoria.trim().isEmpty()) {
            user.agregarCategoria(new Categoria(nuevaCategoria.trim()));
            actualizarCategoriasArea();
        }
    }//GEN-LAST:event_btnAgregarCategoriaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setSize(800, 600);
    }//GEN-LAST:event_formWindowOpened

    private void txtSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_txtSalirActionPerformed

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
            java.util.logging.Logger.getLogger(BibliotecaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BibliotecaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BibliotecaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BibliotecaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BibliotecaForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCategoria;
    private javax.swing.JButton btnAgregarImagen;
    private javax.swing.JButton btnEliminarCategoria;
    private javax.swing.JButton btnEliminarImagen;
    private javax.swing.JTextArea categoriasArea;
    private javax.swing.JPanel centroPanel;
    private javax.swing.JPanel estePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCategorias;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel nortePanel;
    private javax.swing.JPanel surPanel;
    private javax.swing.JButton txtSalir;
    // End of variables declaration//GEN-END:variables
}
