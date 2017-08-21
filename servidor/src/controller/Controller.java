package controller;

import java.io.BufferedReader;

import util.Grafo;
import util.Rota;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import rmi.Guiche;
import rmi.Servidor;

/**
 * Created by wanderson on 28/07/17.
 */
public class Controller {

    private ArrayList<Guiche> servidoresConectados = new ArrayList();

    private String nomeServidor;
    private Grafo grafo;
    private Stack<String> caminhoAtual;   //Pilha de caminho atual
    private TreeSet<String> visitados;   //Conjunto de vértices visitados no caminho
    private ArrayList<String> rotas;

    public Controller(String nomeServidor) {
        this.nomeServidor = nomeServidor;
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
                String[] aux = linha.split(":");
                this.grafo.addTrecho(aux[0], aux[1], aux[2]);
                linha = bf.readLine();
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<String> buscarRotas(String origem, String destino) throws RemoteException {

        System.out.println(origem + " " + destino);

        caminhoAtual.push(origem);   //Adiciona cidade na pilha
        visitados.add(origem);   //Adiciona cidade na lista de visitadas

        if (origem.equals(destino)) {   //Se chegou no destino:
            rotas.add(caminhoAtual.toString());
        } else {   //Se não:
            //Pega vizinhos de tal origem no próprio servidor:
            ArrayList<Rota> vizinhos = grafo.getVizinhos(origem);
            System.out.println(vizinhos.size());

            for (Rota rotas : vizinhos) {   //Percorre a lista de vizinhos
                if (!visitados.contains(rotas.getLocal()) && rotas.getPeso() > 0) {   //Se ainda não foi visitada:
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

            for (int i = 0; i < aux.size(); i++) {
                aux.set(i, aux.get(i) + " -> " + this.nomeServidor);
            }

            for (Guiche g : servidoresConectados) {

                aux.addAll(g.buscarRotas(origem, destino));
            }

            //  System.out.println("Quant servers"+servidoresConectados.size());
            //  servidoresConectados.forEach(servers -> System.out.println(servers));
            return aux;
        }
        return null;
    }

    public ArrayList<String> buscarLocais() {
        return grafo.getVertices();
    }

    public void comprar(String rota) {
        if (rota.contains(this.nomeServidor)) {
            String aux = rota.replace("[", "").replace("]", "").replace(" ", "").replace("->" + this.getNomeServidor(), "");
            System.out.println(aux);

            String[] aux2 = aux.trim().split(",");

            ArrayList<Rota> caminho;

            for (int i = 0; i < aux2.length; i++) {

                caminho = grafo.getVizinhos(aux2[i]);

                for (int j = 0; j < caminho.size(); j++) {
                    if (i < aux2.length - 1) {
                        if (caminho.get(j).getLocal().equals(aux2[i + 1])) {
                            caminho.get(j).setPeso(caminho.get(j).getPeso() - 1);
                        }
                    }
                }
            }
        }
        
        else {
           
               try {
                   
                   for (Guiche g : servidoresConectados) {
                   
                   if(rota.contains(g.getNomeServidor())){
                       g.comprarRota(rota);      
                   }
                }
               } catch (RemoteException ex) {
                   Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
               }
           } 
        }
    

    public void carregarServidores() {
        BufferedReader bf;
        try {
            bf = new BufferedReader(new FileReader("servidores.data"));
            String linha = bf.readLine();
            String[] dados = linha.split(" ");

            //System.out.println("passou aqui");
            while (linha != null) {
                dados = linha.split(" ");
                Guiche guiche = (Guiche) Naming.lookup("rmi://" + dados[0] + ":1099/" + dados[1]);
//                System.out.println(guiche.getNomeServidor());
                servidoresConectados.add(guiche);
                linha = bf.readLine();
            }

        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getNomeServidor() {
        return nomeServidor;
    }
}
