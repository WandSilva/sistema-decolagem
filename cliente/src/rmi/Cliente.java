package rmi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe responsável por encapsular as informações dos compradores de passagens
 * areas.
 */
public class Cliente {

    private Guiche guiche;
    private String endacesso;
    private String id;

    /**
     * Contrutor da Classe Cliente.
     */
    public Cliente() {
        this.id = JOptionPane.showInputDialog(null, "Digite o seu ID");
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

    /**
     * Método responsável por buscar os locais de interesse.
     */
    public ArrayList<String> buscarLocais() {
        return guiche.buscarLocais();
    }

    /**
     * Método responsável por identificar um usuário.
     *
     * @param id nome do usuário
     */
    public void setID(String id) throws RemoteException {
        guiche.setID(id);
    }

    /**
     * Método responsável por buscar as rotas.
     *
     * @param orgigem cidade de partida
     * @param destino cidade de destino
     */
    public ArrayList<String> buscarRotas(String orgigem, String destino) throws RemoteException {
        return guiche.buscarRotas(orgigem, destino);
    }

    /**
     * Método responsável por adquirir uma rota específica
     *
     * @param rota trecho adquirido
     */
    public void comprarRota(String rota) throws RemoteException {
        guiche.comprarRota(rota);
    }

    /**
     * Método responsável por reservar uma rota.
     *
     * @param rota rota desejada
     */
    public boolean reservarRota(String rota) throws RemoteException {
        return guiche.reservarRota(rota);
    }

    /**
     * Método responsável por estabelecer as conexões entre os servidores das
     * companhias.
     */
    public void carregarServidores() throws RemoteException {
        guiche.carregarServidores();
    }
}
