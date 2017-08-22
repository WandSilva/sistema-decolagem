package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interface que servidores utilizaram para a comunicação RMI. 
 */
public interface Guiche extends Remote {
    public ArrayList<String> buscarRotas(String origem, String destino) throws RemoteException;
    public boolean reservarRota(String rota) throws RemoteException;
    public void setID(String id)throws RemoteException;
    public void carregarServidores() throws RemoteException;
    public ArrayList<String> buscarLocais() throws RemoteException;
    public void comprarRota(String rota) throws RemoteException;
    public String getNomeServidor() throws RemoteException;
}
