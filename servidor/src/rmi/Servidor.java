package rmi;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by wanderson on 28/07/17.
 */
public class Servidor {
    public Servidor() {
        try {
            LocateRegistry.createRegistry(1099);
            Guiche servidorA = new GuicheImp("A");
            Guiche servidorB = new GuicheImp("B");
            Guiche servidorC = new GuicheImp("C");
            Naming.bind("servidorA", (Remote) servidorA);
            Naming.bind("servidorB", (Remote) servidorB);
            Naming.bind("servidorC", (Remote) servidorC);
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Servidor servidor = new Servidor();
        
        
        
        System.out.println("Servidor iniciado");
    }
}
