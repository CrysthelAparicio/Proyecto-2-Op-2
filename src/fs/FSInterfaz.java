package fs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.File;
import java.io.IOException;
import javax.swing.tree.DefaultTreeModel;

public interface FSInterfaz extends Remote {

    void desmontar(String name) throws RemoteException;
    public String getName() throws RemoteException;
    void send(String fileChanged) throws RemoteException;
    void broadcast(String fileChanged) throws RemoteException;
    DefaultTreeModel cargarDirectorio() throws RemoteException;
    void crearArchivo(File archivoaCrear, boolean esArchivo) throws RemoteException, IOException;
    void editarArchivo(File editandoArchivo, String texto) throws RemoteException;
    void eliminarArchivo(File archivoaEliminar) throws RemoteException;
    void agregarCliente(FSInterfaz cliente) throws RemoteException;
}
