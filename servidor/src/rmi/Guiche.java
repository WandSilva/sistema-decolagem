package rmi;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by wanderson on 28/07/17.
 */
public interface Guiche extends Remote {
    public ArrayList<String> pesquisarRotas(String origem, String destino) throws RemoteException;
    public void carregarServidores() throws RemoteException;
    public ArrayList<String> buscarLocais() throws RemoteException;
    public void comprarRota(String rota) throws RemoteException;
    public String getNomeServidor() throws RemoteException;
}
