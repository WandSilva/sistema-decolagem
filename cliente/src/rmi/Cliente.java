package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Created by wanderson on 28/07/17.
 */
public class Cliente {
    private Guiche guiche;
    private String end;
    private String serv;
    
    public Cliente() {        
        this.end = JOptionPane.showInputDialog(null, "Digite o IP do Servidor");
        this.serv = JOptionPane.showInputDialog(null, "Qual a companhia?");
        
        try {
            this.guiche = (Guiche) Naming.lookup("rmi://"+end+":1099/servidor"+serv);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> buscarLocais(){
        return guiche.buscarLocais();
    }

    public ArrayList<String> buscarRotas(String orgigem, String destino) throws RemoteException {
        return guiche.pesquisarRotas(orgigem, destino);
    }
}
