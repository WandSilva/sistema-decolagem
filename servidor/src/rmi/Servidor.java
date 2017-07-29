package rmi;

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
            Guiche guiche = new GuicheImp();
            Naming.bind("GuicheService", (Remote) guiche);
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new Servidor();
    }
}
