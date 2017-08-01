package controller;

import util.Grafo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

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

    public void criarRotas() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("rotas.data")).useDelimiter("\\||\\n");

        while (scanner.hasNext()) {
            String linha = scanner.nextLine();
            String[] aux = linha.trim().split(":");
            this.grafo.addTrecho(aux[0], aux[1]);
        }
    }

    public ArrayList<String> buscarRotas(String origem, String destino) {
        caminhoAtual.push(origem);   //Adiciona cidade na pilha
        visitados.add(origem);   //Adiciona cidade na lista de visitadas

        if (origem.equals(destino)) {   //Se chegou no destino:
            rotas.add(caminhoAtual.toString());
        } else {   //Se não:
            //Pega vizinhos de tal origem no próprio servidor:
            ArrayList<String> vizinhos = grafo.getVizinhos(origem);

            for (String vizinho : vizinhos) {   //Percorre a lista de vizinhos

                if (!visitados.contains(vizinho)) {   //Se ainda não foi visitada:
                    //Método recursivo que estabelece caminhos possíveis a partir dela:
                    buscarRotas(vizinho, destino);
                }
            }
        }
        caminhoAtual.pop();   //Remove cidade-topo da pilha
        visitados.remove(origem);   //Remove cidade da lista de visitadas

        if (caminhoAtual.empty()){
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