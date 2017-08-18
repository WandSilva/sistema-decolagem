package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import rmi.Cliente;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by wanderson on 28/07/17.
 */
public class FxmlViewController implements Initializable {

    @FXML
    private ComboBox<String> comboDestino;

    @FXML
    private ComboBox<String> comboOrigem;

    @FXML
    private ListView<String> listView;


    Cliente cliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cliente = new Cliente();
        obterLocais();
    }

    public void obterLocais() {
        comboOrigem.getItems().addAll(cliente.buscarLocais());
        comboDestino.getItems().addAll(cliente.buscarLocais());
    }

    public void buscar(ActionEvent e) {
        try {
            ArrayList<String> lista = cliente.buscarRotas(comboOrigem.getValue(), comboDestino.getValue());
            listView.getItems().clear();
            listView.getItems().addAll(lista);
            System.out.println(lista);
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }

    public void comprarRota() {

        try {
            cliente.comprarRota(listView.getSelectionModel().getSelectedItem());
            listView.getItems().clear();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
