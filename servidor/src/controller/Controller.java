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
            this.grafo.addTrecho(aux[0],aux[1]);
        }
    }

    public Grafo getGrafo() {
        return grafo;
    }



    //usando para testar o grafo
    public static void main(String[]args){
        Controller c = new Controller();
        try {
            c.criarRotas();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        c.getGrafo().teste();
        ArrayList lista = c.getGrafo().caminhosPossiveis("A", "B");

        System.out.println("_______caminho___________");
        for(int i =0;i<lista.size();i++){
            System.out.println(lista.get(i));
        }
    }
}