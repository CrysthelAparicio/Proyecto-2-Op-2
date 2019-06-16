/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.swing.tree.DefaultTreeModel;


/**
 *
 * @author isaac
 */
public interface ComuFace extends Remote{
    public String getName() throws RemoteException;
    public DefaultTreeModel getModel() throws RemoteException;
    public void setModel(DefaultTreeModel modelo) throws RemoteException;
    public void sendTree() throws RemoteException;
    public void send(String msg) throws RemoteException;
    public void setClient(ComuFace cliente) throws RemoteException;
    public void printMessage(String s) throws RemoteException;
    public void nuevoCliente() throws RemoteException;
    public File requestFile(String path) throws RemoteException;
    public void saveFile(String content, File file) throws RemoteException;
    
}
