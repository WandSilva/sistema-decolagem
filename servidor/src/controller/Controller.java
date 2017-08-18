package controller;

import java.io.BufferedReader;

import util.Grafo;
import util.Rota;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by wanderson on 28/07/17.
 */
public class Controller {

    private Grafo grafo;
    private Stack<String> caminhoAtual;   //Pilha de caminho atual
    private TreeSet<String> visitados;   //Conjunto de vértices visitados no caminho
    private ArrayList<String> rotas;

    public Controller() {
        this.grafo = new Grafo();
        this.rotas = new ArrayList<>();
        this.caminhoAtual = new Stack<>();
        this.visitados = new TreeSet<>();
    }

    public void criarRotas() {

        try {
            BufferedReader bf = new BufferedReader(new FileReader("rotas.data"));

            String linha;
            linha = bf.readLine();
            while (linha != null) {
                String[] aux = linha.trim().split(":");
                this.grafo.addTrecho(aux[0], aux[1], aux[2]);
                linha = bf.readLine();
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<String> buscarRotas(String origem, String destino) {

        caminhoAtual.push(origem);   //Adiciona cidade na pilha
        visitados.add(origem);   //Adiciona cidade na lista de visitadas

        if (origem.equals(destino)) {   //Se chegou no destino:
            rotas.add(caminhoAtual.toString());
        } else {   //Se não:
            //Pega vizinhos de tal origem no próprio servidor:
            ArrayList<Rota> vizinhos = grafo.getVizinhos(origem);

            for (Rota rotas : vizinhos) {   //Percorre a lista de vizinhos

                if (!visitados.contains(rotas.getLocal()) && rotas.getPeso()>0) {   //Se ainda não foi visitada:
                    //Método recursivo que estabelece caminhos possíveis a partir dela:
                    buscarRotas(rotas.getLocal(), destino);
                }
            }
        }
        caminhoAtual.pop();   //Remove cidade-topo da pilha
        visitados.remove(origem);   //Remove cidade da lista de visitadas

        if (caminhoAtual.empty()) {
            ArrayList<String> aux = new ArrayList<>();
            aux.addAll(rotas);
            rotas.clear();
            return aux;
        }
        return null;
    }

    public ArrayList<String> buscarLocais() {
        return grafo.getVertices();
    }

    public void comprar(String rota) {
        String aux = rota.replace("[", "").replace("]", "").replace(" ", "");;
        String[] aux2 = aux.trim().split(",");

        System.out.println("teste: "+aux2);
        System.out.println("teste: "+aux2.length);
        ArrayList<Rota> caminho;

        for (int i = 0; i < aux2.length; i++) {

           caminho = grafo.getVizinhos(aux2[i]);


            for (int j = 0; j < caminho.size(); j++) {
                if (i < aux2.length-1) {
                    if (caminho.get(j).getLocal().equals(aux2[i+1])) {
                        caminho.get(j).setPeso(caminho.get(j).getPeso() - 1);
                    }
                }
            }
        }
    }

//    //usando para testar o grafo
//    public static void main(String[] args) {
//        Controller c = new Controller();
//        try {
//            c.criarRotas();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        ArrayList lista1=c.buscarLocais();
//
//        System.out.println("_______locais___________");
//        for (int i = 0; i < lista1.size(); i++) {
//            System.out.println(lista1.get(i));
//        }
//
//        ArrayList lista2 = c.buscarRotas("A", "B");
//        System.out.println("_______caminho___________");
//        for (int i = 0; i < lista2.size(); i++) {
//            System.out.println(lista2.get(i));
//        }
//    }
}
