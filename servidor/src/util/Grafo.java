package util;

import java.util.*;

/**
 * Created by wanderson on 29/07/17.
 */
public class Grafo {

    private HashMap<String, ArrayList<String>> grafo;   //Lista de adjacência
    private Stack<String> caminhoAtual;   //Pilha de caminho atual
    private TreeSet<String> visitados;   //Conjunto de vértices visitados no caminho
    private ArrayList<String> rotas;

    /**
     * Construtor da classe, o grafo é inicializado.
     */
    public Grafo() {
        this.grafo = new HashMap<String, ArrayList<String>>();
        this.rotas = new ArrayList<>();
        this.caminhoAtual = new Stack<>();
        this.visitados = new TreeSet<>();
    }

    /**
     * Método que retorna as cidades vizinhas de uma dada origem.
     *
     * @param origem
     * @return ArrayList
     */
    public ArrayList<String> getVizinhos(String origem) {
        return (this.grafo.get(origem));
    }

    /**
     * Método que recebe a origem e o destino, e cria um trecho entre eles.
     *
     * @param origem
     * @param destino
     */
    public void addTrecho(String origem, String destino) {

        if (this.grafo.containsKey(origem)) {
            this.grafo.get(origem).add(destino);
        } else {
            ArrayList<String> vizinhos = new ArrayList<String>();
            vizinhos.add(destino);
            this.grafo.put(origem, vizinhos);
        }
    }

    /**
     * Método que identifica todas as possíveis rotas formadas a partir da origem
     * e destino especificados.
     *  @param origem
     * @param destino
     */
    public ArrayList<String> caminhosPossiveis(String origem, String destino) {
        caminhoAtual.push(origem);   //Adiciona cidade na pilha
        visitados.add(origem);   //Adiciona cidade na lista de visitadas

        if (origem.equals(destino)) {   //Se chegou no destino:
            rotas.add(caminhoAtual.toString());
        } else {   //Se não:
            //Pega vizinhos de tal origem no próprio servidor:
            ArrayList<String> vizinhos = getVizinhos(origem);

            for (String vizinho : vizinhos) {   //Percorre a lista de vizinhos

                if (!visitados.contains(vizinho)) {   //Se ainda não foi visitada:
                    //Método recursivo que estabelece caminhos possíveis a partir dela:
                    caminhosPossiveis(vizinho, destino);
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

    public void teste() {
        for (String name : grafo.keySet()) {

            String key = name.toString();
            String value = grafo.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

}

