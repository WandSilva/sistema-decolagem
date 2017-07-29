package rmi;

import controller.Controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by wanderson on 28/07/17.
 */
public class GuicheImp extends UnicastRemoteObject implements Guiche {

    private Controller controller;

    protected GuicheImp() throws RemoteException {
        super();
        this.controller = new Controller();
    }

    @Override
    public void pesquisarRotas(String origem, String destino) throws RemoteException {

    }

}
