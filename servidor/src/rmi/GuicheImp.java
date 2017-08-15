package rmi;

import controller.Controller;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by wanderson on 28/07/17.
 */
public class GuicheImp extends UnicastRemoteObject implements Guiche {

    private Controller controller;

    protected GuicheImp(String server) throws RemoteException, FileNotFoundException {
        super();
        this.controller = new Controller();
        this.controller.criarRotas(server);
    }

    @Override
    public ArrayList<String> pesquisarRotas(String origem, String destino) throws RemoteException {

        return  controller.buscarRotas(origem,destino);
    }

    @Override
    public ArrayList<String> buscarLocais() throws RemoteException {
        return controller.buscarLocais();
    }

}
