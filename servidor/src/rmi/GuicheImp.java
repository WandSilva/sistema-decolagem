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

    protected GuicheImp(String nomeServidor) throws RemoteException, FileNotFoundException {
        super();
        this.controller = new Controller(nomeServidor);
        this.controller.criarRotas();
    }

    @Override
    public ArrayList<String> pesquisarRotas(String origem, String destino) throws RemoteException {
        
<<<<<<< HEAD
        ArrayList<String> teste = controller.buscarRotas(origem, destino);
        for (int i = 0; i < teste.size(); i++) {
            teste.set(i, teste.get(i) + " -> " + this.nomeServidor);
        }
        
        
        
        return teste;
=======
        return controller.buscarRotas(origem, destino);
>>>>>>> master
    }

    @Override
    public ArrayList<String> buscarLocais() throws RemoteException {
        return controller.buscarLocais();
    }

    @Override
    public void carregarServidores() throws RemoteException {
        controller.carregarServidores();
    }

}
