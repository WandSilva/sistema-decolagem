package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import rmi.Cliente;

import javax.swing.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by wanderson on 28/07/17.
 */
public class FxmlViewController implements Initializable {

    @FXML
    private ComboBox<String> comboDestino;

    @FXML
    private ComboBox<String> comboOrigem;

    @FXML
    private ListView<String> listViewCompra;
    @FXML
    private ListView<String> listViewReserva;
    @FXML
    private Button btnReservar;
    @FXML
    private Button btnComprar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnMudaTela;
    private int contadorTela=0;


    Cliente cliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cliente = new Cliente();
        this.desabilitarTelaCompra();
        this.habilitarTelaReserva();
        obterLocais();
    }

    public void obterLocais() {
        comboOrigem.getItems().addAll(cliente.buscarLocais());
        comboDestino.getItems().addAll(cliente.buscarLocais());
    }

    public void buscar(ActionEvent e) {
        try {
            ArrayList<String> lista = cliente.buscarRotas(comboOrigem.getValue(), comboDestino.getValue());
            listViewReserva.getItems().clear();
            listViewReserva.getItems().addAll(lista);
            System.out.println(lista);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }

    public void comprarRota() {
        try {
            cliente.comprarRota(listViewCompra.getSelectionModel().getSelectedItem());
            listViewCompra.getItems().clear();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void reservarRotas(){
        try {
            boolean x = cliente.reservarRota(listViewReserva.getSelectionModel().getSelectedItem());
            if(x)
                listViewCompra.getItems().add(listViewReserva.getSelectionModel().getSelectedItem());
            else JOptionPane.showMessageDialog(null,"Rota Indisponível.\n\n\nProfessora, Isso vale 3 pontos");
        } catch (RemoteException ex) {
            Logger.getLogger(FxmlViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mudarTelas(ActionEvent event) {
        if (contadorTela ==0){
            this.desabilitarTelaCompra();
            this.habilitarTelaReserva();
            contadorTela=1;
        }
        else {
            this.desabilitarTelaReserva();
            this.habilitarTelaCompra();
            contadorTela=0;
        }
    }

    public void desabilitarTelaReserva() {
        this.btnReservar.setVisible(false);
        this.listViewReserva.setVisible(false);
        this.comboDestino.setDisable(true);
        this.comboOrigem.setDisable(true);
        this.btnBuscar.setDisable(true);
    }

    public void habilitarTelaReserva() {
        this.btnReservar.setVisible(true);
        this.listViewReserva.setVisible(true);
        this.comboDestino.setDisable(false);
        this.comboOrigem.setDisable(false);
        this.btnBuscar.setDisable(false);
        btnMudaTela.setText("Ver reservas");
    }

    public void desabilitarTelaCompra() {
        btnComprar.setVisible(false);
        listViewCompra.setVisible(false);
    }

    public void habilitarTelaCompra() {
        btnComprar.setVisible(true);
        listViewCompra.setVisible(true);
        btnMudaTela.setText("Voltar");
    }
}
