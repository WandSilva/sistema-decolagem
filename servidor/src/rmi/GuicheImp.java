package rmi;

import controller.Controller;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Classe que implementa os métodos da interface Guiche.
 * @see rmi.Guiche
 */
public class GuicheImp extends UnicastRemoteObject implements Guiche {

    private String nomeServidor;
    private Controller controller;
    
/**
 * Método que realizar uma compra de rota.
 * @param rota rota adquirida
 * 
 */
    
    @Override
    public void comprarRota(String rota) throws RemoteException {
        this.controller.comprar(rota);
    }
    
/**
 * Contrutor da Classe GuicheImp.
 * @param nomeServidor Identificação do Servidor
 * 
 */
    protected GuicheImp(String nomeServidor) throws RemoteException, FileNotFoundException {
        super();
        this.nomeServidor = nomeServidor;
        this.controller = new Controller(nomeServidor);
        this.controller.criarRotas();
    }

/**
     * Método responsável por buscar as rotas.
     *
     * @param origem cidade de partida
     * @param destino cidade de destino
     */
    
    @Override
    public ArrayList<String> buscarRotas(String origem, String destino) throws RemoteException {

        ArrayList<String> teste = controller.buscarRotas(origem, destino);
        return teste;
    }
    
    /**
     * Método responsável por buscar os locais de interesse.
     */

    @Override
    public ArrayList<String> buscarLocais() throws RemoteException {
        return controller.buscarLocais();
    }
    
    /**
     * Método responsável por estabelecer as conexões entre os servidores das
     * companhias.
     */

    @Override
    public void carregarServidores() throws RemoteException {
        controller.carregarServidores();
    }
    
    /**
     * Método responsável por obter o nome do servidor.
     */

    @Override
    public String getNomeServidor() throws RemoteException {
        return controller.getNomeServidor();
    }

     /**
     * Método responsável por reservar uma rota.
     *
     * @param rota rota desejada
     */
    
    @Override
    public boolean reservarRota(String rota) throws RemoteException {
        return controller.reservarRota(rota);
    }
    
    /**
     * Método responsável por identificar um usuário.
     *
     * @param id nome do usuário
     */

    @Override
    public void setID(String id) throws RemoteException {
        controller.setID(id);
    }
}
