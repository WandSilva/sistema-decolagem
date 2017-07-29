package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by wanderson on 28/07/17.
 */
public class GuicheImp extends UnicastRemoteObject implements Guiche {

    protected GuicheImp() throws RemoteException {
        super();
    }

    @Override
    public void pesquisarRotas(String origem, String destino) throws RemoteException {

    }
}
