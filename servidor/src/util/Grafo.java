package util;

import java.util.*;

/**
 * Classe que responsável por modelar os trechos e os possíveis caminhos existentes.
 */

public class Grafo {

    private HashMap<String, ArrayList<Rota>> grafo;   //Lista de adjacência

    /**
     * Construtor da classe, o grafo é inicializado.
     */
    public Grafo() {
        this.grafo = new HashMap<String, ArrayList<Rota>>();
    }

    /**
     * Método que retorna as cidades vizinhas de uma dada origem.
     *
     * @param origem
     * @return ArrayList
     */
    public ArrayList<Rota> getVizinhos(String origem) {
        return (this.grafo.get(origem));
    }

    
    /**
     * Método que retorna as cidades existentes na estrutura.
     *
     * @return ArrayList
     */

    public ArrayList<String> getVertices(){
        ArrayList<String> lista = new ArrayList<>();
        lista.addAll(this.grafo.keySet());
        return lista;
    }

    /**
     * Método que recebe a origem, destino e a quantidade de passagens e cria um trecho entre eles.
     *
     * @param origem
     * @param destino
     */
    public void addTrecho(String origem, String destino, String peso) {

        if (this.grafo.containsKey(origem)) {
            ArrayList<Rota> vizinhos = this.grafo.get(origem);
            this.grafo.get(origem).add(new Rota(destino, Integer.parseInt(peso)));
        } else {
            ArrayList<Rota> vizinhos = new ArrayList<>();
            vizinhos.add(new Rota(destino, Integer.parseInt(peso)));
            this.grafo.put(origem, vizinhos);
        }
    }
}