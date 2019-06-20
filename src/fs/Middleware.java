package fs;

import gui.Cliente;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Middleware extends UnicastRemoteObject implements FSInterfaz {
    ArrayList<FSInterfaz> clientes = new ArrayList<>();
    String name;
    Cliente frameCliente;

    public Middleware() throws RemoteException {
        this.name = "SERVER";
    }

    public Middleware(String name, Cliente frameCliente) throws RemoteException {
        this.name = name;
        this.frameCliente = frameCliente;
    }
    
    @Override
    public String getName() {
        return name;
    }

    public Cliente getFrame() {
        return frameCliente;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<FSInterfaz> getClients() {
        return clientes;
    }

    @Override
    public void desmontar(String name) throws RemoteException {
        try {
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getName().equals(name)) {
                    clientes.remove(i);
                    System.out.println("Se desmonto correctamente: " + name);
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void broadcast(String fileChanged) throws RemoteException {
        for (FSInterfaz cliente : clientes) {
            cliente.send(fileChanged);
        }
    }

    @Override
    public void send(String fileChanged) throws RemoteException {
        System.out.println("Broadcast del servidor: " + fileChanged);
        // ver si el archivo que el cliente tiene abierto es el que se envio
        if (fileChanged.equals(frameCliente.abierto)) {
            // si es asi, decirle que hay un conflicto
            frameCliente.conflicto();
        } else {
            // volver a cargar la estructura
            frameCliente.cargarArchivo();
        }
    }
    
    @Override
    public DefaultTreeModel cargarDirectorio() throws RemoteException {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new FileConText(new File("RootServer"), "", false, true));
        cargarArbol("RootServer", root);
        return new DefaultTreeModel(root);
    }

    @Override
    public void crearArchivo(File archivoaCrear, boolean esArchivo) throws RemoteException, IOException {
        // crear un archivo vacio
        // debe recibir la ruta completa, ej:
        // C:\\Users\\Nohelia\\RootServer\\341234\\12442\\creame\\laptops.txt
        File fileRes = pathRootServer(archivoaCrear);

        if (esArchivo) {
            fileRes.getParentFile().mkdirs();
            fileRes.delete();
        } else {
            fileRes.delete();
        }
    }

    @Override
    public void editarArchivo(File editandoArchivo, String texto) throws RemoteException {
        try {
            Files.write(Paths.get(editandoArchivo.getAbsolutePath()), texto.getBytes());
        } catch (Exception e) {
            System.out.println(e);
        }
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
        System.out.println("Montando al cliente " + cliente.getName());
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

        pathRes = "RootServer/" + pathRes;
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
        File[] list = root.listFiles();
        FileConText res;
        
        for (File file : list) {
            String filename = file.getName();
            if (file.isFile()) {
                String texto = readFile(file.getAbsolutePath());
                res = new FileConText(file, texto, true, false);
                node.add(new DefaultMutableTreeNode(res));
            } else if (file.isDirectory()) {
                res = new FileConText(file, "", false, true);
                DefaultMutableTreeNode subdir = new DefaultMutableTreeNode(res);
                File subdirfile = new File(root.getAbsolutePath(),  filename);
                cargarArbol(subdirfile.getAbsolutePath(), subdir);
                node.add(subdir);
            }
        }
    }
    
    public static String readFile(String fileName) { 
        String data = ""; 
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName))); 
        } catch (IOException e) {
            System.out.println(e);
        }
        return data; 
    } 
}