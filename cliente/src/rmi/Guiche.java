package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by wanderson on 28/07/17.
 */
public interface Guiche extends Remote {
    public void pesquisarRotas(String origem, String destino) throws RemoteException;
}
