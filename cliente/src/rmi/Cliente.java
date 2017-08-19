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
    private String endacesso;
    
    public Cliente() {        
        this.endacesso = JOptionPane.showInputDialog(null, "Digite o IP do Servidor de Acesso");    
        
        try {
            this.guiche = (Guiche) Naming.lookup("rmi://"+endacesso+":1099/servidor");
            carregarServidores();
            
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
    public void comprarRota(String rota) throws RemoteException {
        guiche.comprarRota(rota);
    }
    
    public void carregarServidores() throws RemoteException{
        guiche.carregarServidores();
    }
}
