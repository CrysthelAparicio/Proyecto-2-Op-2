package gui;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import fs.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class Cliente extends javax.swing.JFrame {

//    private static final String IP = "172.16.6.194"; // Puedes cambiar a localhost
    private static final String IP = "127.0.0.1"; // Puedes cambiar a localhost
    private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor

    public Registry registry;
    public FSInterfaz server;
    public FSInterfaz cliente;
    public String abierto;

    public Cliente() throws RemoteException, NotBoundException {
        initComponents();
        name = JOptionPane.showInputDialog(this, "Ingese un nombre para identificar al cliente", "INICIANDO CLIENTE", JOptionPane.INFORMATION_MESSAGE);
        cliente = new Middleware(name, this);
        registry = LocateRegistry.getRegistry(IP, PUERTO);
        server = (FSInterfaz) registry.lookup("fs"); // Buscar en el registro...
        server.agregarCliente(cliente);
        cargarArchivo();
        ta_archivo.setVisible(false);
        btn_guardar.setVisible(false);

        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenuDir = new javax.swing.JPopupMenu();
        crearArchivo = new javax.swing.JMenuItem();
        crearDir = new javax.swing.JMenuItem();
        eliminarDir = new javax.swing.JMenuItem();
        popMenuArchivo = new javax.swing.JPopupMenu();
        abrirArchivo = new javax.swing.JMenuItem();
        eliminarArchivo = new javax.swing.JMenuItem();
        popMenuRoot = new javax.swing.JPopupMenu();
        crearArchivo1 = new javax.swing.JMenuItem();
        crearDir1 = new javax.swing.JMenuItem();
        eliminarDir1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbolCliente = new javax.swing.JTree();
        btn_cargarArchivo = new javax.swing.JButton();
        btn_desmontarFS = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_archivo = new javax.swing.JTextArea();
        btn_guardar = new javax.swing.JButton();

        crearArchivo.setText("Crear Archivo");
        crearArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearArchivoActionPerformed(evt);
            }
        });
        popMenuDir.add(crearArchivo);

        crearDir.setText("Crear Directorio");
        crearDir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearDirMouseClicked(evt);
            }
        });
        crearDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearDirActionPerformed(evt);
            }
        });
        popMenuDir.add(crearDir);

        eliminarDir.setText("Eliminar Directorio");
        eliminarDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarDirActionPerformed(evt);
            }
        });
        popMenuDir.add(eliminarDir);

        abrirArchivo.setText("Abrir Archivo");
        abrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirArchivoActionPerformed(evt);
            }
        });
        popMenuArchivo.add(abrirArchivo);

        eliminarArchivo.setText("Eliminar Archivo");
        eliminarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarArchivoActionPerformed(evt);
            }
        });
        popMenuArchivo.add(eliminarArchivo);

        popMenuRoot.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        popMenuRoot.setName(""); // NOI18N

        crearArchivo1.setText("Crear Archivo");
        crearArchivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearArchivo1ActionPerformed(evt);
            }
        });
        popMenuRoot.add(crearArchivo1);

        crearDir1.setText("Crear Directorio");
        crearDir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearDir1MouseClicked(evt);
            }
        });
        crearDir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearDir1ActionPerformed(evt);
            }
        });
        popMenuRoot.add(crearDir1);

        eliminarDir1.setText("Eliminar Directorio");
        eliminarDir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarDir1ActionPerformed(evt);
            }
        });
        popMenuRoot.add(eliminarDir1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        arbolCliente.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        arbolCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arbolClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(arbolCliente);

        btn_cargarArchivo.setText("Cargar Directorios");
        btn_cargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cargarArchivoActionPerformed(evt);
            }
        });

        btn_desmontarFS.setText("Desmontar FS");
        btn_desmontarFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_desmontarFSActionPerformed(evt);
            }
        });

        jLabel1.setText("Cliente");

        ta_archivo.setColumns(20);
        ta_archivo.setRows(5);
        jScrollPane2.setViewportView(ta_archivo);

        btn_guardar.setText("Guardar");
        btn_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_guardarMouseClicked(evt);
            }
        });
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_desmontarFS, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(383, 383, 383)
                                .addComponent(btn_guardar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_cargarArchivo)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(45, 45, 45)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addComponent(btn_cargarArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_desmontarFS)
                    .addComponent(btn_guardar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cargarArchivoActionPerformed
        try {
            DefaultTreeModel modelo = server.cargarDirectorio();
            arbolCliente.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btn_cargarArchivoActionPerformed

    private void btn_desmontarFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_desmontarFSActionPerformed

        try {
            if (!montar) {
                btn_desmontarFS.setText("Desmontar FS");
                arbolCliente.setEnabled(true);
                btn_cargarArchivo.setEnabled(true);
                montar = !montar;
                server.agregarCliente(cliente);
                cargarArchivo();
            } else {
                server.desmontar(name);

                // cerrar el archivo
                abierto = "";
                ta_archivo.setVisible(false);
                btn_guardar.setVisible(false);
                // volver a cargar la estructura
                btn_desmontarFS.setText("Montar FS");
                arbolCliente.setEnabled(false);
                btn_cargarArchivo.setEnabled(false);
                montar = !montar;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_btn_desmontarFSActionPerformed

    private void arbolClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arbolClienteMouseClicked
        arbolCliente.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        if (evt.isMetaDown()) {
            int row = arbolCliente.getClosestRowForLocation(evt.getX(), evt.getY());
            arbolCliente.setSelectionRow(row);
            Object v1 = arbolCliente.getSelectionPath().getLastPathComponent();
            nodo_seleccionado = (DefaultMutableTreeNode) v1;
            if (nodo_seleccionado.getUserObject() instanceof FileConText) {
                file_seleccionado = ((FileConText) nodo_seleccionado.getUserObject()).getFile();
                fct_seleccionado = (FileConText) nodo_seleccionado.getUserObject();
                if (fct_seleccionado.isDir) {
                    popMenuDir.show(evt.getComponent(), evt.getX(), evt.getY());
                } else {
                    popMenuArchivo.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            } else if (nodo_seleccionado.isRoot()) {
                popMenuRoot.show(evt.getComponent(), evt.getX(), evt.getY());
            }

        }
    }//GEN-LAST:event_arbolClienteMouseClicked

    private void crearDirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearDirMouseClicked

    }//GEN-LAST:event_crearDirMouseClicked

    private void crearDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearDirActionPerformed
        String path = pathArchivo();
        String name = JOptionPane.showInputDialog(this, "Nombre del Directorio");
        path = path.concat(name);
        path = path.concat("/");
        File dir = new File(path);
        dir.delete();
        
        // padre del directorio actual
        File parent = dir.getParentFile();

        try {
            server.crearArchivo(dir, false);
                    parent.getParentFile().delete();
        } catch (Exception e) {
            System.out.println(e);
        }
        cargarArchivo();

    }//GEN-LAST:event_crearDirActionPerformed

    private void eliminarDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarDirActionPerformed
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbolCliente.getLastSelectedPathComponent();
        String path = pathArchivo();
        path = path.concat("/");
        File dir = new File(path);

        try {
            server.eliminarArchivo(dir.getParentFile());
        } catch (Exception e) {
            System.out.println(e);
        }
        cargarArchivo();
    }//GEN-LAST:event_eliminarDirActionPerformed

    private void eliminarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarArchivoActionPerformed
        DefaultTreeModel modelo = (DefaultTreeModel) arbolCliente.getModel();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbolCliente.getLastSelectedPathComponent();
        String path = pathArchivo();
        //path = path.concat("/");
        File dir = new File(path);

        try {
            server.eliminarArchivo(dir.getParentFile());
        } catch (Exception e) {
            System.out.println(e);
        }
        cargarArchivo();
    }//GEN-LAST:event_eliminarArchivoActionPerformed

    private void crearArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearArchivoActionPerformed
        String path = pathArchivo();
        String name = JOptionPane.showInputDialog(this, "Nombre del Archivo");
        path = path.concat(name);
        path = path.concat("/");
        File archivo = new File(path);

        try {
            server.crearArchivo(archivo, true);
        } catch (Exception e) {
            System.out.println(e);
        }
        cargarArchivo();
    }//GEN-LAST:event_crearArchivoActionPerformed

    private void crearArchivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearArchivo1ActionPerformed
        String path = pathArchivo();
        String name = JOptionPane.showInputDialog(this, "Nombre del Archivo");
        path = path.concat(name);
        path = path.concat("/");
        File archivo = new File(path);

        try {
            server.crearArchivo(archivo, true);
        } catch (Exception e) {
            System.out.println(e);
        }
        cargarArchivo();

    }//GEN-LAST:event_crearArchivo1ActionPerformed

    private void crearDir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearDir1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_crearDir1MouseClicked

    private void crearDir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearDir1ActionPerformed
        String path = pathArchivo();
        String name = JOptionPane.showInputDialog(this, "Nombre del Directorio");
        path = path.concat(name);
        path = path.concat("/");
        File dir = new File(path);

        try {
            server.crearArchivo(dir, false);
        } catch (Exception e) {
            System.out.println(e);
        }
        cargarArchivo();

    }//GEN-LAST:event_crearDir1ActionPerformed

    private void eliminarDir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarDir1ActionPerformed
        String path = pathArchivo();
        //path = path.concat("/");
        File dir = new File(path);
        System.out.println(path);

        try {
            server.eliminarArchivo(dir);
        } catch (Exception e) {
            System.out.println(e);
        }
        cargarArchivo();
    }//GEN-LAST:event_eliminarDir1ActionPerformed

    private void abrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirArchivoActionPerformed
        ta_archivo.setText(fct_seleccionado.getText());
        ta_archivo.setVisible(true);
        btn_guardar.setVisible(true);
        abierto = dirLocalServer(new File(fct_seleccionado.getFile().getAbsolutePath()));
        System.out.println("Abriendo archivo: " + abierto);
    }//GEN-LAST:event_abrirArchivoActionPerformed

    private void btn_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardarMouseClicked
        try {
            server.editarArchivo(file_seleccionado, ta_archivo.getText());
            abierto = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        cargarArchivo();
        ta_archivo.setVisible(false);
        btn_guardar.setVisible(false);
    }//GEN-LAST:event_btn_guardarMouseClicked

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("Cerrando...");
        try {
            server.desmontar(name);
        } catch (RemoteException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_formWindowClosing

    public void conflicto() {
        // se detecto un conflicto
        // notificarlo
        JOptionPane.showMessageDialog(this, "Se detecto un conflicto con el archivo que tiene abierto:\n" + abierto);
        // cerrar el archivo
        abierto = "";
        ta_archivo.setVisible(false);
        btn_guardar.setVisible(false);
        // volver a cargar la estructura
        cargarArchivo();
    }
    
    public void cargarArchivo() {
        try {
            DefaultTreeModel modelo = server.cargarDirectorio();
            arbolCliente.setModel(modelo);

            // reset del cache en el cliente
            resetCache();
            // Recorrer todos los archivos en el arbol
            recorrer((DefaultMutableTreeNode) arbolCliente.getModel().getRoot());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void recorrer(DefaultMutableTreeNode nodo) throws IOException {
        // Recorrer todos los archivos en el arbol e imprimir su info
        FileConText file = (FileConText) nodo.getUserObject();

        boolean isFile = file.isFile;
        boolean isDir = file.isDir;

        File local = new File(dirLocal(file.getFile()));

        if (isFile) {
            local.getParentFile().mkdirs();
            local.createNewFile();
            writeFile(local.getAbsolutePath(), file.getText());
        }

        if (isDir) {
            local.mkdirs();
        }

        if (nodo.isLeaf()) {
            return;
        }

        for (int i = 0; i < nodo.getChildCount(); i += 1) {
            recorrer((DefaultMutableTreeNode) nodo.getChildAt(i));
        }
    }

    public String dirLocalServer(File dirServer) {
        // Obtener el dir local, dado el del server
        String[] spl = dirServer.getAbsolutePath().split("RootServer");
        if (spl.length == 1) {
            return "RootServer";
        }

        return "RootServer" + spl[1];
    }
    
    public String dirLocal(File dirServer) {
        // Obtener el dir local, dado el del server
        String[] spl = dirServer.getAbsolutePath().split("RootServer");
        if (spl.length == 1) {
            return "RootClient" + name;
        }

        return "RootClient" + name + spl[1];
    }

    public void writeFile(String filename, String text) {
        try {
            Files.write(Paths.get(filename), text.getBytes());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void resetCache() {
        File rootClient = new File("RootClient" + name);

        // Borrar el directorio de cache del cliente
        eliminarDirs(rootClient);

        // Volver a crear el directorio
        rootClient.mkdirs();
    }

    public void eliminarDirs(File root) {
        File[] allContents = root.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                eliminarDirs(file);
            }
        }
        root.delete();
    }

    public void regenerar() {
        try {
            DefaultTreeModel modelo = server.cargarDirectorio();
            arbolCliente.setModel(modelo);
            arbolCliente.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
            arbolCliente.setSelectionRow(0);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Cliente().setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NotBoundException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public String pathArchivo() {
        String ruta = "";
        arbolCliente.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbolCliente.getLastSelectedPathComponent();
        for (int i = 0; i < node.getPath().length; i++) {
            ruta = ruta.concat(node.getPath()[i].toString());
            ruta = ruta.concat("/");

        }

        return ruta;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirArchivo;
    private javax.swing.JTree arbolCliente;
    private javax.swing.JButton btn_cargarArchivo;
    private javax.swing.JButton btn_desmontarFS;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JMenuItem crearArchivo;
    private javax.swing.JMenuItem crearArchivo1;
    private javax.swing.JMenuItem crearDir;
    private javax.swing.JMenuItem crearDir1;
    private javax.swing.JMenuItem eliminarArchivo;
    private javax.swing.JMenuItem eliminarDir;
    private javax.swing.JMenuItem eliminarDir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu popMenuArchivo;
    private javax.swing.JPopupMenu popMenuDir;
    private javax.swing.JPopupMenu popMenuRoot;
    private javax.swing.JTextArea ta_archivo;
    // End of variables declaration//GEN-END:variables
    DefaultMutableTreeNode nodo_seleccionado;
    File file_seleccionado;
    File root;
    FileConText fct_seleccionado;
    String name;
    boolean montar = true;
}
