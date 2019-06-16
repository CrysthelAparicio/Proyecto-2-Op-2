package fs;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FSInterfaz extends Remote {
    float dividir(float numero1, float numero2) throws RemoteException;
}