package fs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.File;
import javax.swing.tree.DefaultTreeModel;

public interface FSInterfaz extends Remote {

    void desmontar() throws RemoteException;

    DefaultTreeModel cargarDirectorio() throws RemoteException;

    void crearArchivo(File archivoaCrear, boolean esArchivo) throws RemoteException;
    
    void editarArchivo(File editandoArchivo, String texto) throws RemoteException;
    
    void eliminarArchivo(File archivoaEliminar) throws RemoteException;
    
}
