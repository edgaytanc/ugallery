package ugallery.view;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import ugallery.control.Biblioteca;
import ugallery.control.Categoria;
import ugallery.control.NodoImagen;
import ugallery.control.NodoUsuario;
import ugallery.control.Usuario;
import ugallery.editor.BMPtoJPEGImage;
import ugallery.editor.JPEGImageCopy;
import ugallery.editor.JPEGImageHandlerBN;
import ugallery.editor.JPEGImageHandlerColors;
import ugallery.editor.JPEGImageHandlerRotator;
import ugallery.editor.JPEGtoBMPImage;
import javax.swing.SwingUtilities;

public class ConvertidorForm extends javax.swing.JFrame {
    private String usuario;
    private Usuario user;
    private Biblioteca biblioteca;
    private Categoria categoriaSeleccionada;
  
    public ConvertidorForm() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public ConvertidorForm(Biblioteca biblioteca) {
        initComponents();
        setLocationRelativeTo(null);
        
        this.biblioteca = biblioteca;
        this.user = this.biblioteca.recuperarUsuario(usuario);
        
        
        radioGroup.add(jpegBmpRadio);
        radioGroup.add(copiaJpegRadio);
        radioGroup.add(rojoVerdeAzulRadio);
        radioGroup.add(modificaImagenRadio);
        radioGroup.add(blancoNegroRadio);
        
        cargarUsuarios();
        cargarCategorias();
        
    }
    
    //Metodos agregados
    private void cargarUsuarios() {
        NodoUsuario nodoUsuarioActual = biblioteca.getUsuarios().getCabeza();
        usuarioBox.removeAllItems();
        while (nodoUsuarioActual != null) {
            usuarioBox.addItem(nodoUsuarioActual.getUsuario().getNombre());
            nodoUsuarioActual = nodoUsuarioActual.getSiguiente();
        }
    }
    
    private void cargarCategorias() {
        String usuarioSeleccionado = (String) usuarioBox.getSelectedItem();
        Usuario usuario = biblioteca.recuperarUsuario(usuarioSeleccionado);
        categoriaBox.removeAllItems();
        if (usuario != null) {
            for (Categoria categoria : usuario.getCategorias()) {
                categoriaBox.addItem(categoria.getNombre());
            }
        }
    }
    
    private void agregarImagenesACola() {
        String usuarioSeleccionado = (String) usuarioBox.getSelectedItem();
        String categoriaSeleccionada = (String) categoriaBox.getSelectedItem();
        Usuario usuario = biblioteca.recuperarUsuario(usuarioSeleccionado);
        Categoria categoria = usuario.getCategoriaPorNombre(categoriaSeleccionada);
        colaArea.setText("");
        if (categoria != null) {
            ArrayList<Path> imagenes = categoria.getImagenes();
            for (Path imagen : imagenes) {
                colaArea.append(imagen.toString() + "\n");
            }
        }
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
    
    private void ejecutaFunciones(String[] imagenesEnCola){
        new Thread(() ->{
        
        
        
        for (String rutaImagen : imagenesEnCola) {
            String urlImagen = rutaImagen;
            if (jpegBmpRadio.isSelected()) {
                try{
                    if(isJPEG(urlImagen)){
                        String urlImage= rutaImagen;
                        JPEGtoBMPImage jpegTobmp = new JPEGtoBMPImage(urlImage);
                        jpegTobmp.readFile();
                        jpegTobmp.generateFiles();
                        SwingUtilities.invokeLater(() -> addToConsole("Imagen JPEG convertida a BMP: " + urlImage));
                        
                    }
                    else{
                        String urlImage=rutaImagen;
                        BMPtoJPEGImage bmpTojpeg = new BMPtoJPEGImage(urlImage);
                        bmpTojpeg.readFile();
                        bmpTojpeg.generateFiles();
                        SwingUtilities.invokeLater(() -> addToConsole("Imagen BMP convertida a JPEG: " + urlImage));
                        
                    }
                }
                catch (Exception ex) {
                    Logger.getLogger(EditorForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (copiaJpegRadio.isSelected()) {
                try{
                    String urlImage=rutaImagen;
                    JPEGImageCopy jpegImageCopy = new JPEGImageCopy(urlImage);
                    jpegImageCopy.readFile();
                    jpegImageCopy.generateFiles();
                    SwingUtilities.invokeLater(() -> addToConsole("Copia de imagen JPEG creada: " + urlImage));
                    
                }
                catch (Exception ex) {
                    Logger.getLogger(EditorForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (rojoVerdeAzulRadio.isSelected()) {
                try{
                    String urlImage = rutaImagen;
                    JPEGImageHandlerColors  handlerColors = new JPEGImageHandlerColors(urlImage);
                    handlerColors.readFile();
                    handlerColors.generateFiles();
                    SwingUtilities.invokeLater(() -> addToConsole("Imágenes de color generadas: " + urlImage));
                    
                }
                catch (Exception ex) {
                    Logger.getLogger(EditorForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (modificaImagenRadio.isSelected()) {
                try{
                    String urlImage = rutaImagen;
                    JPEGImageHandlerRotator handlerRotator = new JPEGImageHandlerRotator(urlImage);
                    handlerRotator.readFile();
                    handlerRotator.generateFiles();
                    SwingUtilities.invokeLater(() -> addToConsole("Imagen rotada: " + urlImage));
                    
                }
                catch (Exception ex) {
                    Logger.getLogger(EditorForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (blancoNegroRadio.isSelected()) {
                try{
                    String urlImage = rutaImagen;
                    JPEGImageHandlerBN handlerBN = new JPEGImageHandlerBN(urlImage);
                    handlerBN.readFile();
                    handlerBN.generateFiles();
                    SwingUtilities.invokeLater(() -> addToConsole("Imagen en blanco y negro generada: " + urlImage));
                    
                }
                catch (Exception ex) {
                    Logger.getLogger(EditorForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una opción antes de ejecutar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }
            try {
                    Thread.sleep(1000); // Espera un segundo (1000 milisegundos)
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
            
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    progresoBar.setValue(progresoBar.getValue() + 1);
                }
            });
        } 
        }).start();
    }
    
    private void addToConsole(String message) {
        synchronized (consolaArea) {
            Document doc = consolaArea.getDocument();
            try {
                doc.insertString(doc.getLength(), message + "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
    
    




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioGroup = new javax.swing.ButtonGroup();
        nortePanel = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        usuarioBox = new javax.swing.JComboBox<>();
        categoriaBox = new javax.swing.JComboBox<>();
        estePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        colaArea = new javax.swing.JTextArea();
        lblCola = new javax.swing.JLabel();
        centroPanel = new javax.swing.JPanel();
        jpegBmpRadio = new javax.swing.JRadioButton();
        copiaJpegRadio = new javax.swing.JRadioButton();
        rojoVerdeAzulRadio = new javax.swing.JRadioButton();
        modificaImagenRadio = new javax.swing.JRadioButton();
        blancoNegroRadio = new javax.swing.JRadioButton();
        btnEjecutar = new javax.swing.JButton();
        progresoBar = new javax.swing.JProgressBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        consolaArea = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        lblUsuario.setText("Usuario");

        lblCategoria.setText("Categoria");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        usuarioBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                usuarioBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout nortePanelLayout = new javax.swing.GroupLayout(nortePanel);
        nortePanel.setLayout(nortePanelLayout);
        nortePanelLayout.setHorizontalGroup(
            nortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nortePanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usuarioBox, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoriaBox, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(57, 57, 57))
        );
        nortePanelLayout.setVerticalGroup(
            nortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nortePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(nortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(lblCategoria)
                    .addComponent(btnAgregar)
                    .addComponent(btnSalir)
                    .addComponent(usuarioBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoriaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        getContentPane().add(nortePanel, java.awt.BorderLayout.PAGE_START);

        colaArea.setColumns(20);
        colaArea.setRows(5);
        jScrollPane1.setViewportView(colaArea);

        lblCola.setText("Cola de procesamiento");

        javax.swing.GroupLayout estePanelLayout = new javax.swing.GroupLayout(estePanel);
        estePanel.setLayout(estePanelLayout);
        estePanelLayout.setHorizontalGroup(
            estePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, estePanelLayout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(estePanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblCola)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        estePanelLayout.setVerticalGroup(
            estePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, estePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblCola)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(estePanel, java.awt.BorderLayout.LINE_START);

        jpegBmpRadio.setText("JPEG a BMP y viceversa");

        copiaJpegRadio.setText("copia JPEG");

        rojoVerdeAzulRadio.setText("Rojo Verde Azul Sepia");

        modificaImagenRadio.setText("Modificar Imagen");

        blancoNegroRadio.setText("Blanco y negro");

        btnEjecutar.setText("EJECUTAR EN PARALELO");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(consolaArea);

        javax.swing.GroupLayout centroPanelLayout = new javax.swing.GroupLayout(centroPanel);
        centroPanel.setLayout(centroPanelLayout);
        centroPanelLayout.setHorizontalGroup(
            centroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(centroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpegBmpRadio)
                    .addComponent(copiaJpegRadio, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rojoVerdeAzulRadio, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modificaImagenRadio, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(blancoNegroRadio, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addComponent(btnEjecutar)
                .addGap(74, 74, 74))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centroPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(centroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(progresoBar, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        centroPanelLayout.setVerticalGroup(
            centroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centroPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jpegBmpRadio)
                .addGap(18, 18, 18)
                .addComponent(copiaJpegRadio)
                .addGap(18, 18, 18)
                .addGroup(centroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rojoVerdeAzulRadio)
                    .addComponent(btnEjecutar))
                .addGap(18, 18, 18)
                .addComponent(modificaImagenRadio)
                .addGap(18, 18, 18)
                .addComponent(blancoNegroRadio)
                .addGap(27, 27, 27)
                .addComponent(progresoBar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(centroPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void usuarioBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_usuarioBoxItemStateChanged
        cargarCategorias();
    }//GEN-LAST:event_usuarioBoxItemStateChanged

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarImagenesACola();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        String[] imagenesEnCola = colaArea.getText().split("\n");
        progresoBar.setMinimum(0);
        progresoBar.setMaximum(imagenesEnCola.length);
        progresoBar.setValue(0);
        new Thread(() -> {
        ejecutaFunciones(imagenesEnCola);
        }).start();
    }//GEN-LAST:event_btnEjecutarActionPerformed

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
            java.util.logging.Logger.getLogger(ConvertidorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConvertidorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConvertidorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConvertidorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConvertidorForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton blancoNegroRadio;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> categoriaBox;
    private javax.swing.JPanel centroPanel;
    private javax.swing.JTextArea colaArea;
    private javax.swing.JTextPane consolaArea;
    private javax.swing.JRadioButton copiaJpegRadio;
    private javax.swing.JPanel estePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton jpegBmpRadio;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCola;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JRadioButton modificaImagenRadio;
    private javax.swing.JPanel nortePanel;
    private javax.swing.JProgressBar progresoBar;
    private javax.swing.ButtonGroup radioGroup;
    private javax.swing.JRadioButton rojoVerdeAzulRadio;
    private javax.swing.JComboBox<String> usuarioBox;
    // End of variables declaration//GEN-END:variables
}
