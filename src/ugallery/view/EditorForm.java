
package ugallery.view;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import ugallery.control.Biblioteca;
import ugallery.control.Usuario;
import ugallery.editor.BMPtoJPEGImage;
import ugallery.editor.JPEGImageCopy;
import ugallery.editor.JPEGImageHandlerBN;
import ugallery.editor.JPEGImageHandlerColors;
import ugallery.editor.JPEGImageHandlerRotator;
import ugallery.editor.JPEGtoBMPImage;


public class EditorForm extends javax.swing.JFrame {

    private String usuario;
    private Biblioteca biblioteca;
    private Usuario user;
    private String urlImagen;
    
    public EditorForm() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public EditorForm(String usuario, Biblioteca biblioteca){
        initComponents();
        setLocationRelativeTo(null);
        this.usuario = usuario;
        this.biblioteca = biblioteca;
        this.user = this.biblioteca.recuperarUsuario(usuario);
        lblUsuario.setText(user.getNombre());
        
        radioGroup.add(jpegBmpRadio);
        radioGroup.add(copiaJpegRadio);
        radioGroup.add(rojoVerdeAzulRadio);
        radioGroup.add(modificaImagenRadio);
        radioGroup.add(blancoNegroRadio);
    }
    
    /*
    Funciones que verifican si el archivo es jpg o bmp
    */
    
    public boolean isJPEG(String filePath) {
        String fileExtension = getFileExtension(filePath);
        return fileExtension.equalsIgnoreCase("jpeg") || fileExtension.equalsIgnoreCase("jpg");
    }

    public boolean isBMP(String filePath) {
        String fileExtension = getFileExtension(filePath);
        return fileExtension.equalsIgnoreCase("bmp");
    }

    private String getFileExtension(String filePath) {
        int lastIndexOfDot = filePath.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return "";
        }
        return filePath.substring(lastIndexOfDot + 1);
    }

    /*
    Funcion que verifica que opcion se escogio
    */
    private void ejecutarFunciones() {
        if (jpegBmpRadio.isSelected()) {
            try {
                if(isJPEG(this.urlImagen)){
                    String urlImage= this.urlImagen;
                    JPEGtoBMPImage jpegTobmp = new JPEGtoBMPImage(urlImage);
                    jpegTobmp.readFile();
                    jpegTobmp.generateFiles();
                    JOptionPane.showMessageDialog(this, "Imagen generada satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    String urlImage=this.urlImagen;
                    BMPtoJPEGImage bmpTojpeg = new BMPtoJPEGImage(urlImage);
                    bmpTojpeg.readFile();
                    bmpTojpeg.generateFiles();
                    JOptionPane.showMessageDialog(this, "Imagen generada satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                Logger.getLogger(EditorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (copiaJpegRadio.isSelected()) {
            try{
                String urlImage=this.urlImagen;
                JPEGImageCopy jpegImageCopy = new JPEGImageCopy(urlImage);
                jpegImageCopy.readFile();
                jpegImageCopy.generateFiles();
                JOptionPane.showMessageDialog(this, "Imagen generada satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception ex) {
                Logger.getLogger(EditorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (rojoVerdeAzulRadio.isSelected()) {
            
            try{
                String urlImage = this.urlImagen;
                JPEGImageHandlerColors  handlerColors = new JPEGImageHandlerColors(urlImage);
                handlerColors.readFile();
                handlerColors.generateFiles();
                JOptionPane.showMessageDialog(this, "Imagen generada satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception ex){
                Logger.getLogger(EditorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (modificaImagenRadio.isSelected()) {
            try{
                String urlImage = this.urlImagen;
                JPEGImageHandlerRotator handlerRotator = new JPEGImageHandlerRotator(urlImage);
                handlerRotator.readFile();
                handlerRotator.generateFiles();
                JOptionPane.showMessageDialog(this, "Imagen generada satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception ex){
                Logger.getLogger(EditorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (blancoNegroRadio.isSelected()) {
            try{
                String urlImage = this.urlImagen;
                JPEGImageHandlerBN handlerBN = new JPEGImageHandlerBN(urlImage);
                handlerBN.readFile();
                handlerBN.generateFiles();
                JOptionPane.showMessageDialog(this, "Imagen generada satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception ex){
                Logger.getLogger(EditorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // En caso de que no haya ningún botón seleccionado
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una opción", "Error", JOptionPane.ERROR_MESSAGE);
        }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioGroup = new javax.swing.ButtonGroup();
        nortePanel = new javax.swing.JPanel();
        lblUrlImagen = new javax.swing.JLabel();
        btnSeleccionarImagen = new javax.swing.JButton();
        estePanel = new javax.swing.JPanel();
        jpegBmpRadio = new javax.swing.JRadioButton();
        copiaJpegRadio = new javax.swing.JRadioButton();
        rojoVerdeAzulRadio = new javax.swing.JRadioButton();
        modificaImagenRadio = new javax.swing.JRadioButton();
        blancoNegroRadio = new javax.swing.JRadioButton();
        surpanel = new javax.swing.JPanel();
        btnEjecutar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        centroPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblUrlImagen.setText("Url de imagen");

        btnSeleccionarImagen.setText("Seleccionar Imagen");
        btnSeleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarImagenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nortePanelLayout = new javax.swing.GroupLayout(nortePanel);
        nortePanel.setLayout(nortePanelLayout);
        nortePanelLayout.setHorizontalGroup(
            nortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nortePanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblUrlImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(btnSeleccionarImagen)
                .addGap(40, 40, 40))
        );
        nortePanelLayout.setVerticalGroup(
            nortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nortePanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(nortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUrlImagen)
                    .addComponent(btnSeleccionarImagen))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        getContentPane().add(nortePanel, java.awt.BorderLayout.PAGE_START);

        jpegBmpRadio.setText("JPEG a BMP y viceversa");

        copiaJpegRadio.setText("copia JPEG");
        copiaJpegRadio.setMaximumSize(new java.awt.Dimension(145, 21));
        copiaJpegRadio.setMinimumSize(new java.awt.Dimension(145, 21));

        rojoVerdeAzulRadio.setText("Rojo Verde Azul Sepia");
        rojoVerdeAzulRadio.setMaximumSize(new java.awt.Dimension(145, 21));
        rojoVerdeAzulRadio.setMinimumSize(new java.awt.Dimension(145, 21));

        modificaImagenRadio.setText("Modificar Imagen");

        blancoNegroRadio.setText("Blanco Negro");

        javax.swing.GroupLayout estePanelLayout = new javax.swing.GroupLayout(estePanel);
        estePanel.setLayout(estePanelLayout);
        estePanelLayout.setHorizontalGroup(
            estePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(estePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(copiaJpegRadio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, estePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jpegBmpRadio))
                    .addComponent(rojoVerdeAzulRadio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modificaImagenRadio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blancoNegroRadio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        estePanelLayout.setVerticalGroup(
            estePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(estePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpegBmpRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(copiaJpegRadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rojoVerdeAzulRadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modificaImagenRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(blancoNegroRadio)
                .addContainerGap(249, Short.MAX_VALUE))
        );

        getContentPane().add(estePanel, java.awt.BorderLayout.LINE_START);

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblUsuario.setText("Usuario");

        javax.swing.GroupLayout surpanelLayout = new javax.swing.GroupLayout(surpanel);
        surpanel.setLayout(surpanelLayout);
        surpanelLayout.setHorizontalGroup(
            surpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(surpanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnEjecutar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(238, 238, 238)
                .addComponent(btnSalir)
                .addGap(46, 46, 46))
        );
        surpanelLayout.setVerticalGroup(
            surpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, surpanelLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(surpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEjecutar)
                    .addComponent(btnSalir)
                    .addComponent(lblUsuario))
                .addGap(32, 32, 32))
        );

        getContentPane().add(surpanel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout centroPanelLayout = new javax.swing.GroupLayout(centroPanel);
        centroPanel.setLayout(centroPanelLayout);
        centroPanelLayout.setHorizontalGroup(
            centroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );
        centroPanelLayout.setVerticalGroup(
            centroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );

        getContentPane().add(centroPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setSize(800, 600);
    }//GEN-LAST:event_formWindowOpened

    private void btnSeleccionarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarImagenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG & BMP Images", "jpg", "jpeg", "bmp");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            urlImagen = selectedFile.getAbsolutePath();
            lblUrlImagen.setText(urlImagen);
            System.out.println(urlImagen);
            // Aquí puedes guardar la variable urlImagen como un atributo de clase si es necesario
        }
    }//GEN-LAST:event_btnSeleccionarImagenActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        ejecutarFunciones();
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(EditorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditorForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton blancoNegroRadio;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionarImagen;
    private javax.swing.JPanel centroPanel;
    private javax.swing.JRadioButton copiaJpegRadio;
    private javax.swing.JPanel estePanel;
    private javax.swing.JRadioButton jpegBmpRadio;
    private javax.swing.JLabel lblUrlImagen;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JRadioButton modificaImagenRadio;
    private javax.swing.JPanel nortePanel;
    private javax.swing.ButtonGroup radioGroup;
    private javax.swing.JRadioButton rojoVerdeAzulRadio;
    private javax.swing.JPanel surpanel;
    // End of variables declaration//GEN-END:variables
}
