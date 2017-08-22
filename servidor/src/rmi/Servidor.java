package rmi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private String nomeServidor;

    public Servidor() {
        try {
            BufferedReader bf;
            bf = new BufferedReader(new FileReader("nome.data"));
            String linha = bf.readLine();
            this.nomeServidor = linha;
            LocateRegistry.createRegistry(1100);
            Guiche servidor = new GuicheImp(nomeServidor);
            Naming.bind("servidor2" , (Remote) servidor);
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Servidor servidor = new Servidor();
        System.out.println("Servidor iniciado");
    }
}
