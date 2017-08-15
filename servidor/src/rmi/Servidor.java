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

/**
 * Created by wanderson on 28/07/17.
 */
public class Servidor {

    private ArrayList servidores = new ArrayList();

    public Servidor() {
        try {
            LocateRegistry.createRegistry(1099);
            Guiche servidor = new GuicheImp();
            Naming.bind("servidor", (Remote) servidor);
            carregarServidores();
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Servidor servidor = new Servidor();
        System.out.println("Servidor iniciado");
    }

    private void carregarServidores() {
        BufferedReader bf;
        try {
            bf = new BufferedReader(new FileReader("servidores.data"));
            String linha = bf.readLine();

            while (linha != null) {
                Guiche guiche = (Guiche) Naming.lookup("rmi://"+linha+":1099/servidor");
                servidores.add(guiche);
                linha = bf.readLine();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}