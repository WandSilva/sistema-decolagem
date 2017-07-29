package controller;

import util.Grafo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by wanderson on 28/07/17.
 */
public class Controller {

    private Grafo grafo;

    public Controller() {
        this.grafo = new Grafo();
    }

    public void criarRotas() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("rotas.data")).useDelimiter("\\||\\n");

        while (scanner.hasNext()) {
            String linha = scanner.nextLine();
            String[] aux = linha.trim().split(":");
            this.grafo.addTrecho(aux[0], aux[1]);
        }
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public ArrayList<String> buscarRotas(String origem, String destino) {
        return grafo.caminhosPossiveis(origem, destino);
    }

    public ArrayList<String> buscarLocais() {
        return grafo.getVertices();
    }

    //usando para testar o grafo
    public static void main(String[] args) {
        Controller c = new Controller();
        try {
            c.criarRotas();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList lista1=c.buscarLocais();

        System.out.println("_______locais___________");
        for (int i = 0; i < lista1.size(); i++) {
            System.out.println(lista1.get(i));
        }

        ArrayList lista2 = c.buscarRotas("A", "B");
        System.out.println("_______caminho___________");
        for (int i = 0; i < lista2.size(); i++) {
            System.out.println(lista2.get(i));
        }
    }

}