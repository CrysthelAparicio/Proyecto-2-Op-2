/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package p2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author isaac
 */
public class Comunicacion extends UnicastRemoteObject implements ComuFace {
     public String name;
    public DefaultTreeModel model;
    public Vector<ComuFace> clients = new Vector();

    public Comunicacion(String name) throws RemoteException {
        this.name = name;
    }

    public Comunicacion(String name, DefaultTreeModel model) throws RemoteException {
        this.name = name;
        this.model = model;
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public DefaultTreeModel getModel() throws RemoteException {
        return this.model;
    }

    @Override
    public void sendTree() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setModel(DefaultTreeModel modelo) throws RemoteException {
        this.model = modelo;
    }

    @Override
    public void setClient(ComuFace cliente) throws RemoteException {
        clients.add(cliente);
    }

    @Override
    public void send(String msg) throws RemoteException {
        System.out.println(msg);

    }

    @Override
    public void printMessage(String s) throws RemoteException {
        if(!this.name.equals("server")){
            System.out.println(s);
        }
        Iterator<ComuFace> it = this.clients.iterator();
        while (it.hasNext()) {
            it.next().printMessage(s);
        }
    }

    @Override
    public void nuevoCliente() throws RemoteException {
        Iterator<ComuFace> it = this.clients.iterator();
        while (it.hasNext()) {
            it.next().setModel(this.model);
            //actual.printMessage("Cambios en el arbol, presione refrescar!");
        }
    }

    @Override
    public File requestFile(String path) throws RemoteException {
        //System.out.println("Desde Com: " + path);
        File retVal = new File(path);
        //System.out.println(retVal.exists());
        
        
        /*BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(retVal));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Com.class.getName()).log(Level.SEVERE, null, ex);
        }
        String linea = "";
        String retorno = "";
        try {
            while((linea = br.readLine())!= null){
                retorno += linea;
                retorno += "\n";
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Com.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return retVal;
    }

    @Override
    public void saveFile(String content, File file) throws RemoteException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            bw.write(content);
            bw.flush();
            bw.close();
            printMessage(">>Archivo modificado. Estructura Actualizada.");

        } catch (IOException ex) {
            Logger.getLogger(Comunicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

