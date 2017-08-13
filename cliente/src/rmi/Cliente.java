package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by wanderson on 28/07/17.
 */
public class Cliente {
    private Guiche guiche;

    public Cliente() {
        try {
            this.guiche = (Guiche) Naming.lookup("rmi://127.0.0.1:1099/GuicheService");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> buscarLocais(){
        return guiche.buscarLocais();
    }

    public ArrayList<String> buscarRotas(String orgigem, String destino) throws RemoteException {
        return guiche.pesquisarRotas(orgigem, destino);
    }
}
