package util;

import java.util.*;

/**
 * Created by wanderson on 29/07/17.
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


    public ArrayList<String> getVertices(){
        ArrayList<String> lista = new ArrayList<>();
        lista.addAll(this.grafo.keySet());
        return lista;
    }

    /**
     * Método que recebe a origem e o destino, e cria um trecho entre eles.
     *
     * @param origem
     * @param destino
     */
    public void addTrecho(String origem, String destino, String peso) {

        if (this.grafo.containsKey(origem)) {
            this.grafo.get(origem).add(new Rota(destino, Integer.parseInt(peso)));
        } else {
            ArrayList<Rota> vizinhos = new ArrayList<>();
            vizinhos.add(new Rota(destino, Integer.parseInt(peso)));
            this.grafo.put(origem, vizinhos);
        }
    }

    public void teste() {
        for (String name : grafo.keySet()) {

            String key = name.toString();
            String value = grafo.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

}

