package fs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.JTree;
import java.io.File;
import javax.swing.tree.DefaultTreeModel;

public interface FSInterfaz extends Remote {

    float dividir(float numero1, float numero2) throws RemoteException;

    void desmontar() throws RemoteException;

    DefaultTreeModel cargarDirectorio() throws RemoteException;

    File crearArchivo(File archivoaCrear) throws RemoteException;
    
    File editarArchivo(File editandoArchivo) throws RemoteException;
    
    File eliminarArchivo(File archivoaEliminar) throws RemoteException;
    
    File eliminarDirectorio(File directorioaEliminar) throws RemoteException;
    

}
