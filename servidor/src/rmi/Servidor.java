package rmi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Classe responsável por encapsular os parâmetros para utilização do RMI pelos servidores das companhias
 * areas.
 */

public class Servidor {

    private String nomeServidor;

/**
 * Construtor da Classe Servidor.
 */   
    public Servidor() {
        try {
            BufferedReader bf;
            bf = new BufferedReader(new FileReader("nome.data"));
            String linha = bf.readLine();
            this.nomeServidor = linha;
            LocateRegistry.createRegistry(1100);
            Guiche servidor = new GuicheImp(nomeServidor);
            Naming.bind(nomeServidor, (Remote) servidor);
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/**
 * Método para a execução dos servidores
 */    
    public static void main(String args[]) {
        Servidor servidor = new Servidor();
        System.out.println("Servidor iniciado");
    }
}
