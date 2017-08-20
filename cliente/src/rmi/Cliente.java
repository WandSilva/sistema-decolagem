package rmi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            BufferedReader bf;
            bf = new BufferedReader(new FileReader("servidores_C.data"));
            String linha = bf.readLine();

            while (linha != null) {
                if (linha.startsWith(endacesso)) {
                    String[] dados = linha.split(" ");
                    this.guiche = (Guiche) Naming.lookup("rmi://" + endacesso + ":1099/" + dados[1]);
                }
                linha = bf.readLine();
            }

            carregarServidores();

        } catch (Exception ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> buscarLocais() {
        return guiche.buscarLocais();
    }

    public ArrayList<String> buscarRotas(String orgigem, String destino) throws RemoteException {
        return guiche.buscarRotas(orgigem, destino);
    }

    public void comprarRota(String rota) throws RemoteException {
        guiche.comprarRota(rota);
    }

    public void carregarServidores() throws RemoteException {
        guiche.carregarServidores();
    }
}
