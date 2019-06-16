package gui;

import fs.FSInterfaz;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Servidor extends javax.swing.JFrame {

    private static final int PUERTO = 1100; // Si cambias aquí el puerto, recuerda cambiarlo en el cliente

    public Remote remote;
    public Registry registry;
    public WatchService watcher;
    
    public Servidor() throws RemoteException, AlreadyBoundException {
        initComponents();
        
        remote = UnicastRemoteObject.exportObject(new FSInterfaz() {
            @Override
            public float dividir(float numero1, float numero2) throws RemoteException {
                return numero1 / numero2;
            }
            
            @Override
            public void desmontar() throws RemoteException {
            }
            
            @Override
            public DefaultTreeModel cargarDirectorio() throws RemoteException {
                DefaultMutableTreeNode root = new DefaultMutableTreeNode("RootServer");
                cargarArbol("./RootServer", root);
                return new DefaultTreeModel(root);
            }

            @Override
            public File crearArchivo(File archivoaCrear) throws RemoteException {
                return new File("");
            }

            @Override
            public File editarArchivo(File editandoArchivo) throws RemoteException {
                return new File("");
            }

            @Override
            public File eliminarArchivo(File archivoaEliminar) throws RemoteException {
                return new File("");
            }

            @Override
            public File eliminarDirectorio(File directorioaEliminar) throws RemoteException {
                return new File("");
            }
        }, 0);
        
        registry = LocateRegistry.createRegistry(PUERTO);
       	System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO));
        registry.bind("Calculadora", remote); // Registrar calculadora        

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("RootServer");
        cargarArbol("./RootServer", root);
        arbolServidor.setModel(new DefaultTreeModel(root));
        
//        watcher = new WatchService();
        
        this.setLocationRelativeTo(null);
    }

    void cargarArbol(String dir, DefaultMutableTreeNode node) {
        File root = new File(dir);
        File subdirfile;
        File[] list = root.listFiles();
        String filename;
        for (File file : list) {
            filename = file.getName();
            if (file.isFile()) {
                node.add(new DefaultMutableTreeNode(filename));
            } else if (file.isDirectory()) {
                DefaultMutableTreeNode subdir = new DefaultMutableTreeNode(filename);
                subdirfile = new File(root.getAbsolutePath(),  filename);
                cargarArbol(subdirfile.getAbsolutePath(), subdir);
                node.add(subdir);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbolServidor = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Algo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(arbolServidor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("Este es el servidor");
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
             public void run() {
                try {
                    new Servidor().setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (AlreadyBoundException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolServidor;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
