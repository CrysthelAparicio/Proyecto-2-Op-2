package fs;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Middleware extends UnicastRemoteObject implements FSInterfaz {
    Vector<FSInterfaz> clientes;

    public Middleware() throws RemoteException {
        clientes = new Vector<>();
    }

    public Vector<FSInterfaz> getClientes() {
        return clientes;
    }
    
    @Override
    public void desmontar() throws RemoteException {
    }

    @Override
    public void broadcast() throws RemoteException {
        for (FSInterfaz cliente : clientes) {
            cliente.send("Hola desde el server");
        }
    }

    @Override
    public void send(String msg) throws RemoteException {
        System.out.println(msg);
    }
    
    @Override
    public DefaultTreeModel cargarDirectorio() throws RemoteException {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("RootServer");
        cargarArbol("./RootServer", root);
        return new DefaultTreeModel(root);
    }

    @Override
    public void crearArchivo(File archivoaCrear, boolean esArchivo) throws RemoteException {
        // crear un archivo vacio
        // debe recibir la ruta completa, ej:
        // C:\\Users\\Nohelia\\RootServer\\341234\\12442\\creame\\laptops.txt
        File fileRes = pathRootServer(archivoaCrear);

        if (esArchivo) {
            fileRes.getParentFile().mkdirs();
        } else {
            fileRes.mkdirs();
        }
    }

    @Override
    public void editarArchivo(File editandoArchivo, String texto) throws RemoteException {
    }

    @Override
    public void eliminarArchivo(File archivoaEliminar) throws RemoteException {
        // eliminar un archivo
        // debe recibir la ruta completa, ej:
        // C:\\Users\\Nohelia\\RootServer\\341234\\12442\\creame\\laptops.txt
        File fileRes = pathRootServer(archivoaEliminar);

        eliminarDirs(fileRes);
    }

    @Override
    public void agregarCliente(FSInterfaz cliente) throws RemoteException {
        clientes.add(cliente);
    }
    
    public File pathRootServer(File archivo) {
        // debe recibir la ruta completa, ej:
        // C:\\Users\\Nohelia\\RootServer\\341234\\12442\\creame\\laptops.txt

        String pathRes = archivo.getName();
        File parent = archivo;

        while (true) {
            parent = parent.getParentFile();
            if (parent.getName().equals("RootServer")) {
                break;
            }
            pathRes = parent.getName() + "/" + pathRes;
        }

        pathRes = "./RootServer/" + pathRes;
        return new File(pathRes);
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
    
    public void cargarArbol(String dir, DefaultMutableTreeNode node) {
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
}