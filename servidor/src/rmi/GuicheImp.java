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
    @Override
    public void comprarRota(String rota) throws RemoteException {
        this.controller.comprar(rota);
    }

    private Controller controller;

    protected GuicheImp() throws RemoteException, FileNotFoundException {
        super();
        this.controller = new Controller();
        this.controller.criarRotas();
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
