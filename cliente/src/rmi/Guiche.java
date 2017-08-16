package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by wanderson on 28/07/17.
 */
public interface Guiche extends Remote {
    public ArrayList<String> pesquisarRotas(String origem, String destino) throws RemoteException;
    public ArrayList<String> buscarLocais();
    public void comprarRota(ArrayList<String> rota) throws RemoteException;
}
