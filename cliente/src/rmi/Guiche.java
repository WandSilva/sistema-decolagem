package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by wanderson on 28/07/17.
 */
public interface Guiche extends Remote {
    public ArrayList<String> buscarRotas(String origem, String destino) throws RemoteException;
    public ArrayList<String> buscarLocais();
    public void setID(String id) throws RemoteException ;
    public boolean reservarRota(String rota) throws RemoteException;
    public void comprarRota(String rota) throws RemoteException;
    public void carregarServidores() throws RemoteException;
}
