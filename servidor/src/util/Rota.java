package util;

import java.util.ArrayList;

/**
 * Classe que modela uma caminho entre duas localidades.
 */
public class Rota {
    
    private String local;
    private int peso;
    private ArrayList<String> interessados = new ArrayList();

/**
 * Construtor da Classe Rota.
 * @param local destino
 * @param peso quantidade de passagens
 */
    public Rota(String local, int peso) {
        this.local = local;
        this.peso = peso;
    }

/**
 * Método por retorna o destino.
 */
    public String getLocal() {
        return local;
    }
    
/**
 * Método que modifica o destino.
 */

    public void setLocal(String local) {
        this.local = local;
    }
    
 /**
 * Obter a quantidade de passagens disponíveis para o trecho.
 */
    public int getPeso() {
        return peso;
    }

/**
 * Modificar a quantidade de passagens disponíveis para o trecho.
 */ 
    
    public void setPeso(int peso) {
        this.peso = peso;
    }
    
 /**
 * Obter a lista de interessados em uma determinada rota.
 */
    
    public ArrayList<String> getInteressados() {
        return interessados;
    }  
}
